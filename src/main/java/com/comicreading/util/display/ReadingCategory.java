package com.comicreading.util.display;

public enum ReadingCategory {
    CHAPTER_BASED("CB"), ONE_DAY("OD"), SEVEN_DAYS("SD"), TEN_DAYS("TD"), GREATER_THAN_TEN("GT"), WEIRD("W");

    private final String code;

    private ReadingCategory(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
