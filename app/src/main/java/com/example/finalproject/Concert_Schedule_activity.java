package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class Concert_Schedule_activity extends AppCompatActivity
{
    private RecyclerView main_LST_songs;
    private MaterialButton conecrt_BTN_lyrics;
    private MaterialButton conecrt_BTN_bright;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_concert_schedule);


            main_LST_songs = findViewById(R.id.main_LST_songs);
            conecrt_BTN_lyrics = findViewById(R.id.concert_BTN_lyrics);
            conecrt_BTN_bright=findViewById(R.id.concert_BTN_bright);

            ArrayList<Song> songs = DataManager.getSongs();

            songs.get(10).setFavorite(true);
            songs.get(8).setFavorite(true);

            Adapter_Song adapter_song = new Adapter_Song(this, songs);
            main_LST_songs.setLayoutManager(new LinearLayoutManager(this));
            main_LST_songs.setAdapter(adapter_song);


            conecrt_BTN_lyrics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(Concert_Schedule_activity.this, LyricsAndFlashActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        conecrt_BTN_bright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Concert_Schedule_activity.this, BrightnessActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }

}