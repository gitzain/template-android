package com.iamzain.template_android.models;

/**
 * Created by zain on 5/15/17.
 */

public class Card {
    private String thumbnail, title, description, meta;

    public Card() {
    }

    public Card(String thumbnail_url, String title, String description, String meta) {
        this.thumbnail = thumbnail_url;
        this.title = title;
        this.description = description;
        this.meta = meta;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
}