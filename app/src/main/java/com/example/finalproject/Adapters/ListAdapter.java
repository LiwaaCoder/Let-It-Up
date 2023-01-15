package com.example.finalproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.finalproject.Activities.SongDetailsActivity;
import com.example.finalproject.MyUtils.ImageUtils;
import com.example.finalproject.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>
{

    private Context mContext;
    private List<Song> teacherList;
    private ImageUtils util;

    public class ListViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgT;
        TextView nameT;
        TextView descriT;
        View v;

        public ListViewHolder(View v) {
            super(v);
            this.v = v;
            imgT = v.findViewById(R.id.teacherImageView);
            nameT = v.findViewById(R.id.nameTextView);
            descriT = v.findViewById(R.id.descriptionTextView);
        }
    }

        public ListAdapter(Context mContext, List<Song> teacherList) {
        this.mContext = mContext;
        this.teacherList = teacherList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.rowitem, parent, false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Song newList = teacherList.get(position);
        holder.nameT.setText(newList.getName());
        holder.descriT.setText(newList.getDescription());
        util.loadImage((AppCompatImageView) holder.imgT, newList.getImageUrl());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newList.getName();
                String descrip = newList.getDescription();
                String imgUri = newList.getImageUrl();

                Intent mIntent = new Intent(mContext, SongDetailsActivity.class);
                mIntent.putExtra("NAMET", name);
                mIntent.putExtra("DESCRIT", descrip);
                mIntent.putExtra("IMGURI", imgUri);
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

}
