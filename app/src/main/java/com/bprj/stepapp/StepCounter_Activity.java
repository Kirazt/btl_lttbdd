package com.bprj.stepapp;


import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.bprj.stepapp.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class StepCounter_Activity extends Fragment implements SensorEventListener {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SCORE = "score";
    public static final String STEP = "step";
    TextView step,score, currentday, preday3, preday2, preday1, curday, nxday1, nxday2, nxday3, buffstatus;
    SensorManager sensorManager;
    Sensor msensor;
    boolean run = false;
    int stepcount = 0;
    int prestep= 0;
    int fscore = stepcount * 10;
    int buffintoscore;


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

        curday = (TextView) getActivity().findViewById(R.id.curday);
        preday1 = (TextView) getActivity().findViewById(R.id.preday1);
        preday2 = (TextView) getActivity().findViewById(R.id.preday2);
        preday3 = (TextView) getActivity().findViewById(R.id.preday3);
        nxday1 = (TextView) getActivity().findViewById(R.id.nxday1);
        nxday2 = (TextView) getActivity().findViewById(R.id.nxday2);
        nxday3 = (TextView) getActivity().findViewById(R.id.nxday3);
        buffstatus = (TextView) getActivity().findViewById(R.id.buffstatus);
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
        step.setText(""+String.valueOf(prestep));
        score.setText(""+String.valueOf(fscore));

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


    }
    @Override
    public void onResume() {
        super.onResume();
        Sensor count = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        SharedPreferences prefs = getActivity().getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        int buff = prefs.getInt("buffquantity", 0);
        buffintoscore = buff*20;
        Log.e("buff1",String.valueOf(buffintoscore));
        buffstatus.setText("Buff rate: "+String.valueOf(buffintoscore) +"%");
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
            loadData();
            stepcount = (int) (sensorEvent.values[0]);
            int currentstep = stepcount - prestep;
            if(buffintoscore==0){
                fscore = fscore + (currentstep * 10);
            }
            else fscore = fscore + ((currentstep * 10)+(currentstep*10)*buffintoscore/100);
            Log.e("buff",String.valueOf(currentstep));
            step.setText(""+String.valueOf(stepcount));
            score.setText(""+String.valueOf(fscore));
            saveData();
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(STEP, stepcount);
        editor.putInt(SCORE, fscore);
        editor.apply();
    };

    public void loadData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        int stepsave = sharedPreferences.getInt(STEP, 0);
        Log.i("step", String.valueOf(stepsave));
        int scoresave = sharedPreferences.getInt(SCORE, 0);

        prestep =  stepsave;
        Log.i("steplast", String.valueOf(stepcount));
        fscore = scoresave;
    }


}
