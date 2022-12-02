package com.comicreading.comicreading.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ComicTest {
    static Comic testComic;

    @BeforeAll
    static void setup() {
        testComic = new Comic("title", "main link", 12);
    }

    @Test
    void testGetCurrChapter() {
        assertTrue(testComic.getCurrChapter() == 12);
    }

    @Test
    void testGetMainLink() {
        assertTrue(testComic.getMainLink() == "main link");
    }

    @Test
    void testGetTitle() {
        assertTrue(testComic.getTitle() == "title");
    }

    @Test
    void testSetCurrChapter() {
        Comic test1 = new Comic("t", "", 12);
        test1.setCurrChapter(34);
        assertFalse(test1.getCurrChapter() == 12);
        assertTrue(test1.getCurrChapter() == 34);
    }

    @Test
    void testSetMainLink() {
        Comic test2 = new Comic("t", "link", 12);
        test2.setMainLink("place");
        assertFalse(test2.getMainLink() == "link");
        assertTrue(test2.getMainLink() == "place");
    }

    @Test
    void testSetTitle() {
        Comic test3 = new Comic("t", "link", 12);
        test3.setTitle("new title");
        assertFalse(test3.getTitle() == "t");
        assertTrue(test3.getTitle() == "new title");
    }
}
