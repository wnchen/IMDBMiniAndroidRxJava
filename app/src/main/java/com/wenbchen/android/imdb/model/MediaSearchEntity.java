package com.wenbchen.android.imdb.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wenbchen on 12/1/16.
 */

public class MediaSearchEntity {

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("imdbID")
    private String uuid;

    @SerializedName("Type")
    private String type;

    @SerializedName("Poster")
    private String thumbnailUrl;



    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }



    @Override
    public String toString() {
        return "MediaSearchEntity.title =" + title
                + " MediaSearchEntity.thumbnailUrl=" + thumbnailUrl
                + " MediaSearchEntity.year=" + year
                + " | ";
    }
}
