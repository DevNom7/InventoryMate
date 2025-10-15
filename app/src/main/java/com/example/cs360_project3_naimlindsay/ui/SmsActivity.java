// My SMS screen - handles permissions and lets me test sending a text message.
package com.example.cs360_project3_naimlindsay.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.cs360_project3_naimlindsay.R;


public class SmsActivity extends AppCompatActivity {

    // Request code I made up for the permission check (just needs to be unique)
    private static final int REQ_SMS = 100;

    // Label on screen that shows permission status or messages
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);  // connects this file to my SMS layout XML

        // Bottom navigation buttons so I can switch between screens
        Button navLogin = findViewById(R.id.navLogin);
        Button navInventory = findViewById(R.id.navInventory);

        // Permission + test SMS buttons
        Button btnCheck = findViewById(R.id.btnCheckPermission);
        Button btnSend = findViewById(R.id.btnSendTest);

        // Text area that shows whether permission is granted or not
        status = findViewById(R.id.tvPermStatus);

        // When I click “Go to Login”, it switches to the login screen
        navLogin.setOnClickListener(v ->
                startActivity(new Intent(this, LoginActivity.class)));

        // When I click “Go to Inventory”, it switches to the inventory screen
        navInventory.setOnClickListener(v ->
                startActivity(new Intent(this, com.example.cs360_project3_naimlindsay.ui.InventoryActivity.class)));

        // When I hit “Check Permission”, it runs my permission checker
        btnCheck.setOnClickListener(v -> checkSmsPermission());

        // When I hit “Send Test SMS”, it tries to send a fake message to 5551234567
        btnSend.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_GRANTED) {
                // If I already have permission, send the text
                SmsManager.getDefault().sendTextMessage("5551234567", null,
                        "Test alert from Project 3 app!", null, null);
                status.setText("SMS sent successfully ✅");
            } else {
                // If not allowed, just show that I can’t send it
                status.setText("Permission denied — no SMS sent ❌");
            }
        });
    }

    // My helper method to check or ask for SMS permission
    private void checkSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            // I already have permission
            status.setText("Permission granted ✅");
        } else {
            // Ask the user to allow it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, REQ_SMS);
        }
    }

    // Runs automatically after user responds to the permission popup
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] perms, int[] results) {
        super.onRequestPermissionsResult(requestCode, perms, results);

        if (requestCode == REQ_SMS) {
            if (results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
                status.setText("Permission granted ✅");
            } else {
                status.setText("Permission denied ❌");
            }
        }
    }
}
