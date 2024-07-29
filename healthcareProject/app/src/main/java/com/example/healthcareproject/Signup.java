package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText edtu,edte,edtp,edtc;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edtu=findViewById(R.id.editTextu);
        edte=findViewById(R.id.editTexte);
        edtp=findViewById(R.id.editTextp);
        edtc=findViewById(R.id.editTextc);
        tv=findViewById(R.id.textView3);
        btn=findViewById(R.id.button);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,MainActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edtu.getText().toString();
                String email=edte.getText().toString();
                String password=edtp.getText().toString();
                String confirm=edtc.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0||password
                        .length()==0||confirm.length()==0||email.length()==0) {
                    Toast.makeText(Signup.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.compareTo(confirm)==0){
                        if(password.length()>=8){
                            db.register(username,email,password);
                            Toast.makeText(Signup.this, "User registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Signup.this,home.class));
                        }
                        else{
                            Toast.makeText(Signup.this, "password must be 8 characters long", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Signup.this, "password and confirm password should be same", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}