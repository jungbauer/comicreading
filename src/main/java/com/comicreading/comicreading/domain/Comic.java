package com.comicreading.comicreading.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String mainLink;
    private int currChapter;

    
    public Comic(String title, String mainLink, int currChapter) {
        this.title = title;
        this.mainLink = mainLink;
        this.currChapter = currChapter;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMainLink() {
        return mainLink;
    }
    public void setMainLink(String mainLink) {
        this.mainLink = mainLink;
    }
    public int getCurrChapter() {
        return currChapter;
    }
    public void setCurrChapter(int currChapter) {
        this.currChapter = currChapter;
    }

    
}
