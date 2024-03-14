package com.example.android_lesson_7.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android_lesson_7.dao.CharacterDao;
import com.example.android_lesson_7.entity.Character;

@Database(entities = {Character.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
}
