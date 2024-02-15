package com.example.android_lesson_7.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_lesson_7.R;

public class ViewHolderSW extends RecyclerView.ViewHolder {
    public TextView txtName;
    public TextView txtHeight;
    public TextView txtMass;
    public TextView txtHairColor;
    public TextView txtSkinColor;
    public TextView txtEyeColor;
    public TextView txtYob;
    public TextView txtGender;

    public ViewHolderSW(@NonNull View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        txtMass = itemView.findViewById(R.id.txtMass);
        txtHairColor = itemView.findViewById(R.id.txtHairColor);
        txtSkinColor = itemView.findViewById(R.id.txtSkinColor);
        txtEyeColor = itemView.findViewById(R.id.txtEyeColor);
        txtYob = itemView.findViewById(R.id.txtYob);
        txtGender = itemView.findViewById(R.id.txtGender);
        txtHeight = itemView.findViewById(R.id.txtHeight);
    }

    public TextView getTxtName() {
        return txtName;
    }

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    public TextView getTxtHeight() {
        return txtHeight;
    }

    public void setTxtHeight(TextView txtHeight) {
        this.txtHeight = txtHeight;
    }

    public TextView getTxtMass() {
        return txtMass;
    }

    public void setTxtMass(TextView txtMass) {
        this.txtMass = txtMass;
    }

    public TextView getTxtHairColor() {
        return txtHairColor;
    }

    public void setTxtHairColor(TextView txtHairColor) {
        this.txtHairColor = txtHairColor;
    }

    public TextView getTxtSkinColor() {
        return txtSkinColor;
    }

    public void setTxtSkinColor(TextView txtSkinColor) {
        this.txtSkinColor = txtSkinColor;
    }

    public TextView getTxtEyeColor() {
        return txtEyeColor;
    }

    public void setTxtEyeColor(TextView txtEyeColor) {
        this.txtEyeColor = txtEyeColor;
    }

    public TextView getTxtYob() {
        return txtYob;
    }

    public void setTxtYob(TextView txtYob) {
        this.txtYob = txtYob;
    }

    public TextView getTxtGender() {
        return txtGender;
    }

    public void setTxtGender(TextView txtGender) {
        this.txtGender = txtGender;
    }
}
