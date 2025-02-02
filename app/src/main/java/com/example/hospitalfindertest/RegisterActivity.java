package com.example.hospitalfindertest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText e1, e2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        mAuth = FirebaseAuth.getInstance();
    }

    // Navigate to the Login Page
    public void goToLoginPage(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    // Create a new user
    public void createUser(View v) {
        String email = e1.getText().toString().trim();
        String password = e2.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Blank fields are not allowed", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            // Validate password length
            Toast.makeText(getApplicationContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Success: Notify user and navigate to LoginActivity
                                Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(i);
                            } else {
                                // Failure: Show Firebase error message
                                String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error occurred";
                                Toast.makeText(getApplicationContext(), "Registration failed: " + errorMessage, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
