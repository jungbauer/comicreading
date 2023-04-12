package com.comicreading.service;

import com.comicreading.domain.Comic;
import com.comicreading.domain.ComicCategory;
import com.comicreading.domain.RssEntry;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import lombok.extern.slf4j.Slf4j;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ChapterScrapeService {

    @Autowired
    private ComicService comicService;

    @Autowired
    private DatabaseLogsService databaseLogsService;

    @Autowired
    private RssEntryService rssEntryService;

    @Scheduled(cron = "0 0 3 * * ?")
    public void scrapeReadingComicChapters() {
        databaseLogsService.logMessage("Starting chapter scrape");
        List<Comic> comicList = comicService.findComicsByCategory(ComicCategory.READING);
        int toonilyCount = 0, toonilyUpdateCount = 0;

        for (Comic comic: comicList) {
            if (comic.getMainLink().contains("toonily.net")) {
                toonilyCount++;
                try {
                    Integer scrapedChapterCount = getToonilyChapterCount(comic.getMainLink());
                    String logMsg;
                    if (scrapedChapterCount > comic.getTotalChapters()) {
                        toonilyUpdateCount++;
                        logMsg = String.format("Updating %s from %d to %d", comic.getMainLink(), comic.getTotalChapters(), scrapedChapterCount);
                        comic.setTotalChapters(scrapedChapterCount);
                        comicService.saveComic(comic);
                    }
                    else {
                        logMsg = String.format("No update needed for %s", comic.getMainLink());
                    }
                    databaseLogsService.logMessage(logMsg);
                } catch (Exception e) {
                    String errorMsg = String.format("FAILED getToonilyChapterCount() for %s", comic.getMainLink());
                    log.error(errorMsg, e);
                    databaseLogsService.logMessage("ERROR!! : " + errorMsg);
                }
            }
        }

        databaseLogsService.logMessage(String.format("Toonily Updates: %d of %d", toonilyUpdateCount, toonilyCount));
    }

    private Integer getToonilyChapterCount(String url) throws IOException, NullPointerException {
        Document doc = Jsoup.connect(url).get();
        Element chapter = doc.select("li.wp-manga-chapter").first();
        if (chapter == null) throw new NullPointerException("No chapter element can be found for" + url);
        String anchorText = chapter.select("a").first().text();
        String chapterCount = anchorText.substring(8); // cutting down from "Chapter 175" to "75"

        return Integer.parseInt(chapterCount);
    }
    
    @Scheduled(cron = "0 0 * 1/1 * ?")
    public void rssFeedCheck() {
        databaseLogsService.logMessage("Checking RSS feed");
        try {
            Map<String, String> headers = new LinkedHashMap<>();
            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            headers.put("Accept-Language", "en-US,en;q=0.5");
            headers.put("Connection", "keep-alive");
            headers.put("DNT", "1");
            headers.put("Host", "www.asurascans.com");
            headers.put("Sec-Fetch-Site", "none");
            headers.put("Sec-Fetch-Mode", "navigate");
            headers.put("Sec-Fetch-User", "?1");
            headers.put("Sec-Fetch-Dest", "document");
            headers.put("Upgrade-Insecure-Requests", "1");

            Connection.Response response = Jsoup.connect("https://www.asurascans.com/feed")
                    .method(Connection.Method.GET)
                    .headers(headers)
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/110.0")
                    .execute();

            InputStream stream = response.bodyStream();
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(stream));
            
            databaseLogsService.logMessage("RSS feed size: " + feed.getEntries().size());

            List<RssEntry> entriesToSave = new ArrayList<>();
            List<Comic> allComics = comicService.getAllComics();

            for (SyndEntry entry : feed.getEntries()) {
                boolean found = false;
                int count = 0;
                while (!found && count < allComics.size()) {
                    found = entry.getTitle().contains(allComics.get(count).getTitle());
                    count++;
                }

                if (found) {
                    databaseLogsService.logMessage("rss found for: " + entry.getTitle());
                    entriesToSave.add(new RssEntry(entry.getTitle(), entry.getLink()));
                } else {
                    databaseLogsService.logMessage("Not comic found for: " + entry.getTitle());
                }
            }

            databaseLogsService.logMessage("RSS feed saving " + entriesToSave.size() + " entries.");
            if (entriesToSave.size() > 0) rssEntryService.saveRssEntries(entriesToSave);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            databaseLogsService.logMessage("IOException: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            databaseLogsService.logMessage("IllegalArgumentException: " + e.getMessage());
        } catch (FeedException e) {
            databaseLogsService.logMessage("FeedException: " + e.getMessage());
        }
    }

    public Map<String, String> manualRssEntryUpdate(boolean dryRun) {
        if (dryRun) databaseLogsService.logMessage("DRYRUN: Comparing comics and recorded RSS feed");
        else databaseLogsService.logMessage("Manual comic vs rssentry update.");
        
        List<Comic> asuraComics = comicService.getMatchingMainLink("asurascans");
        Map<String, String> comparingMap = new HashMap<String, String>();
        Pattern chapterPattern = Pattern.compile("(Chapter \\d{1,})");
        Pattern numberPattern = Pattern.compile("(\\d{1,})");
        for (Comic comic : asuraComics) {
            List<RssEntry> rssEntries = rssEntryService.getMatchingEntries(comic.getTitle());
            Integer feedInt = comic.getTotalChapters();

            for (RssEntry entry : rssEntries) {
                String culledTitle = entry.getTitle().replaceAll(comic.getTitle(), "");
                Matcher chapterMatcher = chapterPattern.matcher(culledTitle.trim());
                boolean found = chapterMatcher.find();
                if (found) {
                    String chapterStr = chapterMatcher.group(0);
                    Matcher numberMatcher = numberPattern.matcher(chapterStr);
                    if (numberMatcher.find()) {
                        feedInt = Integer.parseInt(numberMatcher.group());
                    }
                }
            }

            if (feedInt > comic.getTotalChapters()) {
                if (dryRun) {
                    comparingMap.put(comic.getTitle(), "Entries: " + rssEntries.size() + " - should update from " + comic.getTotalChapters() + " to " + feedInt);
                } else {
                    Integer oldTotal = comic.getTotalChapters();
                    comic.setTotalChapters(feedInt);
                    comicService.saveComic(comic);
                    comparingMap.put(comic.getTitle(), "Entries: " + rssEntries.size() + " - updated from " + oldTotal + " to " + feedInt);
                }
            } else {
                comparingMap.put(comic.getTitle(), "Entries: " + rssEntries.size() + " - no update: " + comic.getTotalChapters() + " vs " + feedInt);
            }
        }

        return comparingMap;
    }
}
