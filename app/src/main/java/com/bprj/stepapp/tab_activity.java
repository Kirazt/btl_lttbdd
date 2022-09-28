package com.bprj.stepapp;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.bprj.stepapp.Item.itembag_activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class tab_activity extends AppCompatActivity {

    private ViewPager viewPager;
    public static final String SHARED_PREFS = "sharedPrefs";
    private BottomNavigationView bottomNavigationView;
    TextView step,score;
    SensorManager sensorManager;
    Sensor msensor;
    Button reset,setting_btn;
    boolean run = false,notifistatus = true;
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
        setting_btn = findViewById(R.id.setting_btn);
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

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tab_activity.this, setting_activity.class);
                startActivity(intent);
            }
        });

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        notifistatus = sharedPreferences.getBoolean("notifi",true);
    }



    @Override
    public void onDestroy() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        super.onDestroy();
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
