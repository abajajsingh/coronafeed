package com.example.coronafeed;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_table")
public class ReadLaterArticle {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mTitle;
    private String mUrl;
    private String mSource;
    private String mDescription;
    private String mDate;

    public ReadLaterArticle(String mTitle, String mUrl, String mSource, String mDescription, String mDate) {
        this.mTitle = mTitle;
        this.mUrl = mUrl;
        this.mSource = mSource;
        this.mDescription = mDescription;
        this.mDate = mDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getSource() {
        return mSource;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDate() {
        return mDate;
    }
}
