package com.example.databaseapp.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RuanganDAO {
    @Query("SELECT * FROM Ruangan")
    List<Ruangan> getAllRuangan();

    @Query("SELECT * FROM Ruangan WHERE namaGedung = :namaGedung")
    List<Ruangan> getAllRuanganByNamaGedung(String namaGedung);

    @Insert
    void insertAll(List<Ruangan> ruangan);

    @Insert
    void insertRuangan(Ruangan ruangan);

    @Update
    void updateRuangan(Ruangan ruangan);

    @Delete
    void delete(Ruangan ruangan);

    @Query("DELETE FROM Ruangan WHERE namaGedung = :namaGedung")
    void deleteByGedung(String namaGedung);

    @Query("DELETE FROM Ruangan")
    void deleteAllRuangan();
}
