package com.example.finalproject.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.R;
import com.google.android.material.button.MaterialButton;


public class NavigateActivity extends AppCompatActivity
{
    private MaterialButton btnViewAll;
    private MaterialButton btnViewlyrics;
    private MaterialButton btnViewbrightness;
    private MaterialButton btnViewSuggest;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        btnViewAll = findViewById(R.id.navigate_BTN_ViewSchedule);
        btnViewlyrics = findViewById(R.id.navigate_BTN_Lyrics);
        btnViewbrightness = findViewById(R.id.navigate_BTN_Brightness);
        btnViewSuggest = findViewById(R.id.navigate_BTN_suggest);

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigateActivity.this, ScheduleActivity.class));
            }
        });

        btnViewlyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigateActivity.this, LyricsAndFlashActivity.class));
            }
        });

        btnViewbrightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigateActivity.this, BrightnessActivity.class));
            }
        });

        btnViewSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigateActivity.this, PickSongActivity.class));
            }
        });
    }
}
