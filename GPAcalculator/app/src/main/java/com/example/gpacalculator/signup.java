package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class signup extends AppCompatActivity {

    FirebaseAuth fbauth;
    EditText edt3, edt4;
    Button signupbtn1;
    TextView logintv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fbauth = FirebaseAuth.getInstance();

        edt3 = findViewById(R.id.em1);
        edt4 = findViewById(R.id.pwd1);
        logintv = findViewById(R.id.logintv);
        signupbtn1 = findViewById(R.id.signup1btn);

        signupbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, pwd;
                email = edt3.getText().toString().trim();
                pwd = edt4.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    edt3.setError("email field can't be left empty");
                } else if (TextUtils.isEmpty(pwd)) {
                    edt4.setError("password field can't be left empty");
                } else {
                    fbauth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(signup.this, "Email account created", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), signin.class));

                                    } else {
                                        Toast.makeText(signup.this, "Email creation error\n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        // startActivity(new Intent(signup.this, signin.class));
                                    }
                                }
                            });
                }
            }
        });

        logintv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), signin.class));
            }
        });
    }
}