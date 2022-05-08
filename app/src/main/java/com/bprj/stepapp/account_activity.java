package com.bprj.stepapp;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bprj.stepapp.R;

public class account_activity extends Fragment {

    Button withdrawbtn, depositbtn, logoutbtn;
    Dialog dialog;
    TextView tokentext, acc_id;
    public static final String SHARED_PREFS = "sharedPrefs";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.account_activity, container, false);


        withdrawbtn = view.findViewById(R.id.withdrawbtn);
        depositbtn = view.findViewById(R.id.depositbtn);
        logoutbtn = view.findViewById(R.id.logoutbtn);
        tokentext = view.findViewById(R.id.tokentext);
        acc_id = view.findViewById(R.id.acc_id);
        dialog = new Dialog(getActivity());

        withdrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openwithdrawdialog();
            }
        });
        depositbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendepositdialog();
            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogoutdialog();
            }
        });

        return view;

    }

    @Override
    public void onResume() {
        SharedPreferences prefs = getActivity().getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        int restoredText = prefs.getInt("score", 0);
        String accid = prefs.getString("acc_id", null);

        acc_id.setText(accid);
        tokentext.setText(String.valueOf(restoredText));
        super.onResume();
    }

    private void opendepositdialog() {
        dialog.setContentView(R.layout.deposit_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.closedialogdeposit);
        Button accpetdeposit = dialog.findViewById(R.id.depositbtn);
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
        accpetdeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        dialog.show();
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

            }
        });
        dialog.show();
    }

    private void openwithdrawdialog() {
        dialog.setContentView(R.layout.withdraw_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.closedialogwithdraw);
        Button accpetwithdraw = dialog.findViewById(R.id.withdrawbtn);
        Button cancel = dialog.findViewById(R.id.cancelbtn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        accpetwithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}