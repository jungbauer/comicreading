package com.comicreading.domain;

public enum ComicCategory {
    READING("R"), WAITING("W"), DORMANT("D"), LOST_INTEREST("L"), OTHER("O"), COMPLETE("C");

    private String code;

    private ComicCategory(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
