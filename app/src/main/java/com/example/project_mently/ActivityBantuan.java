package com.example.project_mently;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityBantuan extends AppCompatActivity {

    ImageView btnBack;
    Button btnWhatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);

        btnBack = findViewById(R.id.btnKembaliBantuan);
        btnBack.setOnClickListener(v -> {
            finish();
        });
        btnWhatsapp = findViewById(R.id.btnWa);
        btnWhatsapp.setOnClickListener(v -> {
            String phoneNumber = "628884287484"; // Nomor telepon WhatsApp

            // Membuat Intent untuk membuka WhatsApp dengan nomor telepon yang ditentukan
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://wa.me/" + phoneNumber));
            startActivity(intent);
        });

    }
}