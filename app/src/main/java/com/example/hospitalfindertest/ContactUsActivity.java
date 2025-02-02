package com.example.hospitalfindertest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Button emailButton = findViewById(R.id.btn_email);
        Button callButton = findViewById(R.id.btn_call);
        Button chatButton = findViewById(R.id.btn_chat);
        Button submitButton = findViewById(R.id.btn_submit);

        EditText nameInput = findViewById(R.id.edit_name);
        EditText emailInput = findViewById(R.id.edit_email);
        EditText messageInput = findViewById(R.id.edit_message);

        // Send Email
        emailButton.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:contact@healthcare.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry");
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
        });

        // Call
        callButton.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:+1234567890"));
            startActivity(callIntent);
        });



        // Open WhatsApp Chat
        chatButton.setOnClickListener(v -> {
            String url = "https://wa.me/1234567890"; // Replace with actual WhatsApp number
            Intent chatIntent = new Intent(Intent.ACTION_VIEW);
            chatIntent.setData(Uri.parse(url));
            startActivity(chatIntent);
        });



        // Submit Contact Form
        submitButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String message = messageInput.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:contact@healthcare.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact Form Inquiry from " + name);
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Name: " + name + "\nEmail: " + email + "\nMessage: " + message);
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
        });
    }
}
