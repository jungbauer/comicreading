package com.comicreading.controller;

import com.comicreading.service.ChapterScrapeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class TestRestController {

    @Autowired
    private ChapterScrapeService chapterScrapeService;

    @GetMapping("/titlesoup")
    public Map<String,Integer> titleSoup() {
        log.info("title soup");
        return chapterScrapeService.titleSoup();
    }

}