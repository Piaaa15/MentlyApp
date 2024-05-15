package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import kodeJava.Pasien;

public class DaftarPasien extends AppCompatActivity {
    TextView txtMasuk;

    EditText etNama, etEmail, etUmur,  etJenisKelamin;
    Button btnLanjut;
    // objek untuk mengkoneksikan firebase
    private DatabaseReference mDatabase;
    // membuat userId

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pasien);
        // koneksi database
        mDatabase = FirebaseDatabase.getInstance().getReference("Pasien");


        // variabel untuk mengkoneksikan halaman xml ke java
        txtMasuk = findViewById(R.id.PunyaAkun);
        etNama = findViewById(R.id.edtNamaLengkap);
        etEmail = findViewById(R.id.edtEmail);
        etUmur = findViewById(R.id.edtUmur);
        etJenisKelamin = findViewById(R.id.edtJenkel);
        btnLanjut = findViewById(R.id.btn_lanjut);

        txtMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginPasien.class);
                startActivity(i);
            }
        });
        etJenisKelamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), etJenisKelamin);
                dropDownMenu.getMenuInflater().inflate(R.menu.dropdown_menu, dropDownMenu.getMenu());
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        etJenisKelamin.setText(menuItem.getTitle());
                        return true;
                    }
                });
                dropDownMenu.show();

            }
        });
        etJenisKelamin.clearFocus();
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // membuat method untuk menyimpan data

                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String umur = etUmur.getText().toString();
                String jenkel = etJenisKelamin.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String tanggalDibuat = sdf.format(new Date());

                if (nama.isEmpty()){
                    etNama.setError("Nama tidak boleh kosong");
                } else if (email.isEmpty()) {
                    etEmail.setError("Email tidak boleh kosong");
                } else if (umur.isEmpty()) {
                    etUmur.setError("Umur tidak boleh kosong");
                } else if (jenkel.isEmpty()) {
                    etJenisKelamin.setError("Jenis kelamin tidak boleh kosong");
                }else {
                // membuat objek pasien
                Pasien pasien = new Pasien(nama, email, umur, jenkel, tanggalDibuat);
                // menyimpan data ke firebase
                String id = mDatabase.push().getKey();
                mDatabase.child(id).setValue(pasien);
                // membuat intent untuk pindah ke halaman selanjutnya
                Intent i = new Intent(getApplicationContext(), BuatNama.class);
                i.putExtra("userId", id);
                startActivity(i);
                }

            }
        });




    }
}