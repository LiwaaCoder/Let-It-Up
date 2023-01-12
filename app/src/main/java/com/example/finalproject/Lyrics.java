package com.example.finalproject;

import com.google.gson.annotations.SerializedName;

public class Lyrics {
    @SerializedName("lyrics_body")
    private String lyricsBody;

    public String getLyricsBody() {
        return lyricsBody;
    }
}
