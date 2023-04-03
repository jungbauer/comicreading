package com.comicreading.service;

import com.comicreading.domain.Comic;
import com.comicreading.domain.ComicCategory;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

            for (SyndEntry entry : feed.getEntries()) {
                rssEntryService.saveRssEntry(entry.getTitle(), entry.getLink());
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            databaseLogsService.logMessage("IOException: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            databaseLogsService.logMessage("IllegalArgumentException: " + e.getMessage());
        } catch (FeedException e) {
            databaseLogsService.logMessage("FeedException: " + e.getMessage());
        }
    }
}
