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
import com.example.databaseapp.adapter.RuanganAdapter;
import com.example.databaseapp.entity.AppDatabase;
import com.example.databaseapp.entity.Gedung;
import com.example.databaseapp.entity.Ruangan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListRuangan extends AppCompatActivity {
    private RecyclerView rvRuangan;
    private FloatingActionButton btnAddRuangan;
    private List<Ruangan> ruangans;
    private AppDatabase appDatabase;
    private String namaGedung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ruangan);

        rvRuangan = findViewById(R.id.rvRuangan);
        btnAddRuangan = findViewById(R.id.btnAddRuangan);
//        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Database").build();
        appDatabase = AppDatabase.getDatabase(getApplicationContext());
//        setRvRuangan();

        namaGedung = getIntent().getStringExtra("namaGedung");
        Toast.makeText(this, "ListRuangan : "+namaGedung, Toast.LENGTH_SHORT).show();

        btnAddRuangan.setOnClickListener(v -> {
            Intent intent = new Intent(ListRuangan.this, AddRuanganActivity.class);
            intent.putExtra("namaGedung", namaGedung);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setRvRuangan();
    }

    private void setRvRuangan() {
        List<Ruangan> ruangans = getAllRuangan(namaGedung);
//        Toast.makeText(this, ruangans.toString(), Toast.LENGTH_SHORT).show();
//        rvRuangan.setAdapter(new RuanganAdapter(ruangans,
//                position -> {
//                    Intent intent = new Intent(ListRuangan.this, AddRuanganActivity.class);
//                    intent.putExtra("namaRuangan", ruangans.get(position).getNamaRuang());
//                    startActivity(intent);
//                },
//                position -> {
//                    deleteRuangan(ruangans.get(position));
//                    setRvRuangan();
//                },
//                position -> {
//                    Intent intent = new Intent(ListRuangan.this, ListRuangan.class);
//                    intent.putExtra("namaGedung", ruangans.get(position).getNamaGedung());
//                    startActivity(intent);
//                })
//        );
//        rvRuangan.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Ruangan> getAllRuangan(String namaGedung) {
        List<Ruangan> ruanganList = new ArrayList<>();
        AsyncTask.execute(() -> {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ruanganList.addAll(appDatabase.ruanganDAO().getAllRuangan());
                Toast.makeText(getApplicationContext(), ruanganList.toString(), Toast.LENGTH_SHORT).show();
//            }
        });
        return ruanganList;
    }

    private void deleteRuangan(Ruangan ruangan) {
        AsyncTask.execute(() -> {
            appDatabase.ruanganDAO().delete(ruangan);
        });
    }
}