package com.comicreading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comicreading.domain.RssEntry;

public interface RssEntryRepository extends JpaRepository<RssEntry, Long> {
    
}
