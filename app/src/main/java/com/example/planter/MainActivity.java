package com.example.planter;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
//    private InterstitialAd mInterstitialAd;
    public static boolean mTwoPane;
    public static String TAG = "myApp";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new PlantFragment();
                    if (mTwoPane==true) {
                        findViewById(R.id.item_detail_container).setVisibility(View.GONE);
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            fragment).commit();
                    return true;
                case R.id.navigation_trans:
                    fragment = new PlantFragment();
                    if (mTwoPane==true) {
                        findViewById(R.id.item_detail_container).setVisibility(View.GONE);
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            fragment).commit();
                    return true;
                case R.id.navigation_stat:
                    fragment = new PlantFragment();
                    if (mTwoPane==true) {
                        findViewById(R.id.item_detail_container).setVisibility(View.GONE);
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            fragment).commit();
                    return true;
                case R.id.navigation_plant:
                    fragment = new PlantFragment();
                    if (mTwoPane==true) {
                        findViewById(R.id.item_detail_container).setVisibility(View.GONE);
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            fragment).commit();
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new PlantFragment()).commit();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
