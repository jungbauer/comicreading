package com.comicreading.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class DatabaseLogs {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ZonedDateTime logDateTime;

    @Column(columnDefinition = "text default 'default msg'")
    private String message;

    public DatabaseLogs() {
        this.logDateTime = ZonedDateTime.now();
    }

    public DatabaseLogs(String message) {
        this.logDateTime = ZonedDateTime.now();
        this.message = message;
    }
}
