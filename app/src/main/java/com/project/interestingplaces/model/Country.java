package com.project.interestingplaces.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    private int id;
    private String name;
    @SerializedName("picture_url")
    private String pictureUrl;
    private int date;

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
}
