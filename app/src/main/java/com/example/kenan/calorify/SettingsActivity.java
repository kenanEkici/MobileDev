package com.example.kenan.calorify;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import com.example.kenan.calorify.dal.repos.UserRepository;
import com.example.kenan.calorify.helpers.AuthenticationHelper;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_settings);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button deleteButton = (Button) findViewById(R.id.delete_profile);
        UserRepository userRepository = new UserRepository();

        deleteButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to delete your profile?")
                    .setCancelable(true)
                    .setPositiveButton("YES", (dialog, id) -> {
                        userRepository.deleteUser(userRepository.getActiveUser());
                        AuthenticationHelper.continueToMain(getApplicationContext());
                    });
            AlertDialog alert = builder.create();
            alert.show();
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
