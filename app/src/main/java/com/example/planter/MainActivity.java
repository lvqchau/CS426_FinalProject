package com.example.planter;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    public static boolean mTwoPane;
    public static String usernameAccount;

    public static String TAG = "myApp";
    TextView username;
    String curAccount;

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

        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("Username")) {
            curAccount = extras.getString("Username");
        }
        username = findViewById(R.id.username);
        username.setText(curAccount);
        usernameAccount = curAccount;

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
