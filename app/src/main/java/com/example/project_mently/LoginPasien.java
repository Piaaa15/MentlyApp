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

public class LoginPasien extends AppCompatActivity {
    ImageView btnBack;
    Button btnMasuk, btnDaftar;
    EditText etUsername, etPassword;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pasien);

        btnMasuk = findViewById(R.id.btn_masuk1);

        btnBack = findViewById(R.id.btn_back1);
        btnDaftar = findViewById(R.id.btn_daftar1);
        etUsername = findViewById(R.id.edtUsernameP);
        etPassword = findViewById(R.id.edtKatasandiP);

        mDatabase = FirebaseDatabase.getInstance().getReference("Pasien");



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), WelcomePage.class);
                startActivity(i);
            }
        });
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DaftarPasien.class);
                startActivity(i);
            }
        });

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameP = etUsername.getText().toString();
                String passwordP = etPassword.getText().toString();

                if (usernameP.isEmpty()) {
                    etUsername.setError("username kosong");
                } else if (passwordP.isEmpty()) {
                    etPassword.setError("Password kosong");
                } else {
                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean usernameFound = false;
                            boolean isLoginSuccessful = false;

                            for (DataSnapshot data : snapshot.getChildren()) {
                                String username = data.child("username").getValue(String.class);
                                String password = data.child("password").getValue(String.class);

                                if (usernameP.equals(username)) {
                                    usernameFound = true;
                                    if (passwordP.equals(password)) {
                                        isLoginSuccessful = true;

                                        String nama = data.child("nama").getValue(String.class);
                                        String email = data.child("email").getValue(String.class);
                                        String umur = data.child("umur").getValue(String.class);
                                        String jenkel = data.child("jenkel").getValue(String.class);
                                        Intent i = new Intent(getApplicationContext(), HomepagePengguna.class);
                                        i.putExtra("nama", nama);
                                        i.putExtra("email", email);
                                        i.putExtra("umur", umur);
                                        i.putExtra("jenkel", jenkel);

                                        startActivity(i);
                                        Toast.makeText(LoginPasien.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                }
                            }

                            if (!isLoginSuccessful) {
                                if (usernameFound) {
                                    Toast.makeText(LoginPasien.this, "Password Salah", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginPasien.this, "Username Salah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle possible errors.
                        }
                    });
                }
            }
        });





    }
}