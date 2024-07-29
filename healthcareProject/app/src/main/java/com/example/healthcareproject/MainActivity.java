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

public class MainActivity extends AppCompatActivity {

    EditText edt1,edt2;
    TextView tv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        edt1=findViewById(R.id.editText);
        edt2=findViewById(R.id.editText2);
        tv=findViewById(R.id.textView3);
        Database db=new Database(getApplicationContext(),"healthcare",null,1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edt1.getText().toString();
                String password=edt2.getText().toString();
                if(username.length()==0 || password.length()==0)
                {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(db.login(username,password)==1){
                        SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
                        SharedPreferences.Editor spedit=sp.edit();
                        spedit.putString("username",username);
                        spedit.apply();
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,home.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this, "wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Signup.class));
            }
        });
    }
}