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
import com.example.databaseapp.entity.Gedung;
import com.example.databaseapp.entity.Ruangan;

import java.util.List;

public class RuanganAdapter extends RecyclerView.Adapter<RuanganAdapter.ViewHolder> {
    private List<Ruangan> ruangans;
    private OnClickListerner onClickListerner;
    private OnClickListerner onClickListerner2;
    private OnClickListerner onClickListerner3;

    public RuanganAdapter(List<Ruangan> ruangans, OnClickListerner onClickListerner, OnClickListerner onClickListerner2, OnClickListerner onClickListerner3) {
        this.ruangans = ruangans;
        this.onClickListerner = onClickListerner;
        this.onClickListerner2 = onClickListerner2;
        this.onClickListerner3 = onClickListerner3;
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

        holder.tvNamaRuangan.setText(namaRuangan);
        holder.ivEdit.setOnClickListener(v -> onClickListerner3.onClick(position));
        holder.ivDelete.setOnClickListener(v -> onClickListerner2.onClick(position));
        holder.itemView.setOnClickListener(v -> onClickListerner.onClick(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamaRuangan;
        private ImageView ivEdit;
        private ImageView ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaRuangan = itemView.findViewById(R.id.tvNamaRuangan);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    public interface OnClickListerner {
        void onClick(int position);
    }
}
