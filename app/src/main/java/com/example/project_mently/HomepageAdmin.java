package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomepageAdmin extends AppCompatActivity {
    private int selectedTab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_admin);
        final LinearLayout adminHome = findViewById(R.id.page_home);
        final LinearLayout adminRiwayat = findViewById(R.id.page_status);
        final LinearLayout adminProfile = findViewById(R.id.page_profile);


        final ImageView homeicon = findViewById(R.id.icon_home);
        final ImageView statusicon = findViewById(R.id.icon_status);
        final ImageView profileicon = findViewById(R.id.icon_profil);

        final TextView texthome = findViewById(R.id.text_home);
        final TextView textstatus = findViewById(R.id.text_status);
        final TextView textprofil = findViewById(R.id.text_profile);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        getSupportFragmentManager()
                .beginTransaction().setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, Admin_Home.class,null)
                .commit();

        adminHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTab != 1){
                    getSupportFragmentManager()
                            .beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, Admin_Home.class,null)
                            .commit();
                    textprofil.setVisibility(View.GONE);
                    textstatus.setVisibility(View.GONE);

                    statusicon.setImageResource(R.drawable.icon_status);
                    profileicon.setImageResource(R.drawable.icon_profile);

                    adminHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    adminRiwayat.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    adminProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    texthome.setVisibility(View.VISIBLE);
                    homeicon.setImageResource(R.drawable.icon_selected_home);
                    adminHome.setBackgroundResource(R.drawable.round_icon);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);

                    adminHome.startAnimation(scaleAnimation);
                    selectedTab = 1;
                }

            }
        });

        adminRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTab != 2){
                    getSupportFragmentManager()
                            .beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, Admin_Riwayat.class,null)
                            .commit();
                    texthome.setVisibility(View.GONE);
                    textprofil.setVisibility(View.GONE);

                    homeicon.setImageResource(R.drawable.icon_home);
                    profileicon.setImageResource(R.drawable.icon_profile);

                    adminHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    adminProfile.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    textstatus.setVisibility(View.VISIBLE);
                    statusicon.setImageResource(R.drawable.icon_selected_status);
                    adminRiwayat.setBackgroundResource(R.drawable.round_icon);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);

                    adminRiwayat.startAnimation(scaleAnimation);
                    selectedTab = 2;
                }
            }
        });
        adminProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTab != 3){
                    getSupportFragmentManager()
                            .beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, Admin_Profile.class,null)
                            .commit();
                    texthome.setVisibility(View.GONE);
                    textstatus.setVisibility(View.GONE);

                    statusicon.setImageResource(R.drawable.icon_status);
                    homeicon.setImageResource(R.drawable.icon_home);

                    adminRiwayat.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    adminHome.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    textprofil.setVisibility(View.VISIBLE);
                    profileicon.setImageResource(R.drawable.icon_selected_profile);
                    adminProfile.setBackgroundResource(R.drawable.round_icon);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    adminProfile.startAnimation(scaleAnimation);
                    selectedTab = 3;
                }

            }
        });


    }
}