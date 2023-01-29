package com.example.finalproject.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.finalproject.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private void displayPicturesSortedByCount()
    {
        List<String> pictures = new ArrayList<>();
        pictures.add("SkyFullOfStars");
        pictures.add("Paradise");
        pictures.add("vivalavida");


        Collections.sort(pictures, (picture1, picture2) -> {
            int count1 = sharedPreferences.getInt(picture1, 0);
            int count2 = sharedPreferences.getInt(picture2, 0);
            return Integer.compare(count2, count1);
        });

        for (String picture : pictures) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference();
            DatabaseReference getImage = databaseReference.child(picture);


            getImage.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String link = dataSnapshot.getValue(String.class);
                    ImageView imageView;
                    if (picture.equals(pictures.get(0))) {
                        imageView = pick_IMG_song1;
                    } else if (picture.equals(pictures.get(1))) {
                        imageView = pick_IMG_song2;
                    } else {
                        imageView = pick_IMG_song3;
                    }
                    imageView.setVisibility(View.VISIBLE);
                    Picasso.get().load(link).into(imageView);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(PickSongActivity.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
                }
            });
        }

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

        displayPicturesSortedByCount();


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
            public void onClick(View v) {
                incrementPictureCount(R.id.rImage);

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



