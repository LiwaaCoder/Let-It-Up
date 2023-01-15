package com.example.finalproject.Activities;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ScrollView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.finalproject.LyricsAPI.LyricsData;
import com.example.finalproject.LyricsAPI.MusixMatchAPI;
import com.example.finalproject.R;
import com.google.firebase.storage.OnPausedListener;

import java.util.Random;

public class LyricsAndFlashActivity extends AppCompatActivity{
    private AppCompatTextView lyricsView;
    private  ScrollView sc;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashOn = false;
    private Random rand = new Random();
    private final Handler flashHandler = new Handler();
    boolean IsActive=true;
    private Runnable flashRunnable;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_flash);
        lyricsView = findViewById(R.id.lyrics_view);
        sc = findViewById(R.id.lyrics_TXT_scrolling);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        //MusixMatch Section

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.musixmatch.com/ws/1.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MusixMatchAPI musixmatchAPI = retrofit.create(MusixMatchAPI.class);


        musixmatchAPI.getLyrics("AnotherLove", "TomOdell", "43bb51ddb5f920a2d2eca058a2636d1b")
                .enqueue(new Callback<LyricsData>() {
                    @Override
                    public void onResponse(Call<LyricsData> call, Response<LyricsData> response) {
                        if (response.body() != null && response.body().getLyrics() != null) {
                            lyricsView.setText(response.body().getLyrics().getLyricsBody());
                            //Log.d("pttt", response.body().getLyrics().getLyricsBody());


                            final int lines = lyricsView.getLineCount();
                            final int duration = lines * 100;
                            final Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (sc.getChildAt(0).getBottom() <= (sc.getHeight() + sc.getScrollY())) {
                                        h.removeCallbacks(this);
                                    } else {
                                        sc.fullScroll(View.FOCUS_DOWN);
                                        h.postDelayed(this, duration);
                                    }
                                }
                            }, 500);


                        }

                    }

                    @Override
                    public void onFailure(Call<LyricsData> call, Throwable t) {
                        Log.e("ptt", t.getMessage());
                    }


                });

        if (IsActive)
        {

            // Flash Section :
            if (cameraManager != null) {
                try {
                    cameraManager.setTorchMode(cameraId, true);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (cameraManager.getCameraCharacteristics(cameraId).get(CameraCharacteristics.FLASH_INFO_AVAILABLE)) {
                    cameraManager.setTorchMode(cameraId, true);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

            // Turn flash on or off randomly
            flashRunnable = new Runnable() {
                public void run() {
                    if (IsActive) {
                        int randomNum = rand.nextInt(2);
                        try {
                            if (randomNum == 0) {
                                cameraManager.setTorchMode(cameraId, false);
                                isFlashOn = false;
                            } else {
                                cameraManager.setTorchMode(cameraId, true);
                                isFlashOn = true;
                            }
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        flashHandler.postDelayed(this, 200);
                    }
                }
            };
            flashHandler.postDelayed(flashRunnable, 200);
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        if(IsActive) {
            flashHandler.removeCallbacks(flashRunnable);

            try {
                cameraManager.setTorchMode(cameraId, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        if(IsActive)
        {
            flashHandler.removeCallbacks(flashRunnable);

            try {
                cameraManager.setTorchMode(cameraId, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
