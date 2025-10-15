// My login screen — handles creating accounts and logging users in using SQLite
package com.example.cs360_project3_naimlindsay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs360_project3_naimlindsay.R;
import com.example.cs360_project3_naimlindsay.data.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    // Text fields for username and password
    EditText etUser, etPass;

    // Buttons for logging in, creating an account, and simple navigation
    Button btnLogin, btnCreate, navInventory, navSms;

    // My SQLite helper class that owns the app tables
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // connects this activity to my XML layout

        // Hook up inputs & buttons to their XML IDs
        etUser = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreate = findViewById(R.id.btnCreate);
        navInventory = findViewById(R.id.navInventory);
        navSms = findViewById(R.id.navSms);

        // Ready the database (creates it on first run)
        dbHelper = new DatabaseHelper(this);

        // LOGIN — checks if that username/password combo exists
        btnLogin.setOnClickListener(v -> {
            String u = etUser.getText().toString().trim();
            String p = etPass.getText().toString().trim();

            // super basic input check so I don't try empty creds
            if (TextUtils.isEmpty(u) || TextUtils.isEmpty(p)) {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean ok = dbHelper.validateUser(u, p);
            if (ok) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, InventoryActivity.class)); // go to inventory
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // CREATE ACCOUNT — writes a new row if username isn't taken
        btnCreate.setOnClickListener(v -> {
            String u = etUser.getText().toString().trim();
            String p = etPass.getText().toString().trim();

            if (TextUtils.isEmpty(u) || TextUtils.isEmpty(p)) {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean created = dbHelper.createUser(u, p);
            Toast.makeText(this,
                    created ? "Account created" : "User already exists",
                    Toast.LENGTH_SHORT).show();
        });

        // Quick nav buttons so I can bounce around screens while testing
        navInventory.setOnClickListener(v ->
                startActivity(new Intent(this, InventoryActivity.class)));

        navSms.setOnClickListener(v ->
                startActivity(new Intent(this, SmsActivity.class)));
    }
}
