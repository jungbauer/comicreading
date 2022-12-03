package com.comicreading.comicreading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comicreading.comicreading.domain.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Integer>{
    
    Comic findOneByTitle(String title);
}
