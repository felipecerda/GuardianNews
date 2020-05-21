package com.example.guardiannews;

import java.time.LocalDateTime;

public class NewsItem {

    private String mTitle;
    private String mSection;
    private String mAuthor;
    private LocalDateTime mDate;
    private String mUrl;

    public NewsItem(String title, String section, String author, LocalDateTime date, String url){
        mTitle = title;
        mSection = section;
        mAuthor = author;
        mDate = date;
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public LocalDateTime getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
