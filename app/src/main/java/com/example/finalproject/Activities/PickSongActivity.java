package com.example.finalproject.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.finalproject.Adapters.Song;
import com.example.finalproject.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class PickSongActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;


    // Initializing the ImageView
    ShapeableImageView pick_IMG_song1;
    ShapeableImageView pick_IMG_song2;
    ShapeableImageView pick_IMG_song3;


    private void incrementPictureCount(int pictureId) {
        int count = sharedPreferences.getInt(String.valueOf(pictureId), 0);
        count++;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(String.valueOf(pictureId), count);
        editor.apply();
        Log.d("PickSongActivity", "picture id:" + pictureId + " count:" + count);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_song);
        sharedPreferences = getPreferences(MODE_PRIVATE);


        // getting ImageView by its id
        pick_IMG_song1 = findViewById(R.id.rImage);
        pick_IMG_song2 = findViewById(R.id.c_Image);
        pick_IMG_song3 = findViewById(R.id.l_Image);


        // we will get the default FirebaseDatabase instance
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        // we will get a DatabaseReference for the database
        // root node
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        // Here "image" is the child node value we are
        // getting child node data in the getImage variable
        DatabaseReference getImage = databaseReference.child("SkyFullOfStars");

        DatabaseReference getImage2 = databaseReference.child("Paradise");

        DatabaseReference getImage3 = databaseReference.child("vivalavida");


        // Adding listener for a single change
        // in the data at this location.
        // this listener will triggered once
        // with the value of the data at the location
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);

                Picasso.get().load(link).into(pick_IMG_song1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(PickSongActivity.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });


        getImage2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);

                Picasso.get().load(link).into(pick_IMG_song2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(PickSongActivity.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });


        getImage3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);

                Picasso.get().load(link).into(pick_IMG_song3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(PickSongActivity.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });

        pick_IMG_song1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String SongName = "SkyFullofstars";
                incrementPictureCount(R.id.rImage);
                Intent intent = new Intent(PickSongActivity.this, LyricsAndFlashActivity.class);
                intent.putExtra("song_name", SongName);
                startActivity(intent);

            }
        });

        pick_IMG_song2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementPictureCount(R.id.c_Image);

            }
        });

        pick_IMG_song3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementPictureCount(R.id.l_Image);
            }
        });

    }
}



