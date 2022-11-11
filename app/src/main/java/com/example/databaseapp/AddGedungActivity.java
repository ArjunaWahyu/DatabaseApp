package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.databaseapp.entity.AppDatabase;
import com.example.databaseapp.entity.Gedung;
import com.example.databaseapp.entity.Ruangan;

import java.util.List;

public class AddGedungActivity extends AppCompatActivity {
    private EditText etNamaGedung;
    private Button btnAddGedung;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gedung);

//        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Database").build();
        appDatabase = AppDatabase.getDatabase(getApplicationContext());
        etNamaGedung = findViewById(R.id.etNamaGedung);
        btnAddGedung = findViewById(R.id.btnAddGedung);

        btnAddGedung.setOnClickListener(v -> {
            addGedung();
            finish();
        });
    }

    private void addGedung() {
        String namaGedung = etNamaGedung.getText().toString();

        AsyncTask.execute(() -> {
            appDatabase.gedungDAO().insertGedung(new Gedung(namaGedung));
        });
    }
}