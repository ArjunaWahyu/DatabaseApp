package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.databaseapp.adapter.GedungAdapter;
import com.example.databaseapp.entity.AppDatabase;
import com.example.databaseapp.entity.Gedung;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvGedung;
    private FloatingActionButton btnAddGedung;
    private List<Gedung> gedungs;
    private AppDatabase appDatabase;
    private GedungAdapter gedungAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvGedung = findViewById(R.id.rvGedung);
        btnAddGedung = findViewById(R.id.btnAddGedung);
//        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Database").build();
        appDatabase = AppDatabase.getDatabase(getApplicationContext());

        btnAddGedung.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddGedungActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setRvGedung();
    }

    private void setRvGedung() {
        gedungs = getAllGedung();
        gedungAdapter = new GedungAdapter(gedungs, position -> {
            Intent intent = new Intent(MainActivity.this, ListRuangan.class);
            intent.putExtra("namaGedung", gedungs.get(position).getNamaGedung());
            startActivity(intent);
        }, position -> {
            deleteGedung(gedungs.get(position));
            setRvGedung();
        });
        rvGedung.setAdapter(gedungAdapter);
        rvGedung.setLayoutManager(new LinearLayoutManager(this));
    }

    public List<Gedung> getAllGedung() {
        List<Gedung> gedungs = new ArrayList<>();

        AsyncTask.execute(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                gedungs.addAll(appDatabase.gedungDAO().getAllGedung());
            }
        });

        return gedungs;
    }

    private void deleteGedung(Gedung gedung) {
        AsyncTask.execute(() -> {
            appDatabase.gedungDAO().delete(gedung);
        });
    }
}