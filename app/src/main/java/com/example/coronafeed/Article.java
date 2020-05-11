package com.example.coronafeed;

public class Article {
    private String mTitle;
    private String mUrl;
    private String mSource;
    private String mDescription;
    private String mDate;


    public Article(String title, String url, String source,  String description, String date) {
        description = correctsDescription(description,
                description.indexOf("<div>"),
                description.indexOf("</div>"));

        if(description.contains("</iframe>")) {
            description = "No description available";
        }

        mTitle = title;
        mUrl = url;
        mSource = source;
        mDescription = description;
        mDate = date.substring(0,16);
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


    private String correctsDescription(String description, int openingIndex, int closingIndex) {
        String newDescript;
        if(openingIndex != -1) {
            newDescript = description.substring(openingIndex + 5);
            return correctsDescription(newDescript,
                    newDescript.indexOf("<div>"),
                    newDescript.indexOf("</div>"));
        } else if(closingIndex != -1) {
            newDescript = description.substring(0,closingIndex);
            return correctsDescription(newDescript,
                    newDescript.indexOf("<div>"),
                    newDescript.indexOf("</div>"));
        } else {
            return description;
        }
    }
}
