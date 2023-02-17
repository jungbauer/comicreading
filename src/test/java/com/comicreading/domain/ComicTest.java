package com.comicreading.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComicTest {
    static Comic testComic;
    static Comic activeComic;

    @BeforeAll
    static void setup() {
        testComic = new Comic("title", "main link", "12");
        activeComic = new Comic("active", "active", "106");
        activeComic.setActiveLinkPrefix("https://toonily.net/manga/mercenary-enrollment/chapter-");
        activeComic.setActiveLinkSuffix("/");
    }

    @Test
    void testGetCurrChapter() {
        assertTrue(testComic.getCurrChapter() == "12");
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
    void testGetActiveLinkPrefix() {
        assertTrue(activeComic.getActiveLinkPrefix() == "https://toonily.net/manga/mercenary-enrollment/chapter-");
    }

    @Test
    void testGetActiveLinkSuffix() {
        assertTrue(activeComic.getActiveLinkSuffix() == "/");
    }

    @Test
    void testGetActiveLink() {
        assertEquals("https://toonily.net/manga/mercenary-enrollment/chapter-106/", activeComic.getActiveLink());
        assertEquals("main link", testComic.getActiveLink());

        Comic emptyStrings = new Comic("Demon Defense Agency", "https://www.voyce.me/series/demon-defense-agency", "1");
        emptyStrings.setActiveLinkPrefix("");
        emptyStrings.setActiveLinkSuffix("");
        assertEquals("https://www.voyce.me/series/demon-defense-agency", emptyStrings.getActiveLink());
    }

    @Test
    void testSetCurrChapter() {
        Comic test1 = new Comic("t", "", "12");
        test1.setCurrChapter("34");
        assertFalse(test1.getCurrChapter() == "12");
        assertTrue(test1.getCurrChapter() == "34");
    }

    @Test
    void testSetMainLink() {
        Comic test2 = new Comic("t", "link", "12");
        test2.setMainLink("place");
        assertFalse(test2.getMainLink() == "link");
        assertTrue(test2.getMainLink() == "place");
    }

    @Test
    void testSetTitle() {
        Comic test3 = new Comic("t", "link", "12");
        test3.setTitle("new title");
        assertFalse(test3.getTitle() == "t");
        assertTrue(test3.getTitle() == "new title");
    }

    @Test
    void testSetActiveLinks() {
        Comic active1 = new Comic("active", "active", "37");
        active1.setActiveLinkPrefix("https://beta.asurascans.com/read/114-transmigrating-to-the-otherworld-once-more/chapter-");
        active1.setActiveLinkSuffix("");

        assertTrue(active1.getActiveLink().equals("https://beta.asurascans.com/read/114-transmigrating-to-the-otherworld-once-more/chapter-37"));
    }

    @Test
    void testIncrementChapter() {
        Comic comic = new Comic("active", "active", "37");
        comic.setTotalChapters(38);
        comic.incrementChapter();

        assertEquals("38", comic.getCurrChapter());

        comic.incrementChapter();
        assertEquals("39", comic.getCurrChapter());
        assertEquals(39, comic.getTotalChapters());
    }
    
}
