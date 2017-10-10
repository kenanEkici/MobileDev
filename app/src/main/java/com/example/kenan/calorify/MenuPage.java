package com.example.kenan.calorify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.kenan.calorify.datalayer.ScanActivity;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        setTitle("Calorify Menu");

        Button scanButton = (Button) findViewById(R.id.scan_button);
        scanButton.setOnClickListener((v) -> {
            Intent intent = new Intent(getApplicationContext(), ScanActivity.class);

            startActivityForResult(intent, 0);
        });
    }
}
