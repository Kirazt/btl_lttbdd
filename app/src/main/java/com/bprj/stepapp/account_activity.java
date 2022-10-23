package com.bprj.stepapp;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bprj.stepapp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class account_activity extends Fragment {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReferenceFromUrl("https://movestep-bd7d3-default-rtdb.firebaseio.com/");
    Button withdrawbtn, depositbtn, logoutbtn, fullnamebtn, gmailbtn, agebtn, heightbtn, weightbtn, changepassbtn;
    Dialog dialog;
    TextView tokentext, accid, fullnametext, gmailtext, agetext, heighttext, weighttext;
    int stepmove = 0;
    private DatabaseReference mDatabase;
    String acc_id, name, fullname, gmail, age, height, weight, password;
    boolean isnew = true;
    boolean islogin =true, active = true;
    public static final String SHARED_PREFS = "sharedPrefs";
    LinearLayout editinfo,stepshow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.account_activity, container, false);
        loadData();

        editinfo = view.findViewById(R.id.editinfo);
        stepshow = view.findViewById(R.id.stepshow);
        withdrawbtn = view.findViewById(R.id.withdrawbtn);
        depositbtn = view.findViewById(R.id.depositbtn);
        logoutbtn = view.findViewById(R.id.logoutbtn);
        tokentext = view.findViewById(R.id.tokentext);
        accid = view.findViewById(R.id.acc_id);
        fullnametext = view.findViewById(R.id.fullname);
        gmailtext = view.findViewById(R.id.gmail);
        agetext = view.findViewById(R.id.age);
        heighttext = view.findViewById(R.id.height);
        weighttext = view.findViewById(R.id.weight);
        fullnamebtn = view.findViewById(R.id.fullname_btn);
        gmailbtn = view.findViewById(R.id.gmail_btn);
        agebtn = view.findViewById(R.id.age_btn);
        heightbtn = view.findViewById(R.id.height_btn);
        weightbtn = view.findViewById(R.id.weight_btn);
        changepassbtn = view.findViewById(R.id.changepassbtn);
        dialog = new Dialog(getActivity());
        if(name=="Guest") {
            fullnamebtn.setVisibility(View.INVISIBLE);
            gmailbtn.setVisibility(View.INVISIBLE);
            agebtn.setVisibility(View.INVISIBLE);
            heightbtn.setVisibility(View.INVISIBLE);
            weightbtn.setVisibility(View.INVISIBLE);
            changepassbtn.setVisibility(view.INVISIBLE);
            stepshow.setVisibility(View.GONE);
        }
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
        fullnamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openeditnamedialog();
            }
        });
        gmailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openeditgmaildialog();
            }
        });
        agebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openeditagedialog();
            }
        });
        heightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openeditheightdialog();
            }
        });
        weightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openeditweightdialog();
            }
        });
        changepassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openchangepassdialog();
            }
        });
        return view;

    }

    @Override
    public void onResume() {

        SharedPreferences prefs = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int restoredText = prefs.getInt("stepmove", 0);
        acc_id = prefs.getString("acc_id", null);
        name = prefs.getString("name", null);
        fullname = prefs.getString("fullname", null);
        gmail = prefs.getString("gmail", null);
        age = prefs.getString("age", null);
        height = prefs.getString("height", null);
        weight = prefs.getString("weight", null);
        accid.setText(name);
        tokentext.setText(String.valueOf(restoredText));
        fullnametext.setText(String.valueOf(fullname));
        gmailtext.setText(String.valueOf(gmail));
        agetext.setText(String.valueOf(age));
        heighttext.setText(String.valueOf(height));
        weighttext.setText(String.valueOf(weight));
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

    private void openeditnamedialog() {
        dialog.setContentView(R.layout.fullname_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.closedialog);
        Button accpet = dialog.findViewById(R.id.okbtn);
        Button cancel = dialog.findViewById(R.id.cancelbtn);
        EditText newname = dialog.findViewById(R.id.editfullname);

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
        accpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child("user").child(name).child("fullname").setValue(newname.getText().toString());
                Toast.makeText(accpet.getContext(), "Change name successfull", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("fullname", newname.getText().toString());
                editor.apply();
                fullnametext.setText(sharedPreferences.getString("fullname", null));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openeditgmaildialog() {
        dialog.setContentView(R.layout.gmail_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.closedialog);
        Button accpet = dialog.findViewById(R.id.okbtn);
        Button cancel = dialog.findViewById(R.id.cancelbtn);
        EditText newgmail = dialog.findViewById(R.id.editgmail);

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
        accpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child("user").child(name).child("gmail").setValue(newgmail.getText().toString());
                Toast.makeText(accpet.getContext(), "Change gmail successfull", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("gmail", newgmail.getText().toString());
                editor.apply();
                gmailtext.setText(sharedPreferences.getString("gmail", null));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openeditagedialog() {
        dialog.setContentView(R.layout.age_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.closedialog);
        Button accpet = dialog.findViewById(R.id.okbtn);
        Button cancel = dialog.findViewById(R.id.cancelbtn);
        EditText newage = dialog.findViewById(R.id.editage);

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
        accpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child("user").child(name).child("age").setValue(newage.getText().toString());
                Toast.makeText(accpet.getContext(), "Change age successfull", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("age", newage.getText().toString());
                editor.apply();
                agetext.setText(sharedPreferences.getString("age", null));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openeditheightdialog() {
        dialog.setContentView(R.layout.height_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.closedialog);
        Button accpet = dialog.findViewById(R.id.okbtn);
        Button cancel = dialog.findViewById(R.id.cancelbtn);
        EditText newheight = dialog.findViewById(R.id.editheight);

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
        accpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child("user").child(name).child("height").setValue(newheight.getText().toString());
                Toast.makeText(accpet.getContext(), "Change height successfull", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("height", newheight.getText().toString());
                editor.apply();
                heighttext.setText(sharedPreferences.getString("height", null));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openeditweightdialog() {
        dialog.setContentView(R.layout.weight_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.closedialog);
        Button accpet = dialog.findViewById(R.id.okbtn);
        Button cancel = dialog.findViewById(R.id.cancelbtn);
        EditText newweight = dialog.findViewById(R.id.editweight);

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
        accpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child("user").child(name).child("weight").setValue(newweight.getText().toString());
                Toast.makeText(accpet.getContext(), "Change weight successfull", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("weight", newweight.getText().toString());
                editor.apply();
                weighttext.setText(sharedPreferences.getString("weight", null));
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void openchangepassdialog() {
        dialog.setContentView(R.layout.changepassword_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.closedialog);
        Button accpet = dialog.findViewById(R.id.okbtn);
        Button cancel = dialog.findViewById(R.id.cancelbtn);
        EditText oldpass = dialog.findViewById(R.id.oldpass);
        EditText newpass = dialog.findViewById(R.id.newpass);
        EditText connewpass = dialog.findViewById(R.id.connewpass);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        password = sharedPreferences.getString("password", null);

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
        accpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BCrypt.verifyer().verify(oldpass.getText().toString().toCharArray(), password).verified) {
                    if (newpass.getText().toString().equals(connewpass.getText().toString())) {
                        String hashpass = BCrypt.withDefaults().hashToString(12, newpass.getText().toString().toCharArray());
                        ref.child("user").child(name).child("password").setValue(hashpass);
                        Toast.makeText(accpet.getContext(), "Change password successfull", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("password", hashpass);
                        editor.apply();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(accpet.getContext(), "Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(accpet.getContext(), "Old password is wrong", Toast.LENGTH_SHORT).show();
                }
        }
    });
        dialog.show();
}

    private void openlogoutdialog() {
        dialog.setContentView(R.layout.logout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.closelogoutdialog);
        Button accpetlogout = dialog.findViewById(R.id.logoutbtn);
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
                ref.child("user").child(accid.getText().toString()).child("active").setValue(false);
                isnew = false;
                islogin = false;
                name = "";
                stepmove = 0;
                active = false;
                saveData();
                Intent i = new Intent(getActivity(), login_activity.class);
                startActivity(i);
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

    public void saveData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("acc_id", acc_id);
        editor.putBoolean("isnew", isnew);
        editor.putString("name", name);
        editor.putInt("stepmove", stepmove);
        editor.putBoolean("islogin", islogin);
        editor.apply();
    }

;

public void loadData(){
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        acc_id=sharedPreferences.getString("acc_id",null);
        name=sharedPreferences.getString("name",null);
        stepmove=sharedPreferences.getInt("stepmove",0);
        fullname=sharedPreferences.getString("fullname","empty");
        gmail=sharedPreferences.getString("gmail","empty");
        age=sharedPreferences.getString("age","empty");
        height=sharedPreferences.getString("height","empty");
        weight=sharedPreferences.getString("weight","empty");

        }
        }