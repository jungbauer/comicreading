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

    /**
     * currChapter needs to be a string for active link construction.
     * Sometimes the format 4-5 is used to denote chapter 4,5. An interim chapter.
     * Sometimes the format is otherwise odd. String was a flexibility choice.
     */
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

    private ZonedDateTime chaptersUpdated;

    @Column(columnDefinition = "varchar(255) default 'O'")
    private ComicCategory category;

    public Comic() {
        setTotalChapters(1);
    }
    
    public Comic(String title, String mainLink, String currChapter) {
        this.title = title;
        this.mainLink = mainLink;
        setCurrChapter(currChapter);
        setTotalChapters(1);
    }

    public void setCurrChapter(String currChapter) {
        this.currChapter = currChapter;
        this.chaptersUpdated = ZonedDateTime.now();
    }

    public void setTotalChapters(Integer totalChapters) {
        this.totalChapters = totalChapters;
        this.chaptersUpdated = ZonedDateTime.now();
    }

    public String getActiveLink() {
        if (activeLinkPrefix == null || currChapter == null) return mainLink;
        if (activeLinkPrefix.isBlank() || currChapter.isBlank()) return mainLink;
        if (activeLinkSuffix == null) return activeLinkPrefix.concat(currChapter);
        return activeLinkPrefix.concat(currChapter).concat(activeLinkSuffix);
    }

    public void incrementChapter() {
        int newCh = Integer.parseInt(currChapter) + 1;
        setCurrChapter(Integer.toString(newCh));

        if (totalChapters < newCh) setTotalChapters(totalChapters + 1);
    }

    @Override
    public String toString() {
        return "Comic [id=" + id + ", title=" + title + ", currChapter=" + currChapter + "]";
    }

    public String wasUpdatedAgo() {
        if (chaptersUpdated == null) return "many moons ago";

        long diffHrs = ChronoUnit.HOURS.between(chaptersUpdated, ZonedDateTime.now());
        long diffDays = ChronoUnit.DAYS.between(chaptersUpdated, ZonedDateTime.now());

        if (diffHrs < 24) return Long.toString(diffHrs).concat(" hours ago");
        else return Long.toString(diffDays).concat(" days ago");
    }
}
