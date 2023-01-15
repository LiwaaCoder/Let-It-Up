package com.example.finalproject.Adapters;

import com.google.firebase.database.Exclude;

public class Song {
    private String name;
    private String imageUrl;
    private String description;
    @Exclude
    private String key;

    public Song()
    {
        // Empty constructor is required for Firebase
    }

    public Song(String name, String imageUrl, String description)
    {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
