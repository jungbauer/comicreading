package com.comicreading.service;

import com.comicreading.domain.Comic;
import com.comicreading.domain.ComicCategory;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ChapterScrapeService {

    @Autowired
    private ComicService comicService;

    @Autowired
    private DatabaseLogsService databaseLogsService;

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
}
