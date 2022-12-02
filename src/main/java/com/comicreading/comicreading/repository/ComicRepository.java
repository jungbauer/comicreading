package com.comicreading.comicreading.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.comicreading.comicreading.domain.Comic;

@Repository
public interface ComicRepository extends CrudRepository<Comic, Integer>{
    
    Comic findOneByTitle(String title);
}
