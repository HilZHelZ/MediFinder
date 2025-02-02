package com.example.hospitalfindertest;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Retrieve the email from intent
        String userEmail = getIntent().getStringExtra("USER_EMAIL");

        // Set the welcome message
        TextView welcomeText = findViewById(R.id.welcomeText);
        if (userEmail != null) {
            welcomeText.setText("Welcome, " + userEmail + "!");
        }

        // Buttons
        Button aboutUsButton = findViewById(R.id.btn_about_us);
        Button findHospitalButton = findViewById(R.id.btn_find_hospital);
        Button codeScannerButton = findViewById(R.id.btn_code_scanner);
        Button contactUsButton = findViewById(R.id.btn_contact_us);

        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        findHospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, HospitalListActivity.class);
                startActivity(intent);
            }
        });

        codeScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, CodeScannerActivity.class);
                startActivity(intent);
            }
        });

        contactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });
    }
}
