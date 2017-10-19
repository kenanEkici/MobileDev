package com.example.kenan.calorify;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kenan.calorify.dal.repos.UserRepository;
import com.example.kenan.calorify.fragments.adapters.MenuPagerAdapter;
import com.example.kenan.calorify.fragments.RegisterFragment;
import com.example.kenan.calorify.fragments.WelcomeFragment;
import com.example.kenan.calorify.helpers.AuthenticationHelper;
import com.facebook.stetho.Stetho;

public class MenuActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //inits
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        getSupportActionBar().show();
        setContentView(R.layout.menu_page);

        AuthenticationHelper.continueToMain(getApplicationContext());
        initMenu();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initMenu() {
        mViewPager = (ViewPager) findViewById(R.id.menu_page_container);
        mViewPager.setAdapter(new MenuPagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }



}
