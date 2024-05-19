package com.example.project_mently;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    ImageView btnback;
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
        btnback = findViewById(R.id.btnBackdaftar);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
                AlertDialog.Builder builder = new AlertDialog.Builder(DaftarPasien.this);
                builder.setTitle("Konfirmasi");
                builder.setMessage("Apakah data yang Anda isi sudah sesuai?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Mengambil data dari EditText
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
                        } else {
                            // Membuat objek pasien
                            Pasien pasien = new Pasien(nama, email, umur, jenkel, tanggalDibuat);
                            // Menyimpan data ke firebase
                            String id = mDatabase.push().getKey();
                            mDatabase.child(id).setValue(pasien);
                            // Membuat intent untuk pindah ke halaman selanjutnya
                            Intent intent = new Intent(getApplicationContext(), BuatNama.class);
                            intent.putExtra("userId", id);
                            startActivity(intent);
                            Toast.makeText(DaftarPasien.this, "Data berhasil disimpan, Lanjut Buat Username Password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Mengabaikan tindakan
                    }
                });
                builder.show();
            }
        });

    }
}