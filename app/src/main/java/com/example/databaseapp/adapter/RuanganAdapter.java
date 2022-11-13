package com.example.databaseapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseapp.R;
import com.example.databaseapp.entity.Ruangan;

import java.util.List;

public class RuanganAdapter extends RecyclerView.Adapter<RuanganAdapter.ViewHolder> {
    private List<Ruangan> ruangans;
    private OnClickListerner menuClickListerner;

    public RuanganAdapter(List<Ruangan> ruangans, OnClickListerner menuClickListerner) {
        this.ruangans = ruangans;
        this.menuClickListerner = menuClickListerner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout
        View photoView = inflater.inflate(R.layout.item_ruangan_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String namaRuangan = ruangans.get(position).getNamaRuang();
        int kapasitas = ruangans.get(position).getKapasitas();

        holder.tvNamaRuangan.setText(namaRuangan);
        holder.tvKapasitas.setText(String.valueOf(kapasitas));
        holder.ivMenu.setOnClickListener(v -> menuClickListerner.onClick(position, v));
    }

    @Override
    public int getItemCount() {
        return ruangans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamaRuangan;
        private TextView tvKapasitas;
        private ImageView ivMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaRuangan = itemView.findViewById(R.id.tvNamaRuangan);
            tvKapasitas = itemView.findViewById(R.id.tvKapasitas);
            ivMenu = itemView.findViewById(R.id.ivMenu);
        }
    }

    public interface OnClickListerner {
        void onClick(int position, View view);
    }
}
