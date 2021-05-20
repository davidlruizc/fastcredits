package com.example.fastcredits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

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

        if (true) {
            ViewPager viewPager = findViewById(R.id.viewPager);

            AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
            pagerAdapter.addFragment(new Login());
            pagerAdapter.addFragment(new SignUp());
            viewPager.setAdapter(pagerAdapter);
        } else {
            startActivity(new Intent(MainActivity.this, DrawerHome.class));
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