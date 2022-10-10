package com.bprj.stepapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register_activity extends AppCompatActivity {


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://movestep-bd7d3-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.register_activity);

        final EditText fullname = findViewById(R.id.fullname);
        final EditText gmail = findViewById(R.id.gmail);
        final EditText password = findViewById(R.id.password);
        final EditText conpassword = findViewById(R.id.conpassword);
        final EditText username = findViewById(R.id.username);
        final Button register = findViewById(R.id.button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullname.getText().toString().isEmpty() || gmail.getText().toString().isEmpty() || password.getText().toString().isEmpty() || conpassword.getText().toString().isEmpty() || username.getText().toString().isEmpty())
                {
                    Toast.makeText(register_activity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!conpassword.getText().toString().equals(password.getText().toString()))
                {
                    Toast.makeText(register_activity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(username.getText().toString())){
                                Toast.makeText(register_activity.this, "Username is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                databaseReference.child("user").child(username.getText().toString()).child("fullname").setValue(fullname.getText().toString());
                                databaseReference.child("user").child(username.getText().toString()).child("gmail").setValue(gmail.getText().toString());
                                databaseReference.child("user").child(username.getText().toString()).child("password").setValue(password.getText().toString());

                                Toast.makeText(register_activity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        TextView signin = findViewById(R.id.signinlink);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(register_activity.this, login_activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
