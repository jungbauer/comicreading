package com.comicreading.comicreading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comicreading.comicreading.domain.Comic;

public interface ComicRepository extends JpaRepository<Comic, Integer>{
    
    Comic findOneByTitle(String title);
}
