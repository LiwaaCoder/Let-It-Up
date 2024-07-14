package com.example.finalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.finalproject.R;
import com.google.android.material.button.MaterialButton;
import com.example.insightanalytics.AvgActivityTime;

public class NavigateActivity extends AppCompatActivity {
    private MaterialButton btnViewLyrics;
    private MaterialButton btnViewBrightness;
    private MaterialButton btnViewSuggest;
    private AvgActivityTime avgActivityTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        initializeUI();
        initializeAnalytics();
        setupListeners();
    }

    private void initializeUI() {
        btnViewLyrics = findViewById(R.id.navigate_BTN_Lyrics);
        btnViewBrightness = findViewById(R.id.navigate_BTN_Brightness);
        btnViewSuggest = findViewById(R.id.navigate_BTN_suggest);
    }

    private void initializeAnalytics() {
        avgActivityTime = AvgActivityTime.getInstance();
        avgActivityTime.initializeFirebase(this, "yourAppId", "yourAppVersion"); // Replace with your actual appId and appVersion
    }

    private void setupListeners() {
        btnViewLyrics.setOnClickListener(view -> navigateToActivity(LyricsAndFlashActivity.class, "View Lyrics"));
        btnViewBrightness.setOnClickListener(view -> navigateToActivity(BrightnessActivity.class, "View Brightness"));
        btnViewSuggest.setOnClickListener(view -> navigateToActivity(PickSongActivity.class, "View Suggest"));
    }

    private void navigateToActivity(Class<?> activityClass, String activityName) {
        avgActivityTime.startActivity(activityName);
        startActivity(new Intent(NavigateActivity.this, activityClass));
        avgActivityTime.endActivity(activityName);
    }
}
