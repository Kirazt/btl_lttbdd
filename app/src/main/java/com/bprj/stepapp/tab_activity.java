package com.bprj.stepapp;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class tab_activity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    TextView step,score;
    SensorManager sensorManager;
    Sensor msensor;
    Button reset;
    boolean run = false;
    int stepcount = 0;
    int fscore = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
            }
        }
        setContentView(R.layout.tab_activity);

        viewPager = findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.navbottom);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.homebtn).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.itembtn).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.accbtn).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.spbtn).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homebtn:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.itembtn:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.accbtn:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.spbtn:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }




    //    @Override
//    public void onBackPressed(){
//        FragmentManager fm = getFragmentManager();
//        if (fm.getBackStackEntryCount() > 1) {
//            Log.i("MainActivity", "popping backstack");
//            fm.popBackStack();
//        } else {
//            Log.i("MainActivity", "nothing on backstack, calling super");
//            super.onBackPressed();
//        }
//    }

}
