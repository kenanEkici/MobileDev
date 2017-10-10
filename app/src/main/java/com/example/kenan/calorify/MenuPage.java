package com.example.kenan.calorify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.kenan.calorify.datalayer.services.FoodServiceImpl;

import java.io.IOException;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        setTitle("Calorify Menu");

        Button scanButton = (Button) findViewById(R.id.scan_button);
        scanButton.setOnClickListener((v) -> {
            //Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
            //startActivityForResult(intent, 0);
            try {
                FoodServiceImpl foodapi = new FoodServiceImpl();
                try {
                    foodapi.doGetRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch(Exception ex)
            {
                String exx = ex.getMessage();
            }

        });
    }
}
