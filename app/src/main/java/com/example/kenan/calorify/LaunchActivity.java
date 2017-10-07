package com.example.kenan.calorify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.kenan.calorify.datalayer.models.Gender;
import com.example.kenan.calorify.datalayer.models.User;
import com.example.kenan.calorify.datalayer.repos.UserRepository;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

public class LaunchActivity extends AppCompatActivity {

    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        userRepository = new UserRepository();

        if (userRepository.getActiveUser() != null){
            Intent intent = new Intent(this,MenuPage.class);
            startActivity(intent);
        } else{
            Intent intent = new Intent(this,StartActivity.class);
            startActivity(intent);
        }

        finish();
    }
}
