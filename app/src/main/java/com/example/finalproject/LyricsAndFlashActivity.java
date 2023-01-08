package com.example.finalproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LyricsAndFlashActivity extends AppCompatActivity
{

    private ImageView imageView;
    private TextView textView;
    private Button button;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_flash);

        imageView = findViewById(R.id.image_view);
        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.button);

        // Load the flashing lights animation
        animation = AnimationUtils.loadAnimation(this, R.anim.blink);

        // Set up the button click listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the flashing lights animation
                imageView.startAnimation(animation);

                // Retrieve the lyrics from the API
                new LyricsAsyncTask().execute();
            }
        });
    }

    private class LyricsAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            // Replace YOUR_API_KEY with your actual API key
            String apiKey = "43bb51ddb5f920a2d2eca058a2636d1b";

            // Replace YOUR_SONG_TITLE with the actual title of the song
            String songTitle = "AnotherLove";

            // Encode the song title for use in the URL
            String encodedSongTitle = null;
            try {
                encodedSongTitle = URLEncoder.encode(songTitle, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // Set up the API request URL
            String urlString = "https://api.musixmatch.com/ws/1.1/matcher.lyrics.get?" +
                    "q_track=" + encodedSongTitle +
                    "&apikey=" + apiKey;

            try {
                // Create a URL object from the URL string
                URL url = new URL(urlString);

                // Open a connection to the URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set the request method to GET
                connection.setRequestMethod("GET");

                // Set the connection timeout and read timeout to 10 seconds
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                // Connect to the URL
                connection.connect();

                // Check the response code
                int responseCode = connection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    // If the response code is not 200 (OK), return an empty string
                    return "";
                }

                // Read the response stream and return it as a string
                InputStream inputStream = connection.getInputStream();
                return readStream(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }


        @Override
        protected void onPostExecute(String lyrics) {
            // Display the lyrics in the TextView
            textView.setText(lyrics);
        }
    }

    // Method to read the API response stream and return it as a string
    private String readStream(InputStream stream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder result = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}