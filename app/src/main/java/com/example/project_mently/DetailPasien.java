package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import kodeJava.Konsul;
import kodeJava.Pasien;

public class DetailPasien extends AppCompatActivity {

    private TextView txtNamaPasien, txtJenkelPasien, txtUmurPasien, txtPasienAt;
    private DatabaseReference mdatabase, mDatabaseKonsul;
    private RecyclerView recyclerView;
    private HasilKonsulAdapter adapter;
    private List<Konsul> hasilKonsulList;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasien);

        mdatabase = FirebaseDatabase.getInstance().getReference("Pasien");
        mDatabaseKonsul = FirebaseDatabase.getInstance().getReference("Konsul");

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        hasilKonsulList = new ArrayList<>();
        adapter = new HasilKonsulAdapter(hasilKonsulList, this);
        recyclerView.setAdapter(adapter);

        // Initialize TextViews
        txtNamaPasien = findViewById(R.id.txtNamaPasien);
        txtJenkelPasien = findViewById(R.id.txtJenkelPasien);
        txtUmurPasien = findViewById(R.id.txtUmurPasien);
        txtPasienAt = findViewById(R.id.txtPasienAt);
        btnBack = findViewById(R.id.btn_back5);

        btnBack.setOnClickListener(view -> finish());

        // Get data from intent
        Intent intent = getIntent();
        String namaPasien = intent.getStringExtra("namaPasien");
        String penyakit = intent.getStringExtra("penyakit");
        String gejala = intent.getStringExtra("gejala");

        if (namaPasien != null) {
            Query query = mdatabase.orderByChild("nama").equalTo(namaPasien);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Pasien pasien = data.getValue(Pasien.class);
                        if (pasien != null) {
                            txtNamaPasien.setText(pasien.getNama());
                            txtJenkelPasien.setText(pasien.getJenkel());
                            txtUmurPasien.setText(pasien.getUmur());
                            txtPasienAt.setText(pasien.getTanggalDibuat());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle possible errors
                }
            });
        }

        Query q = mDatabaseKonsul.orderByChild("namaPasien").equalTo(namaPasien);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hasilKonsulList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Konsul hasilKonsul = data.getValue(Konsul.class);
                    if (hasilKonsul != null) {
                        hasilKonsulList.add(hasilKonsul);
                    }
                }
                adapter.notifyDataSetChanged();

                // Scroll to the specific item
                if (penyakit != null && gejala != null) {
                    for (int i = 0; i < hasilKonsulList.size(); i++) {
                        Konsul k = hasilKonsulList.get(i);
                        if (k.getPenyakit().equals(penyakit) && k.getGejala().equals(gejala)) {
                            recyclerView.scrollToPosition(i);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors
            }
        });
    }
}
