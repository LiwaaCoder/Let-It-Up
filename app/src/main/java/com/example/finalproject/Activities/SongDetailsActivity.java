package com.example.finalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.finalproject.MyUtils.ImageUtils;
import com.example.finalproject.R;

public class SongDetailsActivity extends AppCompatActivity
{

    private AppCompatTextView  details_TXT_name;
    private AppCompatImageView details_TXT_uri;
    private AppCompatTextView  details_TXT_desc;
    private ImageUtils util;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);


        details_TXT_name = findViewById(R.id.details_TXT_name);
        details_TXT_uri = findViewById(R.id.details_IMG_uri);
        details_TXT_desc = findViewById(R.id.details_TXT_desc);

        Intent ints = getIntent();
        String nameT = ints.getStringExtra("NAME:");
        String desT = ints.getStringExtra("DESCRI:");
        String imgT = ints.getStringExtra("IMGURI:");

        details_TXT_name.setText(nameT);
        details_TXT_desc.setText(desT);
        util.loadImage(details_TXT_uri,imgT);
    }
}
