package com.example.kenan.calorify;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kenan.calorify.fragments.RegisterFragment;
import com.example.kenan.calorify.fragments.WelcomeFragment;
import com.example.kenan.calorify.helpers.AuthenticationHelper;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.OnFragmentInteractionListener,
        RegisterFragment.OnFragmentInteractionListener {

    private RegisterFragment registerFragment;
    private WelcomeFragment welcomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        getSupportActionBar().hide();

        AuthenticationHelper.continueToMenu(getApplicationContext());
        checkFragments();
    }

    private void checkFragments() {
        registerFragment = (RegisterFragment)getSupportFragmentManager().findFragmentByTag("register");
        welcomeFragment = (WelcomeFragment)getSupportFragmentManager().findFragmentByTag("welcome");

        if (welcomeFragment == null) {
            welcomeFragment = WelcomeFragment.newInstance();
            pushFragment(welcomeFragment, "welcome");
        }

        if (registerFragment == null)
            registerFragment = RegisterFragment.newInstance();
    }

    private void pushFragment(Fragment next, String tag){
        if (findViewById(R.id.main_page_container) != null) {
            next.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_page_container, next, tag)
                    .addToBackStack(null).commit();
        }
    }

    @Override
    public void onRegisterButtonClicked() {
        pushFragment(registerFragment, "register");
    }

    @Override
    public void onRegisterComplete() {
        Toast.makeText(getApplicationContext(), getString(R.string.created_user), Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            AuthenticationHelper.continueToMenu(getApplicationContext());
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }
}
