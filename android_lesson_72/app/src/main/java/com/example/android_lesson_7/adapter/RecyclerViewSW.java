package com.example.android_lesson_7.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_lesson_7.R;
import com.example.android_lesson_7.model.SWCharacter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewSW extends RecyclerView.Adapter<ViewHolderSW> {

    private List<SWCharacter> swCharacterList;

    public RecyclerViewSW(List<SWCharacter> swCharacterList) {
        this.swCharacterList = swCharacterList;
    }

    @NonNull
    @Override
    public ViewHolderSW onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_info_layout, parent, false);
        return new ViewHolderSW(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSW holder, int position) {
        SWCharacter swCharacter = swCharacterList.get(position);
        holder.txtName.setText(swCharacter.getName());
        holder.txtHairColor.setText(swCharacter.getHairColor());
        holder.txtEyeColor.setText(swCharacter.getEyeColor());
        holder.txtSkinColor.setText(swCharacter.getSkinColor());
        holder.txtYob.setText(swCharacter.getBirthYear());
        holder.txtMass.setText(String.valueOf(swCharacter.getMass()));
        holder.txtHeight.setText(String.valueOf(swCharacter.getHeight()));
        holder.txtGender.setText(swCharacter.getGender());
    }

    @Override
    public int getItemCount() {
        return swCharacterList.size();
    }
}
