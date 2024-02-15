package com.example.android_lesson_7;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView txtView;

    public MyViewHolder(View view) {
        super(view);
        txtView = view.findViewById(R.id.userNameTxxt);
    }

    public TextView getTxtView() {
        return txtView;
    }

    public void setTxtView(TextView txtView) {
        this.txtView = txtView;
    }
}
