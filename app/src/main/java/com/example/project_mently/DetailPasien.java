package com.example.project_mently;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailPasien extends AppCompatActivity {

    private TextView txtNamaPasien, txtJenkelPasien, txtUmurPasien, txtPasienAt;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasien);



        // Initialize TextViews
        txtNamaPasien = findViewById(R.id.txtNamaPasien);
        txtJenkelPasien = findViewById(R.id.txtJenkelPasien);
        txtUmurPasien = findViewById(R.id.txtUmurPasien);
        txtPasienAt = findViewById(R.id.txtPasienAt);
        btnBack = findViewById(R.id.btn_back5);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Get data from intent
        String nama = getIntent().getStringExtra("nama");
        String jenkel = getIntent().getStringExtra("jenkel");
        String umur = getIntent().getStringExtra("umur");
        String tanggalDibuat = getIntent().getStringExtra("tanggalDibuat");

        // Set data to TextViews
        txtNamaPasien.setText(nama);
        txtJenkelPasien.setText(jenkel);
        txtUmurPasien.setText(umur);
        txtPasienAt.setText(tanggalDibuat);
    }
}
