package com.example.finalproject;

public class Song
{

    private String name = "";
    private String image = "";
    private int duration = 0; // seconds
    private long views = 0;
    private long likes = 0;
    private boolean favorite = false;

    public Song() {
    }

    public String getName() {
        return name;
    }

    public Song setName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Song setImage(String image) {
        this.image = image;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public Song setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public long getViews() {
        return views;
    }

    public Song setViews(long views) {
        this.views = views;
        return this;
    }

    public long getLikes() {
        return likes;
    }

    public Song setLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public Song setFavorite(boolean favorite) {
        this.favorite = favorite;
        return this;
    }
}