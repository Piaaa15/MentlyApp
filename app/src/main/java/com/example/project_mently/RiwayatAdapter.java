package com.example.project_mently;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.project_mently.R;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kodeJava.Konsul;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.HasilKonsulViewHolder> {

    private List<Konsul> hasilKonsulList;
    private Context parentContext;

    public RiwayatAdapter(List<Konsul> hasilKonsulList, Context parent) {
        this.hasilKonsulList = hasilKonsulList;
        this.parentContext = parent;

    }

    @NonNull
    @Override
    public HasilKonsulViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat, parent, false);
        return new HasilKonsulViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HasilKonsulViewHolder holder, int position) {
        Konsul hasilKonsul = hasilKonsulList.get(position);
        holder.penyakitTextView.setText(hasilKonsul.getPenyakit());
        holder.namaPasienTextView.setText(hasilKonsul.getNamaPasien());
        holder.gejalaTextView.setText(hasilKonsul.getGejala());

        holder.lihatTextView.setOnClickListener(v -> {
            Intent intent = new Intent(parentContext, DetailPasien.class);
            intent.putExtra("konsulId", hasilKonsul.getIdKonsul()); // Assuming Konsul has an getId() method
            parentContext.startActivity(intent);
        });

    }
    @Override
    public int getItemCount() {
        return hasilKonsulList.size();
    }

    public static class HasilKonsulViewHolder extends RecyclerView.ViewHolder {
        TextView namaPasienTextView, penyakitTextView, lihatTextView, gejalaTextView;

        public HasilKonsulViewHolder(@NonNull View itemView) {
            super(itemView);
            namaPasienTextView = itemView.findViewById(R.id.txtPasienRiwayat);
            penyakitTextView = itemView.findViewById(R.id.txtPRiwayat);
            gejalaTextView = itemView.findViewById(R.id.txtPasienGejala);
            lihatTextView = itemView.findViewById(R.id.txt_lihat2);


        }
    }





}
