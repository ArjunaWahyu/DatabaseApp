package com.example.databaseapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databaseapp.entity.AppDatabase;
import com.example.databaseapp.entity.Ruangan;

public class EditRuanganActivity extends AppCompatActivity {
    private EditText etNamaRuangan, etKapasitas;
    private Button btnUpdate;
    private AppDatabase appDatabase;
    String namaGedung, namaRuangan;
    int kapasitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ruangan);

        appDatabase = AppDatabase.getDatabase(getApplicationContext());
        etNamaRuangan = findViewById(R.id.etNamaRuangan);
        etKapasitas = findViewById(R.id.etKapasitas);
        btnUpdate = findViewById(R.id.btnUpdateRuangan);

        namaRuangan = getIntent().getStringExtra("namaRuangan");
        kapasitas = getIntent().getIntExtra("kapasitas", 0);
        namaGedung = getIntent().getStringExtra("namaGedung");

        etNamaRuangan.setText(namaRuangan);
        etKapasitas.setText(String.valueOf(kapasitas));

        btnUpdate.setOnClickListener(v -> {
            updateRuangan();
        });
    }

    private void updateRuangan() {
//        namaRuangan = etNamaRuangan.getText().toString();
        kapasitas = Integer.parseInt(etKapasitas.getText().toString());

        AsyncTask.execute(() -> {
            appDatabase.ruanganDAO().updateRuangan(new Ruangan(namaRuangan, kapasitas, namaGedung));
        });
        finish();
    }
}