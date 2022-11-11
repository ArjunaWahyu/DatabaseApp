package com.example.databaseapp.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GedungDAO {

    @Query("SELECT * FROM Gedung")
    List<Gedung> getAllGedung();

    @Insert
    void insertAll(List<Gedung> gedung);

    @Insert
    void insertGedung(Gedung gedung);

    @Delete
    void delete(Gedung gedung);

    @Query("DELETE FROM Gedung")
    void deleteAllGedung();
}
