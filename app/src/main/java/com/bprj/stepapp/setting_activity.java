package com.bprj.stepapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class setting_activity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    Switch notifiswicth;
    TextView point_tv, accid_tv, step_tv,logout,privacy_tv, faq_tv;
    String acc_id, name;
    Button back;
    boolean notifistatus = true, isnew = true, active = true;
    Dialog dialog;
    int step =0,scoresave=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.setting_activity);
        loadData();

        faq_tv = findViewById(R.id.Faq);
        privacy_tv = findViewById(R.id.privacy);
        logout = findViewById(R.id.logout_sett);
        back = findViewById(R.id.backhome_btn);
        notifiswicth = findViewById(R.id.notifiswicth);
        point_tv = findViewById(R.id.point_sett);
        accid_tv = findViewById(R.id.id_sett);
        step_tv = findViewById(R.id.steps_sett);
        dialog = new Dialog(this);
        point_tv.setText(""+scoresave);
        accid_tv.setText(""+acc_id);
        step_tv.setText(""+step);

        if (notifistatus==true){
            notifiswicth.setChecked(true);
        }else notifiswicth.setChecked(false);

        notifiswicth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (notifiswicth.isChecked()==true){
                    notifistatus = true;
                }else notifistatus = false;
                saveData();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogoutdialog();
            }
        });
        super.onCreate(savedInstanceState);
        privacy_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com/"));
                startActivity(intent);
            }
        });
        faq_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.faq_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ImageView imageView = dialog.findViewById(R.id.closefaqdialog);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void openlogoutdialog() {
        dialog.setContentView(R.layout.logout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.closelogoutdialog);
        Button accpetlogout= dialog.findViewById(R.id.logoutbtn);
        Button cancel = dialog.findViewById(R.id.cancelbtn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        accpetlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isnew =false;
                name = "";
                active = false;
                saveData();
                Intent i = new Intent(setting_activity.this, login_activity.class);
                startActivity(i);
            }
        });
        dialog.show();
    }
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("notifi",notifistatus);
        editor.putBoolean("isnew", isnew);
        editor.putString("name", name);
        editor.putBoolean("active", active);
        editor.apply();
    };
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        notifistatus = sharedPreferences.getBoolean("notifi",true);
        step = sharedPreferences.getInt("step", 0);
        scoresave = sharedPreferences.getInt("score", 0);
        acc_id = sharedPreferences.getString("acc_id", null);
        name = sharedPreferences.getString("name",null);
    }
}
