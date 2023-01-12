package com.example.finalproject;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Random;

public class LyricsAndFlashActivity extends AppCompatActivity{
    private AppCompatTextView lyricsView;
    private  ScrollView sc;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashOn = false;
    private Random rand = new Random();
    private final Handler flashHandler = new Handler();


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


        musixmatchAPI.getLyrics("Yellow", "Coldplay" ,  "43bb51ddb5f920a2d2eca058a2636d1b")
                .enqueue(new Callback<LyricsData>()
                {
                    @Override
                    public void onResponse(Call<LyricsData> call, Response<LyricsData> response)
                    {
                        if (response.body()!=null && response.body().getLyrics() !=null && response.isSuccessful())
                        {
                            lyricsView.setText(response.body().getLyrics().getLyricsBody());
                            Log.d("pttt", response.body().getLyrics().getLyricsBody());


                            // start the scrolling
                            final Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    sc.fullScroll(View.FOCUS_DOWN);
                                    h.postDelayed(this, 250);
                                }
                            }, 250);



                        }
                    }
                    @Override
                    public void onFailure(Call<LyricsData> call, Throwable t)
                    {
                        //Handle the Error
                        Log.e("ptt", t.getMessage());
                    }
                });



        // Flash Section :

        if(cameraManager != null)
        {
            try {
                cameraManager.setTorchMode(cameraId, true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        try {
            if (cameraManager.getCameraCharacteristics(cameraId).get(CameraCharacteristics.FLASH_INFO_AVAILABLE))
            {
                cameraManager.setTorchMode(cameraId, true);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


        // Turn flash on or off randomly every 5 seconds
        Runnable flashRunnable = new Runnable()
        {
            public void run()
            {
                int randomNum = rand.nextInt(2);
                try
                {

                    if (randomNum == 0)
                    {
                        cameraManager.setTorchMode(cameraId, false);
                        isFlashOn = false;
                    } else {
                        cameraManager.setTorchMode(cameraId, true);
                        isFlashOn = true;
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                flashHandler.postDelayed(this, 350);
            }
        };
        flashHandler.postDelayed(flashRunnable, 350);
    }
}
