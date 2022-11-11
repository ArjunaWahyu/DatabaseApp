package com.example.databaseapp.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.databaseapp.entity.Gedung;
import com.example.databaseapp.entity.GedungDAO;

@Database(entities = {Gedung.class, Ruangan.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GedungDAO gedungDAO();
    public abstract RuanganDAO ruanganDAO();

    private static AppDatabase appDatabase;

    public static AppDatabase getDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "Database").build();
        }
        return appDatabase;
    }

    public static void destroyInstance() {
        appDatabase = null;
    }
}
