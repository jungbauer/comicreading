package com.comicreading.domain;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
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

    @Column(columnDefinition = "integer default 1")
    private Integer totalChapters;

    @Column(columnDefinition = "varchar(255) default 'O'")
    private ComicCategory category;

    public Comic() {
        this.totalChapters = 1;
    }
    
    public Comic(String title, String mainLink, String currChapter) {
        this.title = title;
        this.mainLink = mainLink;
        this.currChapter = currChapter;
        this.totalChapters = 1;
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

    @Override
    public String toString() {
        return "Comic [id=" + id + ", title=" + title + ", currChapter=" + currChapter + "]";
    }

    public String wasUpdatedAgo() {
        if (updated == null) return "many moons ago";

        long diffHrs = ChronoUnit.HOURS.between(updated, ZonedDateTime.now());
        long diffDays = ChronoUnit.DAYS.between(updated, ZonedDateTime.now());

        if (diffHrs < 24) return Long.toString(diffHrs).concat(" hours ago");
        else return Long.toString(diffDays).concat(" days ago");
    }
}
