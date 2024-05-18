package com.example.project_mently;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class HasilKonsulAdapter extends RecyclerView.Adapter<HasilKonsulAdapter.HasilKonsulViewHolder> {

    private List<Konsul> hasilKonsulList;
    private Context parentContext;

    public HasilKonsulAdapter(List<Konsul> hasilKonsulList, Context parent) {
        this.hasilKonsulList = hasilKonsulList;
        this.parentContext = parent;

    }

    @NonNull
    @Override
    public HasilKonsulViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hasil_konsul, parent, false);
        return new HasilKonsulViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HasilKonsulViewHolder holder, int position) {
        Konsul hasilKonsul = hasilKonsulList.get(position);

        holder.penyakitTextView.setText(hasilKonsul.getPenyakit());
        holder.resepObatTextView.setText(hasilKonsul.getResepObat());
        holder.solusiTextView.setText(hasilKonsul.getSolusi());
        holder.tingkatStresTextView.setText(hasilKonsul.getTingkatStress());

        String formattedDate = formatDateString(hasilKonsul.getTanggal());
        holder.tanggalTextView.setText(formattedDate);

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(hasilKonsul, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hasilKonsulList.size();
    }

    public static class HasilKonsulViewHolder extends RecyclerView.ViewHolder {
        TextView penyakitTextView, resepObatTextView, solusiTextView, tingkatStresTextView, tanggalTextView;
        ImageView btnHapus;

        public HasilKonsulViewHolder(@NonNull View itemView) {
            super(itemView);
            penyakitTextView = itemView.findViewById(R.id.Penyakit);
            resepObatTextView = itemView.findViewById(R.id.ResepObat2);
            solusiTextView = itemView.findViewById(R.id.Solusi1);
            tingkatStresTextView = itemView.findViewById(R.id.Tingkatstres2);
            tanggalTextView = itemView.findViewById(R.id.tanggal);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }
    private void showDeleteConfirmationDialog(Konsul hasilKonsul, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(parentContext);
        builder.setMessage("Apakah Anda yakin ingin menghapus data ini?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User mengklik Ya, hapus data
                        deleteKonsul(hasilKonsul, position);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User mengklik Tidak, tutup dialog
                        dialog.dismiss();
                    }
                });
        // Membuat dan menampilkan dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void deleteKonsul(Konsul hasilKonsul, int position) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Konsul");

        // Ambil ID dari objek hasilKonsul
        String konsulId = hasilKonsul.getIdKonsul();

        // Buat referensi ke node Konsul dengan ID yang sesuai
        DatabaseReference konsulRef = databaseReference.child(konsulId);

        // Hapus entri yang sesuai dengan ID yang ditentukan
        konsulRef.removeValue()
                .addOnSuccessListener(aVoid -> {
                    // Hapus item dari RecyclerView
                    hasilKonsulList.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(parentContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(parentContext, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                });


    }

    private String formatDateString(String dateString) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = originalFormat.parse(dateString);
            return targetFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateString;
        }
    }


}
