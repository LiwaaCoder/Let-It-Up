package com.example.finalproject.Activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.finalproject.Callbacks.PermissionCallback;
import com.example.finalproject.R;
import com.google.android.material.button.MaterialButton;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BrightnessActivity extends AppCompatActivity implements PermissionCallback
{

    private static final int MY_PERMISSIONS_REQUEST_WRITE_SETTINGS = 1;
    private Timer timer;
    private MaterialButton bright_BTN_red;
    private MaterialButton bright_BTN_green;
    private MaterialButton bright_BTN_blue;
    private LinearLayout layout;
    private boolean permissionRequested = false;
    TimerTask brightnessTask;
    TimerTask colorTask;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);
        if (!preferences.getBoolean("permission_requested", false))
        {
            requestPermission();
            preferences.edit().putBoolean("permission_requested", true).apply();
        }

        bright_BTN_red = findViewById(R.id.button_red);
        bright_BTN_green = findViewById(R.id.button_green);
        bright_BTN_blue = findViewById(R.id.button_blue);
        layout = findViewById(R.id.activity_brightness_root);


        int currentBrightness = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1);


        brightnessTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        if (Settings.System.canWrite(BrightnessActivity.this)) {
                            setRandomBrightness(getContentResolver());
                        }
                    });
                }
            };
        colorTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        layout.setBackgroundColor(getRandomColor());
                    });
                }
            };
            Timer brightnessTimer = new Timer();
            brightnessTimer.scheduleAtFixedRate(brightnessTask, 0, 750);  // change the time here
            Timer colorTimer = new Timer();
            colorTimer.scheduleAtFixedRate(colorTask, 0, 750); // change the time here

            bright_BTN_red.setOnClickListener(v -> layout.setBackgroundColor(Color.RED));
            bright_BTN_green.setOnClickListener(v -> layout.setBackgroundColor(Color.GREEN));
            bright_BTN_blue.setOnClickListener(v -> layout.setBackgroundColor(Color.BLUE));
        }
    private int getRandomColor()
    {
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return Color.rgb(r, g, b);
    }

    public static void setRandomBrightness(ContentResolver contentResolver)
    {
        Random rand = new Random();
        int minBrightness = 15;
        int maxBrightness = 255;
        int brightness = rand.nextInt(maxBrightness - minBrightness + 1) + minBrightness;
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
    }


    @Override
    public void onPermissionGranted()
    {
        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionDenied() {
        // Notify user that permission is needed to perform certain actions
        Toast.makeText(this, "Permission denied, certain features may not work", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_SETTINGS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted();
            } else {
                onPermissionDenied();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_SETTINGS) {
            if (Settings.System.canWrite(this))
            {
                onPermissionGranted();
            } else {
                onPermissionDenied();
            }
        }
    }

    private void requestPermission()
    {
        if (!permissionRequested)
        {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            PackageManager packageManager = getPackageManager();
            if (intent.resolveActivity(packageManager) != null)
            {
                startActivity(intent);
            }
            permissionRequested = true;
        }
    }

    protected void onStop()
    {
        super.onStop();
        if(!permissionRequested) {
            brightnessTask.cancel();
            colorTask.cancel();
        }
    }

    protected void onPause()
    {
        super.onPause();
        if(!permissionRequested) {
            brightnessTask.cancel();
            colorTask.cancel();
        }
    }



}



