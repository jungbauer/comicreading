package com.comicreading.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class RssEntry {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ZonedDateTime logDateTime;

    @Column(columnDefinition = "text")
    private String title;

    @Column(columnDefinition = "text")
    private String link;

    public RssEntry() {
        this.logDateTime = ZonedDateTime.now();
    }

    public RssEntry(String title, String link) {
        this.logDateTime = ZonedDateTime.now();
        this.title = title;
        this.link = link;
    }
}
