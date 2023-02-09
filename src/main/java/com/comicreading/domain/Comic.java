package com.comicreading.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comics")
public class Comic {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String mainLink;
    private String currChapter;
    private String activeLinkPrefix;
    private String activeLinkSuffix;

    @Column(columnDefinition="TEXT")
    private String notes;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Comic() {}
    
    public Comic(String title, String mainLink, String currChapter) {
        this.title = title;
        this.mainLink = mainLink;
        this.currChapter = currChapter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long newId) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Comic [id=" + id + ", title=" + title + ", currChapter=" + currChapter + "]";
    }

}
