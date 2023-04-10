package com.comicreading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.comicreading.domain.RssEntry;

public interface RssEntryRepository extends JpaRepository<RssEntry, Long> {
    
    @Query(value = "SELECT * FROM rss_entry WHERE title like ?1", nativeQuery = true)
    List<RssEntry> getMatchingEntries(String queryTitle);
}
