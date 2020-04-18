package com.example.coronafeed;

public class Article {
    private String mTitle;
    private String mUrl;
    private String mSource;
    private String mAuthor;
    private String mDescription;
    private String mDate;


    public Article(String mTitle, String mUrl, String mSource, String mAuthor, String mDescription, String mDate) {
        this.mTitle = mTitle;
        this.mUrl = mUrl;
        this.mSource = mSource;
        this.mAuthor = mAuthor;
        this.mDescription = mDescription;
        this.mDate = mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String mSource) {
        this.mSource = mSource;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }
}
