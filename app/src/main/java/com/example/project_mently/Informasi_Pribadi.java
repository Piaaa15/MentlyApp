package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Informasi_Pribadi extends AppCompatActivity {
    ImageView btnback;

    EditText edtNama, edtEmail, edtUmur, edtJenkel;
    TextView txtNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_pribadi);

        edtNama = findViewById(R.id.nama);
        edtEmail = findViewById(R.id.Email1);
        edtUmur = findViewById(R.id.umur1);
        edtJenkel = findViewById(R.id.jenkel1);
        txtNama = findViewById(R.id.nama1);

        btnback = findViewById(R.id.btn_back6);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });

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


    }

}