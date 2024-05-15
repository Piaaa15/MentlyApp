package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BuatNama extends AppCompatActivity {
    Button btnDaftar;
    ImageView btnKembali;
    EditText edtUsername, edtPassword;
    // membuat objek firebase
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_nama);
        // menghubungkan firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("Pasien");
        // tombol kembali ke halaman *RegistrasiPenerima
        btnKembali = findViewById(R.id.btnBack);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DaftarPasien.class);
                startActivity(i);
            }
        });
        // tombol untuk melakukan pendaftaran
        btnDaftar = findViewById(R.id.btn_Daftar2);
        edtUsername = findViewById(R.id.edtNamaPengguna);
        edtPassword = findViewById(R.id.edtBuatSandi);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if (username.isEmpty()){
                    edtUsername.setError("username tidak boleh kosong");
                } else if (password.isEmpty()) {
                    edtPassword.setError("Password Tidak boleh kosong");
                }else {
                    Intent intent = getIntent();
                    String id = intent.getStringExtra("userId");
                    mDatabase.child(id).child("username").setValue(username);
                    mDatabase.child(id).child("password").setValue(password);
                    Intent i = new Intent(getApplicationContext(), LoginPasien.class);
                    startActivity(i);

                }
            }
        });

    }
}