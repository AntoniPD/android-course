package com.example.android_lesson_7.adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_lesson_7.R;
import com.example.android_lesson_7.dao.CharacterDao;
import com.example.android_lesson_7.db.AppDatabase;
import com.example.android_lesson_7.entity.Character;
import com.example.android_lesson_7.model.SWCharacter;

import java.util.List;

public class RecyclerViewCharacter extends RecyclerView.Adapter<ViewHolderSW> {

    private List<Character> characters;
    public RecyclerViewCharacter(List<Character> characters) {
        this.characters = characters;
    }

    @NonNull
    @Override
    public ViewHolderSW onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_info_layout, parent, false);
        return new ViewHolderSW(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSW holder, int position) {
        Character character = characters.get(position);
        holder.imageView.setImageResource(R.mipmap.ic_launcher);
        if (character.getName().equals("Chewbacca")) {
            holder.imageView.setImageResource(R.drawable.chubaka);
        }
        holder.txtName.setText(character.getName());
        holder.txtHairColor.setText(character.getHairColor());
        holder.txtEyeColor.setText(character.getEyeColor());
        holder.txtSkinColor.setText(character.getSkinColor());
        holder.txtYob.setText(character.getBirthYear());
        holder.txtMass.setText(String.valueOf(character.getMass()));
        holder.txtHeight.setText(String.valueOf(character.getHeight()));
        holder.txtGender.setText(character.getGender());
        holder.saveButton.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }
}
