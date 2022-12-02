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
    private String activeLinkPrefix;
    private String activeLinkSuffix;

    
    public Comic(String title, String mainLink, int currChapter) {
        this.title = title;
        this.mainLink = mainLink;
        this.currChapter = currChapter;
    }

    public int getId() {
        return id;
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

    public String getActiveLinkPrefix() {
        return activeLinkPrefix;
    }

    public void setActiveLinkPrefix(String activeLinkPrefix) {
        this.activeLinkPrefix = activeLinkPrefix;
    }

    public String getActiveLinkSuffix() {
        return activeLinkSuffix;
    }

    public void setActiveLinkSuffix(String activeLinkSuffix) {
        this.activeLinkSuffix = activeLinkSuffix;
    }

    public String getActiveLink() {
        return activeLinkPrefix.concat(Integer.toString(currChapter)).concat(activeLinkSuffix);
    }
    
}
