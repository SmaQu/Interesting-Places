package com.project.interestingplaces.model;

import com.google.gson.annotations.SerializedName;

public class CountryDetail {

    private int id;
    private String name;
    @SerializedName("picture_url")
    private String pictureUrl;
    private int date;
    private String description;
    @SerializedName("see_more_url")
    private String seeMoreUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeeMoreUrl() {
        return seeMoreUrl;
    }

    public void setSeeMoreUrl(String seeMoreUrl) {
        this.seeMoreUrl = seeMoreUrl;
    }
}
