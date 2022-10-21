package com.bprj.stepapp;


import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.bprj.stepapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StepCounter_Activity extends Fragment implements SensorEventListener {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReferenceFromUrl("https://movestep-bd7d3-default-rtdb.firebaseio.com/");
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String STEP = "step";
    TextView step,score, currentday, preday3, preday2, preday1, curday, nxday1, nxday2, nxday3, buffstatus,info;
    SensorManager sensorManager;
    SensorEvent sensorEvent;
    Sensor msensor;
    String name;

    boolean run = false;
    int stepcount = 0;
    int prestep= 0;
    int laststep=0;
    int n=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stepcounter_activity,container,false);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Date date = new Date();
        SimpleDateFormat formatter ;
        String strDate ;
        Dialog dialog = new Dialog(this.getActivity());

        curday = (TextView) getActivity().findViewById(R.id.curday);
        preday1 = (TextView) getActivity().findViewById(R.id.preday1);
        preday2 = (TextView) getActivity().findViewById(R.id.preday2);
        preday3 = (TextView) getActivity().findViewById(R.id.preday3);
        nxday1 = (TextView) getActivity().findViewById(R.id.nxday1);
        nxday2 = (TextView) getActivity().findViewById(R.id.nxday2);
        nxday3 = (TextView) getActivity().findViewById(R.id.nxday3);
        step = (TextView) getActivity().findViewById(R.id.stepcounter);
        currentday = (TextView) getActivity().findViewById(R.id.currentday);
        score = (TextView) getActivity().findViewById(R.id.convertscore);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);


        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            msensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            run = true;
        }else{
            Toast.makeText(getActivity(),"Counter Sensor is not Present", Toast.LENGTH_SHORT).show();
            run = false;
        }
        loadData();
        step.setText(""+String.valueOf(stepcount));
        score.setText(""+String.valueOf(prestep));

        //Text currentday on top
        formatter = new SimpleDateFormat("EEEE, dd MMMM");
        strDate = formatter.format(date);
        currentday.setText(strDate);

        //Text day in progressbar

        LocalDate date1 = LocalDate.now();
        curday.setText(String.valueOf(date1.getDayOfMonth()));
        preday1.setText(String.valueOf(date1.minusDays(1).getDayOfMonth()));
        preday2.setText(String.valueOf(date1.minusDays(2).getDayOfMonth()));
        preday3.setText(String.valueOf(date1.minusDays(3).getDayOfMonth()));
        nxday1.setText(String.valueOf(date1.plusDays(1).getDayOfMonth()));
        nxday2.setText(String.valueOf(date1.plusDays(2).getDayOfMonth()));
        nxday3.setText(String.valueOf(date1.plusDays(3).getDayOfMonth()));
        ////////////////////////////////

//        info.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.setContentView(R.layout.guide_introduction);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                ImageView imageView = dialog.findViewById(R.id.closeitrodialog);
//                imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//            }
//        });

    }
    @Override
    public void onResume() {
        super.onResume();
        Sensor count = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        SharedPreferences prefs = getActivity().getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        if(count!=null){
            sensorManager.registerListener(this, count,SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.unregisterListener(this,msensor);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(run){
            laststep = (int) (sensorEvent.values[0]);
            saveData();
            if(n>laststep){
                stepcount = (int) (sensorEvent.values[0]);
            }else{
                stepcount = (int) (sensorEvent.values[0]) - n;
            }
            step.setText(""+String.valueOf(stepcount));
            score.setText(""+String.valueOf(stepcount + prestep));

            ref.child("user").child(name).child("stepmove").setValue(stepcount+ prestep);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("last", laststep);
        editor.apply();
    };

    public void loadData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        int stepsave = sharedPreferences.getInt("stepmove", 0);
        n = sharedPreferences.getInt("laststep",0);
        name = sharedPreferences.getString("name", null);
        prestep =  stepsave;
    }


}
