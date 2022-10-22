package com.bprj.stepapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bprj.stepapp.R;

import org.w3c.dom.Text;

public class Support_Activity extends Fragment {
    Button send;
    TextView email, subject, body;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.support_activity,container,false);
        send =view.findViewById(R.id.sendmail);
        subject = view.findViewById(R.id.subject);
        body = view.findViewById(R.id.body);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(view);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void sendEmail(View view){
        if(subject.getText().toString().isEmpty()|| body.getText().toString().isEmpty()){
            Toast.makeText(getActivity(),
                    "please write something for us", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent email = new Intent(Intent.ACTION_SENDTO);
            email.setData(Uri.parse("mailto:"));
            String[] address = {"1951012067luan@ou.edu.vn"};
            email.putExtra(Intent.EXTRA_EMAIL, address );
            email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
            email.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
            if(email.resolveActivity(getActivity().getPackageManager())!=null){
                startActivity(Intent.createChooser(email, "Send mail..."));
                Log.i("Finished sending...", "");
            } else {
                Toast.makeText(view.getContext(),
                        "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
