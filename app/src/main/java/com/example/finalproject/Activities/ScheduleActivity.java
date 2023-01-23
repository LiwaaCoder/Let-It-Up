package com.example.finalproject.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Adapters.ListAdapter;
import com.example.finalproject.Adapters.Song;
import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity
{

    private RecyclerView mRecyclerView;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Song> mSongs = new ArrayList<>();
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        // set adapter
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ListAdapter(this,mSongs);
        mRecyclerView.setAdapter(listAdapter);

        // set Firebase Database
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onCancelled(DatabaseError error)
            {
                Toast.makeText(ScheduleActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDataChange(DataSnapshot snapshot)
            {
                mSongs.clear();
                for (DataSnapshot teacherSnapshot : snapshot.getChildren()) {
                    Song upload = teacherSnapshot.getValue(Song.class);
                    upload.setKey(teacherSnapshot.getKey());
                    mSongs.add(upload);
                }
                (listAdapter).notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}
