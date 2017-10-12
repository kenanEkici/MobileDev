package com.example.kenan.calorify;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kenan.calorify.dal.repos.UserRepository;
import com.example.kenan.calorify.fragments.adapters.MenuPagerAdapter;
import com.example.kenan.calorify.fragments.RegisterFragment;
import com.example.kenan.calorify.fragments.WelcomeFragment;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener{

    private UserRepository userRepository;
    private RegisterFragment registerFragment;
    private WelcomeFragment welcomeFragment;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_page);
        Stetho.initializeWithDefaults(this);
        userRepository = new UserRepository();
        getSupportActionBar().hide();
        checkExistingFragment();
    }

    private void checkExistingFragment() {
        registerFragment = (RegisterFragment)getSupportFragmentManager().findFragmentByTag("register");
        welcomeFragment = (WelcomeFragment)getSupportFragmentManager().findFragmentByTag("welcome");

        if (welcomeFragment == null) {
            welcomeFragment = WelcomeFragment.newInstance();
            if (!activeUserPresent())
                pushFragment(welcomeFragment,"welcome");
        }
        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance();
        }

        if (activeUserPresent()) {
            getSupportActionBar().show();
            setContentView(R.layout.menu_page);

            mViewPager = (ViewPager) findViewById(R.id.menu_container);
            mViewPager.setAdapter(new MenuPagerAdapter(getSupportFragmentManager()));
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);
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
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    @Override
    public void onRegisterComplete() {
        //show animation
        //open menupage or something like that
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
