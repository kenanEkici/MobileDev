package com.example.kenan.calorify;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.kenan.calorify.dal.repos.ConsumedProductRepository;
import com.example.kenan.calorify.dal.repos.DayRepository;
import com.example.kenan.calorify.dal.repos.ScannedProductRepository;
import com.example.kenan.calorify.dal.repos.UserRepository;
import com.example.kenan.calorify.dl.models.ConsumedProduct;
import com.example.kenan.calorify.helpers.AuthenticationHelper;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_settings);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button deleteButton = (Button) findViewById(R.id.delete_profile);
        Button clearButton = (Button) findViewById(R.id.clear_history);

        UserRepository userRepository = new UserRepository();
        ScannedProductRepository productRepository = new ScannedProductRepository();

        deleteButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to delete your profile?")
                    .setCancelable(true)
                    .setPositiveButton("YES", (dialog, id) -> {
                        //clear everything
                        userRepository.deleteUser(userRepository.getActiveUser());
                        ConsumedProductRepository productRepo = new ConsumedProductRepository();
                        DayRepository dayRepo = new DayRepository();
                        productRepo.deleteEverything();
                        dayRepo.deleteEverything();
                        AuthenticationHelper.continueToMain(getApplicationContext());
                    });
            AlertDialog alert = builder.create();
            alert.show();
        });

        clearButton.setOnClickListener(v -> {
            productRepository.deleteEverything();
            Toast.makeText(getApplicationContext(), "History is no more!", Toast.LENGTH_SHORT).show();
            AuthenticationHelper.continueToMenu(getApplicationContext());
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
