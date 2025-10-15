// My inventory screen — adds items to a table and lists them out
package com.example.cs360_project3_naimlindsay.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs360_project3_naimlindsay.R;
import com.example.cs360_project3_naimlindsay.data.DatabaseHelper;

public class InventoryActivity extends AppCompatActivity {

    // Inputs + buttons + where I print the results
    EditText etName, etQty;
    Button btnAdd, btnShow, navLogin, navSms;
    TextView tvDisplay;

    // One helper to rule them all
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory); // binds to my inventory XML

        // Wire up views
        etName = findViewById(R.id.etName);
        etQty = findViewById(R.id.etQty);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        tvDisplay = findViewById(R.id.tvInventory);
        navLogin = findViewById(R.id.navLogin);
        navSms = findViewById(R.id.navSms);

        // Get my database ready
        dbHelper = new DatabaseHelper(this);

        // ADD ITEM — inserts a new row and tells me if it worked
        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String qtyStr = etQty.getText().toString().trim();

            if (name.isEmpty() || qtyStr.isEmpty()) {
                Toast.makeText(this, "Enter name and qty", Toast.LENGTH_SHORT).show();
                return;
            }

            int qty;
            try {
                qty = Integer.parseInt(qtyStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Qty must be a number", Toast.LENGTH_SHORT).show();
                return;
            }

            long rowId = dbHelper.insertItem(name, qty); // -1 = failed
            Toast.makeText(this,
                    rowId > 0 ? "Item added!" : "Insert failed",
                    Toast.LENGTH_SHORT).show();
        });

        // SHOW ITEMS — dumps the table contents into my TextView
        btnShow.setOnClickListener(v -> {
            Cursor c = dbHelper.getAllItems();
            StringBuilder sb = new StringBuilder();

            while (c.moveToNext()) {
                int id = c.getInt(0);         // id
                String nm = c.getString(1);   // name
                int q = c.getInt(2);          // qty
                sb.append(id).append(". ")
                        .append(nm).append(" — Qty: ")
                        .append(q).append("\n");
            }
            c.close();

            tvDisplay.setText(sb.length() == 0 ? "No items yet" : sb.toString());
        });

        // Simple nav while testing
        navLogin.setOnClickListener(v ->
                startActivity(new Intent(this, LoginActivity.class)));
        navSms.setOnClickListener(v ->
                startActivity(new Intent(this, SmsActivity.class)));
    }
}
