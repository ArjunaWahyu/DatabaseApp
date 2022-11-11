package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databaseapp.entity.AppDatabase;
import com.example.databaseapp.entity.Ruangan;

public class AddRuanganActivity extends AppCompatActivity {

    private EditText etNamaRuangan;
    private EditText etKapasitas;
    private Button btnAddRuangan;
    private AppDatabase appDatabase;
    private String namaGedung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ruangan);

//        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Database").build();
        appDatabase = AppDatabase.getDatabase(getApplicationContext());
        etNamaRuangan = findViewById(R.id.etNamaRuangan);
        etKapasitas = findViewById(R.id.etKapasitas);
        btnAddRuangan = findViewById(R.id.btnAddRuangan);

        namaGedung = getIntent().getStringExtra("namaGedung");

        btnAddRuangan.setOnClickListener(v -> {
            addRuangan();
            finish();
        });
    }

    private void addRuangan() {
        String namaRuangan = etNamaRuangan.getText().toString();
        int kapasitas = Integer.parseInt(etKapasitas.getText().toString());
        Toast.makeText(this, namaGedung, Toast.LENGTH_SHORT).show();

        AsyncTask.execute(() -> {
            appDatabase.ruanganDAO().insertRuangan(new Ruangan(namaRuangan, kapasitas, namaGedung));
        });
    }
}