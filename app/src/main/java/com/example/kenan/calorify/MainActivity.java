package com.example.kenan.calorify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kenan.calorify.dal.repos.UserRepository;
import com.example.kenan.calorify.dl.enums.Gender;
import com.example.kenan.calorify.dl.models.User;
import com.example.kenan.calorify.fragments.RegisterFragment;
import com.example.kenan.calorify.fragments.WelcomeFragment;
import com.facebook.stetho.Stetho;

public class MainActivity extends FragmentActivity implements WelcomeFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener{

    private UserRepository userRepository;
    private RegisterFragment registerFragment;
    private WelcomeFragment welcomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_page);
        Stetho.initializeWithDefaults(this);
        userRepository = new UserRepository();
        checkExistingFragment();

        //            Button scanButton = (Button) findViewById(R.id.scan_button);
//            scanButton.setOnClickListener((v) -> {
//                //Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
//                //startActivityForResult(intent, 0);
//                try {
//                    FoodServiceImpl foodapi = new FoodServiceImpl();
//                    try {
//                        foodapi.doGetRequest();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                } catch(Exception ex)
//                {
//                    String exx = ex.getMessage();
//                }
//
//            });
    }

    private void checkExistingFragment() {
        registerFragment = (RegisterFragment)getSupportFragmentManager().findFragmentByTag("register");
        welcomeFragment = (WelcomeFragment)getSupportFragmentManager().findFragmentByTag("welcome");

        if (welcomeFragment == null){
            welcomeFragment = WelcomeFragment.newInstance();

            if (activeUserPresent())
                pushFragment(welcomeFragment,"welcome");
            //open de menu pagina
        }
        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance();
        }
    }

    private void pushFragment(Fragment next, String tag){
        if (findViewById(R.id.main_fragment_container) != null) {
            next.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, next, tag)
                    .addToBackStack(null).commit();
        }
    }

    @Override
    public void onFragmentInteraction() {
        pushFragment(registerFragment, "register");
    }

    private boolean activeUserPresent() {
        if (userRepository.getActiveUser() != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRegisterComplete() {
        //show animation
        //open menupage or something like that
    }
}
