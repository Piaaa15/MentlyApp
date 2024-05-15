package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAdmin extends AppCompatActivity {
    ImageView btnBack;
    Button btnMasuk;
    EditText etUsername, etPassword;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        btnMasuk = findViewById(R.id.btn_masukAdmin);
        btnBack = findViewById(R.id.btn_back1);
        etUsername = findViewById(R.id.edtUsernameA);
        etPassword = findViewById(R.id.edtKatasandiA);

        mDatabase = FirebaseDatabase.getInstance().getReference("Admin");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), WelcomePage.class);
                startActivity(i);
            }
        });
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (username.isEmpty()) {
                    etUsername.setError("username kosong");
                } else if (password.isEmpty()) {
                    etPassword.setError("Password kosong");
                } else {

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean usernameFound = false;
                        boolean isLoginSuccessful = false;

                            String usernameDB = snapshot.child("username").getValue().toString();
                            String passwordDB = snapshot.child("password").getValue().toString();

                            if(username.equals(usernameDB)) {
                                usernameFound = true;
                                if (password.equals(passwordDB)) {
                                    isLoginSuccessful = true;
                                    Intent i = new Intent(getApplicationContext(), HomepageAdmin.class);

                                    startActivity(i);
                                    Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                                }
                            }

                        if (!isLoginSuccessful) {
                            if (usernameFound) {
                                Toast.makeText(LoginAdmin.this, "Password Salah", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginAdmin.this, "Username Salah", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
            }



        });

    }
}