package com.example.fastcredits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fastcredits.utils.PreferenceStore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide header bar
        ((AppCompatActivity) this).getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
    }

    protected void onStart() {
        super.onStart();

        if (!PreferenceStore.getPersistSession(getApplicationContext())) {
            ViewPager viewPager = findViewById(R.id.viewPager);

            AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
            pagerAdapter.addFragment(new Login());
            pagerAdapter.addFragment(new SignUp());
            viewPager.setAdapter(pagerAdapter);
        } else {
            switch (PreferenceStore.getRolePersisted(getApplicationContext())) {
                case 0: // client
                    startActivity(new Intent(MainActivity.this, UsersDrawerActivity.class));
                    break;
                case 1: // lender
                    startActivity(new Intent(MainActivity.this, DrawerHome.class));
                    break;
                case 2: // router
                    startActivity(new Intent(MainActivity.this, RouterDrawerActivity.class));
                    break;
                case 3: // admin
                    startActivity(new Intent(MainActivity.this, AdminBottomNaivation.class));
                    break;
            }
            Toast.makeText(getApplicationContext(), "Bienvenido de vuelta", Toast.LENGTH_LONG).show();
        }
    }

    class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }
}