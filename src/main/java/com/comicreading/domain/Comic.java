package com.comicreading.domain;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    private ZonedDateTime created;

    @UpdateTimestamp
    private ZonedDateTime updated;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(columnDefinition = "varchar(255) default 'O'")
    private ComicCategory category;

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
        if (activeLinkPrefix == null || currChapter == null) return mainLink;
        if (activeLinkSuffix == null) return activeLinkPrefix.concat(currChapter);
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

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Comic [id=" + id + ", title=" + title + ", currChapter=" + currChapter + "]";
    }

    public ComicCategory getCategory() {
        return category;
    }

    public void setCategory(ComicCategory category) {
        this.category = category;
    }

}
