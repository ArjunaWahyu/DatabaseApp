package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.example.databaseapp.adapter.RuanganAdapter;
import com.example.databaseapp.entity.AppDatabase;
import com.example.databaseapp.entity.Ruangan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class ListRuangan extends AppCompatActivity {
    private RecyclerView rvRuangan;
    private FloatingActionButton btnAddRuangan;
    private AppDatabase appDatabase;
    private RuanganAdapter ruanganAdapter;
    private String namaGedung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ruangan);

        rvRuangan = findViewById(R.id.rvRuangan);
        btnAddRuangan = findViewById(R.id.btnAddRuangan);
        appDatabase = AppDatabase.getDatabase(getApplicationContext());

        namaGedung = getIntent().getStringExtra("namaGedung");

        btnAddRuangan.setOnClickListener(v -> {
            Intent intent = new Intent(ListRuangan.this, AddRuanganActivity.class);
            intent.putExtra("namaGedung", namaGedung);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllRuangan(namaGedung);
    }

    private void setRvRuangan(List<Ruangan> ruangans) {
        ruanganAdapter = new RuanganAdapter(ruangans,
                (position, view) -> {
                    PopupMenu popupMenu = new PopupMenu(this, view);
                    popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(item -> {
                        switch (item.getItemId()) {
                            case R.id.ivEdit:
                                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
                                editRuangan(ruangans.get(position));
                                break;
                            case R.id.ivDelete:
                                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                                deleteRuangan(ruangans.get(position));
                                ruangans.remove(position);
                                setRvRuangan(ruangans);
                                break;
                        }
                        return false;
                    });
                    popupMenu.show();
                });
        rvRuangan.setAdapter(ruanganAdapter);
        rvRuangan.setLayoutManager(new LinearLayoutManager(this));
    }

    private void editRuangan(Ruangan ruangan) {
        Intent intent = new Intent(ListRuangan.this, EditRuanganActivity.class);
        intent.putExtra("namaRuangan", ruangan.getNamaRuang());
        intent.putExtra("kapasitas", ruangan.getKapasitas());
        intent.putExtra("namaGedung", namaGedung);
        startActivity(intent);
    }

    private void getAllRuangan(String namaGedung) {
        List<Ruangan> ruangans = new ArrayList<>();
        AsyncTask.execute(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ruangans.addAll(appDatabase.ruanganDAO().getAllRuanganByNamaGedung(namaGedung));

                runOnUiThread(() -> {
                    setRvRuangan(ruangans);
                });

            }
        });
    }

    private void deleteRuangan(Ruangan ruangan) {
        AsyncTask.execute(() -> {
            appDatabase.ruanganDAO().delete(ruangan);
        });
    }
}