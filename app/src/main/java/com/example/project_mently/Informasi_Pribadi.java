package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import kodeJava.Pasien;

public class Informasi_Pribadi extends AppCompatActivity {
    ImageView btnback;
    EditText edtNama, edtEmail, edtUmur, edtJenkel;
    TextView txtNama;
    Button btnUbah;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_pribadi);

        edtNama = findViewById(R.id.nama);
        edtEmail = findViewById(R.id.Email1);
        edtUmur = findViewById(R.id.umur1);
        edtJenkel = findViewById(R.id.jenkel1);
        txtNama = findViewById(R.id.nama1);
        btnUbah = findViewById(R.id.ubah);
        btnback = findViewById(R.id.btn_back6);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Mendapatkan data dari Intent
        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String email = intent.getStringExtra("email");
        String umur = intent.getStringExtra("umur");
        String jenkel = intent.getStringExtra("jenkel");

        edtNama.setText(nama);
        edtEmail.setText(email);
        edtUmur.setText(umur);
        edtJenkel.setText(jenkel);
        txtNama.setText(nama);

        // Inisialisasi referensi Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("Pasien");

        // Mengatur listener untuk tombol Ubah
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
        edtJenkel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), edtJenkel);
                dropDownMenu.getMenuInflater().inflate(R.menu.dropdown_menu, dropDownMenu.getMenu());
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        edtJenkel.setText(menuItem.getTitle());
                        return true;
                    }
                });
                dropDownMenu.show();

            }
        });
        edtJenkel.clearFocus();
    }

    private void updateUser() {
        String nama = edtNama.getText().toString();
        String email = edtEmail.getText().toString();
        String umur = edtUmur.getText().toString();
        String jenkel = edtJenkel.getText().toString();

        // Membuat query untuk mencari data berdasarkan nama
        Query query = databaseReference.orderByChild("nama").equalTo(nama);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Mengambil ID pengguna dari snapshot
                    String userId = userSnapshot.getKey();
                    String username = userSnapshot.child("username").getValue(String.class);
                    String password = userSnapshot.child("password").getValue(String.class);
                    String pasienSejak = userSnapshot.child("tanggalDibuat").getValue(String.class);

                    // Membuat objek Pasien baru dengan data yang diperbarui
                    Pasien updatedUser = new Pasien(nama, email, umur, jenkel, pasienSejak);

                    // Memperbarui data pengguna di Firebase tanpa mengubah username dan password
                    databaseReference.child(userId).child("email").setValue(updatedUser.getEmail());
                    databaseReference.child(userId).child("jenkel").setValue(updatedUser.getJenkel());
                    databaseReference.child(userId).child("nama").setValue(updatedUser.getNama());
                    databaseReference.child(userId).child("umur").setValue(updatedUser.getUmur());
                    databaseReference.child(userId).child("tanggalDibuat").setValue(updatedUser.getTanggalDibuat())
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Informasi_Pribadi.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Informasi_Pribadi.this, "Gagal memperbarui data", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Informasi_Pribadi.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
