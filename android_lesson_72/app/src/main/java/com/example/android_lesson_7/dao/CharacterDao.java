package com.example.android_lesson_7.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android_lesson_7.entity.Character;

import java.util.List;

@Dao
public interface CharacterDao {
    @Insert
    void insertCharacter(Character character);

    @Query(value = "SELECT * FROM character;")
    List<Character> findAllCharacters();

    @Query(value = "SELECT * FROM character WHERE name = :name and birth_year = :dob;")
    List<Character> findByNameAndDob(String name, String dob);
}
