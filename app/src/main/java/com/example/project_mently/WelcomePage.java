package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


public class WelcomePage extends AppCompatActivity {
    Button btnPasien, btnAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        btnAdmin = findViewById(R.id.btn_admin);
        btnPasien = findViewById(R.id.btn_pasien);
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginAdmin.class);
                startActivity(i);
            }
        });
        btnPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginPasien.class);
                startActivity(i);
            }
        });

    }
}