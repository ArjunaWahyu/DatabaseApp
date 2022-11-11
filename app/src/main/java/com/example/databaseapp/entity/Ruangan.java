package com.example.databaseapp.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ruangan {
    @NonNull
    @PrimaryKey
    private String namaRuang;
    @ColumnInfo(name = "kapasitas")
    private int kapasitas;
    @ColumnInfo(name = "namaGedung")
    private String namaGedung;

    public Ruangan(String namaRuang, int kapasitas, String namaGedung) {
        this.namaRuang = namaRuang;
        this.kapasitas = kapasitas;
        this.namaGedung = namaGedung;
    }

    public String getNamaRuang() {
        return namaRuang;
    }

    public void setNamaRuang(String namaRuang) {
        this.namaRuang = namaRuang;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getNamaGedung() {
        return namaGedung;
    }

    public void setNamaGedung(String namaGedung) {
        this.namaGedung = namaGedung;
    }
}
