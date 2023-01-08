package com.example.finalproject;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Adapter_Song extends RecyclerView.Adapter<Adapter_Song.SongViewHolder>
{

    private Context context;
    private ArrayList<Song> songs;

    public Adapter_Song(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @Override
    public int getItemCount() {
        return songs == null ? 0 : songs.size();
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_song, parent, false);
        SongViewHolder mySongViewHolder = new SongViewHolder(view);
        return mySongViewHolder;
    }

    @Override
    public void onBindViewHolder(final SongViewHolder holder, final int position) {
        Log.d("pttt", "Pos= " + position);
        Song song = songs.get(position);

        holder.song_LBL_name.setText("" + position);
        holder.song_LBL_duration.setText( MyStringUtils.getTimeBySeconds(song.getDuration()));

        if (song.isFavorite()) {
            holder.itemView.setBackgroundColor(Color.RED);
        } else {
            holder.itemView.setBackgroundColor(Color.YELLOW);
        }



        final Handler handler = new Handler();
        final int delay = 20; // 1000 milliseconds == 1 second

        handler.postDelayed(new Runnable() {
            public void run() {
                MyImageUtils.getInstance().load(song.getImage(), holder.song_IMG_image);
            }
        }, delay);

    }

    class SongViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView song_LBL_likes;
        private MaterialTextView song_LBL_views;
        private MaterialTextView song_LBL_duration;
        private MaterialTextView song_LBL_name;
        private AppCompatImageView song_IMG_image;

        public SongViewHolder(View itemView) {
            super(itemView);
            song_LBL_likes = itemView.findViewById(R.id.song_LBL_likes);
            song_LBL_views = itemView.findViewById(R.id.song_LBL_views);
            song_LBL_duration = itemView.findViewById(R.id.song_LBL_duration);
            song_LBL_name = itemView.findViewById(R.id.song_LBL_name);
            song_IMG_image = itemView.findViewById(R.id.song_IMG_image);

        }

    }
}