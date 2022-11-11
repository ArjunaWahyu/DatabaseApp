package com.example.databaseapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.databaseapp.R;
import com.example.databaseapp.entity.AppDatabase;
import com.example.databaseapp.entity.Gedung;

import java.util.List;

public class GedungAdapter extends RecyclerView.Adapter<GedungAdapter.ViewHolder> {
    private List<Gedung> gedungs;
    private OnClickListerner onClickListerner;
    private OnClickListerner onClickListerner2;

    public GedungAdapter(List<Gedung> gedungs, OnClickListerner onClickListerner, OnClickListerner onClickListerner2) {
        this.gedungs = gedungs;
        this.onClickListerner = onClickListerner;
        this.onClickListerner2 = onClickListerner2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout
        View photoView = inflater.inflate(R.layout.item_gedung_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String namaGedung = gedungs.get(position).getNamaGedung();

        holder.tvNamaGedung.setText(namaGedung);
        holder.ivDelete.setOnClickListener(v -> onClickListerner2.onClick(position));
        holder.itemView.setOnClickListener(v -> onClickListerner.onClick(position));
    }

    @Override
    public int getItemCount() {
        return gedungs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamaGedung;
        private ImageView ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaGedung = itemView.findViewById(R.id.tvNamaGedung);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    public interface OnClickListerner {
        void onClick(int position);
    }
}