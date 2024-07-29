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

public class signin extends AppCompatActivity {

    FirebaseAuth fbauth;
    EditText edt1,edt2;
    Button loginbtn;
    TextView signuptv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        fbauth=FirebaseAuth.getInstance();

        edt1=findViewById(R.id.em);
        edt2=findViewById(R.id.pwd);
        loginbtn=findViewById(R.id.loginbtn);
        signuptv=findViewById(R.id.signuptv);

        if(fbauth.getCurrentUser()!=null)
            startActivity(new Intent(getApplicationContext(),MainActivity.class));


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,pwd;
                email=edt1.getText().toString().trim();
                pwd=edt2.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    edt1.setError("email field can't be left empty");
                } else if (TextUtils.isEmpty(pwd)) {
                    edt2.setError("password field can't be left empty");
                }
                else{
                    fbauth.signInWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(signin.this, "login was succcessful", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                    }
                                }
                            });

                }
            }
        });



        signuptv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),signup.class));
            }
        });
    }
}