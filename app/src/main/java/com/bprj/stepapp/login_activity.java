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

import com.bprj.stepapp.ranking.rank;
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
    String acc_id, name, fullname, gmail, age, height, weight, pass, gender;
    int stepmove = 0;
    Button test, login;
    EditText username, password;
    TextView signup;
    Boolean islogin = false, active = false;

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
                                 if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                                     Toast.makeText(login_activity.this, "Please enter your username or password.", Toast.LENGTH_SHORT).show();
                                 } else {
                                     databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                                         @Override
                                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                                             if (snapshot.hasChild(username.getText().toString())) {
                                                 if (snapshot.child(username.getText().toString()).child("active").getValue(Boolean.class) == false) {
                                                     final String getPassword = snapshot.child(username.getText().toString()).child("password").getValue(String.class);
                                                     final Boolean checkactive = snapshot.child(username.getText().toString()).child("active").getValue(Boolean.class);
                                                     if (checkactive == false) {
                                                         if (BCrypt.verifyer().verify(password.getText().toString().toCharArray(), getPassword).verified) {
                                                             name = username.getText().toString();
                                                             fullname = snapshot.child(username.getText().toString()).child("fullname").getValue(String.class);
                                                             stepmove = snapshot.child(username.getText().toString()).child("stepmove").getValue(int.class);
                                                             gmail = snapshot.child(username.getText().toString()).child("gmail").getValue(String.class);
                                                             age = snapshot.child(username.getText().toString()).child("age").getValue(String.class);
                                                             height = snapshot.child(username.getText().toString()).child("height").getValue(String.class);
                                                             weight = snapshot.child(username.getText().toString()).child("weight").getValue(String.class);
                                                             pass = snapshot.child(username.getText().toString()).child("password").getValue(String.class);
                                                             gender = snapshot.child(username.getText().toString()).child("gender").getValue(String.class);
                                                             active = true;
                                                             databaseReference.child("user").child(name).child("active").setValue(active);
                                                             islogin = true;
                                                             saveData();
                                                             Intent i = new Intent(login_activity.this, tab_activity.class);
                                                             startActivity(i);
                                                             finish();
                                                         } else {
                                                             Toast.makeText(login_activity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                                         }
                                                     } else {
                                                         Toast.makeText(login_activity.this, "Account is already logged in by another user", Toast.LENGTH_SHORT).show();
                                                     }

                                                 } else {
                                                     Toast.makeText(login_activity.this, "Account is already logged in by another user", Toast.LENGTH_SHORT).show();
                                                 }
                                             } else {
                                                 Toast.makeText(login_activity.this, "Username is not exist", Toast.LENGTH_SHORT).show();
                                             }
                                         }

                                         @Override
                                         public void onCancelled(@NonNull DatabaseError error) {
                                             Log.e("E", error.toString());
                                         }
                                     });
                                 }
                             }


                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {
                             Log.e("E", error.toString());
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

        test =findViewById(R.id.testaccbtn);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acc_id = UUID.randomUUID().toString();
                isnew = true;
                name = "Guest";
                fullname = "Guest";
                gmail = "No mail";
                age = "0";
                height = "0";
                weight = "0";
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
        editor.putString("fullname", fullname);
        editor.putString("gmail", gmail);
        editor.putString("age", age);
        editor.putString("height", height);
        editor.putString("weight", weight);
        editor.putString("password", pass);
        editor.putBoolean("islogin", islogin);
        editor.putString("gender", gender);
        editor.putBoolean("active", active);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
    }
}