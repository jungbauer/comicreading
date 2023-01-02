package com.comicreading.comicreading.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comicreading.comicreading.domain.Comic;

@SpringBootTest
public class ComicRepositoryTest {

    @Autowired
    private ComicRepository comicRepository;

    @Test
    public void createNewComic() {
        Comic newComic = new Comic("comic_title", "http://test.com", "7");
        comicRepository.save(newComic);

        Comic byTitle = comicRepository.findOneByTitle("comic_title");
        assertNotNull(byTitle);
    }
}
