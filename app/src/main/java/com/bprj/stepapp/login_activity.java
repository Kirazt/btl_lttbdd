package com.bprj.stepapp;

import androidx.annotation.NonNull;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.UUID;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class login_activity extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://movestep-bd7d3-default-rtdb.firebaseio.com/");
    public static final String SHARED_PREFS = "sharedPrefs";
    Boolean isnew = false;
    String acc_id, name;
    int stepmove = 0;
    Button test, login;
    EditText username, password;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signuplink);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(login_activity.this, "Please enter your username or password.", Toast.LENGTH_SHORT).show();
                } else {
                        databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(username.getText().toString())) {
                                    final String getPassword = snapshot.child(username.getText().toString()).child("password").getValue(String.class);
                                    if (BCrypt.verifyer().verify(password.getText().toString().toCharArray(), getPassword).verified) {
                                        name = username.getText().toString();
                                        stepmove = snapshot.child(username.getText().toString()).child("stepmove").getValue(int.class);
                                        saveData();
                                        Intent i = new Intent(login_activity.this, tab_activity.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(login_activity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(login_activity.this, "Username is not exist", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e("E",error.toString());
                            }
                        });
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login_activity.this, register_activity.class);
                startActivity(i);
                finish();
            }
        });

        test = findViewById(R.id.testaccbtn);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
                acc_id = UUID.randomUUID().toString();
                isnew = true;
                name = "Guest";
                stepmove = 0;
                saveData();
                Intent i = new Intent(login_activity.this, tab_activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("acc_id", acc_id);
        editor.putBoolean("isnew", isnew);
        editor.putString("name", name);
        editor.putInt("stepmove", stepmove);
        editor.apply();
    }

    ;

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        acc_id = sharedPreferences.getString("acc_id", null);
        name = sharedPreferences.getString("name", null);
        stepmove = sharedPreferences.getInt("stepmove", 0);
    }
}