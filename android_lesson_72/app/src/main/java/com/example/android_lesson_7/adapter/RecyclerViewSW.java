package com.example.android_lesson_7.adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_lesson_7.R;
import com.example.android_lesson_7.dao.CharacterDao;
import com.example.android_lesson_7.db.AppDatabase;
import com.example.android_lesson_7.entity.Character;
import com.example.android_lesson_7.model.SWCharacter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewSW extends RecyclerView.Adapter<ViewHolderSW> {

    private List<SWCharacter> swCharacterList;
    private AppDatabase appDatabase;

    public RecyclerViewSW(List<SWCharacter> swCharacterList, AppDatabase appDatabase) {
        this.swCharacterList = swCharacterList;
        this.appDatabase = appDatabase;
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
        SWCharacter swCharacter = swCharacterList.get(position);
        holder.imageView.setImageResource(R.mipmap.ic_launcher);
        if (swCharacter.getName().equals("Chewbacca")) {
            holder.imageView.setImageResource(R.drawable.chubaka);
        }
        holder.txtName.setText(swCharacter.getName());
        holder.txtHairColor.setText(swCharacter.getHairColor());
        holder.txtEyeColor.setText(swCharacter.getEyeColor());
        holder.txtSkinColor.setText(swCharacter.getSkinColor());
        holder.txtYob.setText(swCharacter.getBirthYear());
        holder.txtMass.setText(String.valueOf(swCharacter.getMass()));
        holder.txtHeight.setText(String.valueOf(swCharacter.getHeight()));
        holder.txtGender.setText(swCharacter.getGender());

        holder.saveButton.setOnClickListener(v -> {
            Log.w(TAG, "BUTTON SAVE IS CLICKED FOR " + swCharacter.getName());
            insertCharacter(swCharacter);
        });
    }

    private void insertCharacter(SWCharacter swCharacter) {
        CharacterDao characterDao = appDatabase.characterDao();
        List<Character> existingCharacters = characterDao.findByNameAndDob(swCharacter.getName(), swCharacter.getBirthYear());
        if (existingCharacters.size() > 0) {
            Log.w(TAG, String.format("Character %s already saved", swCharacter.getName()));
            return;
        }
        Character characterToInsert = new Character(swCharacter.getName(),
                Integer.parseInt(swCharacter.getHeight()),
                Integer.parseInt(swCharacter.getMass()),
                swCharacter.getHairColor(),
                swCharacter.getSkinColor(),
                swCharacter.getEyeColor(),
                swCharacter.getBirthYear(),
                swCharacter.getGender()
                );
        characterDao.insertCharacter(characterToInsert);
    }

    @Override
    public int getItemCount() {
        return swCharacterList.size();
    }
}
