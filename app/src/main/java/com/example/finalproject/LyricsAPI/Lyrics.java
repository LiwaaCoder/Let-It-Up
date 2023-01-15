package com.example.finalproject.LyricsAPI;

import com.google.gson.annotations.SerializedName;

public class Lyrics {
    @SerializedName("lyrics_body")
    private String lyricsBody;

    public String getLyricsBody() {
        return lyricsBody;
    }
}
