package com.example.databaseapp.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Gedung {

    @NonNull
    @PrimaryKey
    private String namaGedung;

    public Gedung(String namaGedung) {
        this.namaGedung = namaGedung;
    }

    public String getNamaGedung() {
        return namaGedung;
    }

    public void setNamaGedung(String namaGedung) {
        this.namaGedung = namaGedung;
    }
}