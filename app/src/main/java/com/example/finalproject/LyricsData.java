package com.example.finalproject;


import com.google.gson.annotations.SerializedName;


public class LyricsData {
    @SerializedName("message")
    private Message message;

    public Lyrics getLyrics() {
        return message.getBody().getLyrics();
    }
}


