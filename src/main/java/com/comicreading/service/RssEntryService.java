package com.comicreading.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comicreading.domain.RssEntry;
import com.comicreading.repository.RssEntryRepository;

@Service
public class RssEntryService {
    
    @Autowired
    private RssEntryRepository rssEntryRepository;

    public void saveRssEntry(String title, String link) {
        RssEntry entry = new RssEntry(title, link);
        rssEntryRepository.save(entry);
    }
}
