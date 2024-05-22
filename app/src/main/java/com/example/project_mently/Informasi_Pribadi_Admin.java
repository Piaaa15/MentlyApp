package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Informasi_Pribadi_Admin extends AppCompatActivity {

    ImageView btnback;

    EditText edtUsername, edtPassword;
    private DatabaseReference mDatabase;
    TextView txtbantu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_pribadi_admin);
        mDatabase = FirebaseDatabase.getInstance().getReference("Admin");

        btnback = findViewById(R.id.btnAdminKembali);
        edtUsername = findViewById(R.id.adminUsername);
        edtPassword = findViewById(R.id.adminPw);
        txtbantu = findViewById(R.id.txtBantu);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        edtUsername.setText(username);
        edtPassword.setText(password);

        btnback.setOnClickListener(view -> {
            finish();
        });
        txtbantu.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), ActivityBantuan.class);
            startActivity(intent1);
        });


    }
}