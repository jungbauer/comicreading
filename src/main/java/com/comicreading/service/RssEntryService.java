package com.comicreading.service;

import java.util.List;

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

    public void saveRssEntries(List<RssEntry> saveList) {
        rssEntryRepository.saveAll(saveList);
    }

    public List<RssEntry> getEntries() {
        return rssEntryRepository.findAll();
    }

    public List<RssEntry> getMatchingEntries(String title) {
        // select * from rss_entry where title like 'Super Evolution%';
        String queryTitle = title.concat("%");
        return rssEntryRepository.getMatchingEntries(queryTitle);
    }
}
