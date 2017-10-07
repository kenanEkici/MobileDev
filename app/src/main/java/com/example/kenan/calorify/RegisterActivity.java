package com.example.kenan.calorify;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kenan.calorify.R;
import com.example.kenan.calorify.datalayer.models.Gender;
import com.example.kenan.calorify.datalayer.models.User;
import com.example.kenan.calorify.datalayer.repos.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create a new profile");

        setContentView(R.layout.activity_register);

        Gender[] genders = { Gender.Male, Gender.Female, Gender.Other };
        Spinner gender = (Spinner) findViewById(R.id.gender);
        ArrayAdapter<Gender> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, genders);
        gender.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.create_profile);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tryCreate()) {
                    Intent intent = new Intent(getApplicationContext(), LaunchActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Foutje", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean tryCreate(){
        UserRepository repository = new UserRepository();
        try{
            EditText name = (EditText) findViewById(R.id.full_name);
            EditText age = (EditText) findViewById(R.id.age);
            EditText weight = (EditText) findViewById(R.id.weight);
            EditText height = (EditText) findViewById(R.id.height);
            Spinner spinner = (Spinner) findViewById(R.id.gender);

            User user = new User(name.getText().toString(),(Gender.valueOf(spinner.getSelectedItem().toString()))
                    ,Double.parseDouble(weight.getText().toString()),Double.parseDouble(height.getText().toString()),
                    Integer.parseInt(age.getText().toString()),true);
            repository.addUser(user);
            return true;
        } catch (Exception ex){
            return false;
        }
    }
}
