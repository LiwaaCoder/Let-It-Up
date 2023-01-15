package com.example.finalproject.MyUtils;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;

public class ImageUtils
{
    public static void loadImage(AppCompatImageView view, String url)
    {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }
}
