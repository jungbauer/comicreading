package com.comicreading.comicreading.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comics")
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String mainLink;
    private String currChapter;
    private String activeLinkPrefix;
    private String activeLinkSuffix;

    public Comic() {}
    
    public Comic(String title, String mainLink, String currChapter) {
        this.title = title;
        this.mainLink = mainLink;
        this.currChapter = currChapter;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
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

    public String getCurrChapter() {
        return currChapter;
    }

    public void setCurrChapter(String currChapter) {
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
        if (activeLinkPrefix == null || currChapter == null || activeLinkSuffix ==null) return mainLink;
        return activeLinkPrefix.concat(currChapter).concat(activeLinkSuffix);
    }

    public void incrementChapter() {
        Integer newCh = Integer.parseInt(currChapter) + 1;
        currChapter = Integer.toString(newCh);
    }

    @Override
    public String toString() {
        return "Comic [id=" + id + ", title=" + title + ", currChapter=" + currChapter + "]";
    }

}
