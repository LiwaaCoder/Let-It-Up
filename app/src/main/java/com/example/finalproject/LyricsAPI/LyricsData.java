package com.example.finalproject.LyricsAPI;


import com.google.gson.annotations.SerializedName;


public class LyricsData {
    @SerializedName("message")
    private Message message;

    public Lyrics getLyrics() {
        return message.getBody().getLyrics();
    }
}


