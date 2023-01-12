package com.example.finalproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusixMatchAPI
{

    @GET("matcher.lyrics.get")
    Call<LyricsData> getLyrics(@Query("q_track") String track, @Query("q_artist") String artist, @Query("apikey") String apiKey);
}
