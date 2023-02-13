package com.comicreading.repository;

import com.comicreading.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comicreading.domain.Comic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ComicRepositoryTest {

    @Autowired
    private ComicRepository comicRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveNewComic() {
        User importUser = userRepository.findByEmail("test@test.com");
        Comic newComic = new Comic("comic_title", "http://test.com", "7");
        newComic.setUser(importUser);
        comicRepository.save(newComic);

        Comic byTitle = comicRepository.findOneByTitle("comic_title");
        assertNotNull(byTitle);
    }

    @Test
    public void findImportedComics() {
        Comic import1 = comicRepository.findOneByTitle("The Beginning After the End");
        List<Comic> list = comicRepository.findAll();

        assertEquals("https://tapas.io/series/tbate-comic/info", import1.getMainLink());
        assertTrue(2 <= list.size());
    }
}
