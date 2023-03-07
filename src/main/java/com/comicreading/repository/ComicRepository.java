package com.comicreading.repository;

import java.util.List;
import java.util.Optional;

import com.comicreading.domain.ComicCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.comicreading.domain.Comic;

public interface ComicRepository extends JpaRepository<Comic, Integer>{
    
    Comic findOneByTitle(String title);
    Optional<Comic> findByIdAndUserId(Integer id, Long userId);

    List<Comic> findComicsByCategory(ComicCategory category);
}
