package com.example.project_mently;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BuatNama extends AppCompatActivity {
    Button btnDaftar;
    EditText edtUsername, edtPassword;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_nama);

        mDatabase = FirebaseDatabase.getInstance().getReference("Pasien");

        btnDaftar = findViewById(R.id.btn_Daftar2);
        edtUsername = findViewById(R.id.edtNamaPengguna);
        edtPassword = findViewById(R.id.edtBuatSandi);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BuatNama.this);
                builder.setTitle("Peringatan");
                builder.setMessage("Apakah data yang Anda isi sudah sesuai?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = edtUsername.getText().toString();
                        String password = edtPassword.getText().toString();

                        if (username.isEmpty()) {
                            edtUsername.setError("Username tidak boleh kosong");
                        } else if (password.isEmpty()) {
                            edtPassword.setError("Password tidak boleh kosong");
                        } else {
                            Intent intent = getIntent();
                            String id = intent.getStringExtra("userId");

                            mDatabase.child(id).child("username").setValue(username);
                            mDatabase.child(id).child("password").setValue(password);

                            Intent pindah = new Intent(getApplicationContext(), LoginPasien.class);
                            startActivity(pindah);

                            Toast.makeText(getApplicationContext(), "Berhasil Mendaftar, Silahkan Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing, close dialog
                    }
                });
                builder.show();
            }
        });
    }
}
