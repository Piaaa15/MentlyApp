package com.example.project_mently;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kodeJava.Konsul;

public class KonsulAdapter extends RecyclerView.Adapter<KonsulAdapter.KonsulViewHolder> {

    private List<Konsul> konsulList;

    public KonsulAdapter(List<Konsul> konsulList) {
        this.konsulList = konsulList;
    }

    @NonNull
    @Override
    public KonsulViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gejala, parent, false);
        return new KonsulViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KonsulViewHolder holder, int position) {
        Konsul konsul = konsulList.get(position);
        String formattedDate = formatDateString(konsul.getTanggal());
        holder.txtTanggal.setText(formattedDate);
        holder.txtGejala.setText(konsul.getGejala());
        holder.nomorKonsul.setText("Konsultasi " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return konsulList.size();
    }

    public static class KonsulViewHolder extends RecyclerView.ViewHolder {

        TextView txtTanggal, txtGejala, nomorKonsul;

        public KonsulViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtGejala = itemView.findViewById(R.id.txtGejala);
            nomorKonsul = itemView.findViewById(R.id.nomor_konsul);
        }

    }
    private String formatDateString(String dateString) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = originalFormat.parse(dateString);
            return targetFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateString; // In case of error, return the original string
        }
    }
}
