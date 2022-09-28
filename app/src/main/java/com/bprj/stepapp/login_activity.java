package com.bprj.stepapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.UUID;

public class login_activity extends AppCompatActivity{

    public static final String SHARED_PREFS = "sharedPrefs";
    Boolean isnew= false;
    String acc_id;
    Button test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);

        test = findViewById(R.id.testaccbtn);

        test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadData();
                    if (acc_id == null||acc_id.isEmpty()) {
                        acc_id = UUID.randomUUID().toString();
                        isnew = true;
                        saveData();
                        Intent i = new Intent(login_activity.this, tab_activity.class);
                        startActivity(i);
                        finish();
                    } else {
                        isnew = true;
                        saveData();
                        Intent i = new Intent(login_activity.this, tab_activity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
          );
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("acc_id", acc_id);
        editor.putBoolean("isnew", isnew);
        editor.apply();
    };
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
         acc_id = sharedPreferences.getString("acc_id", null);
    }
}