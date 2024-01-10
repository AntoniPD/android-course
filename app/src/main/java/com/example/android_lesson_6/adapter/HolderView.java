package com.example.android_lesson_6.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_lesson_6.R;

public class HolderView {
    public final ImageView photoImage;
    public final TextView titleText;
    public final TextView contentText;

    public HolderView(View view) {
        this.photoImage = view.findViewById(R.id.userPhotoImg);
        this.titleText = view.findViewById(R.id.titleTextView);
        this.contentText = view.findViewById(R.id.cntTextView);
    }

}