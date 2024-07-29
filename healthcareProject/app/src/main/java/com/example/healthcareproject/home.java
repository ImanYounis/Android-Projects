package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
        String username=sp.getString("username","");
        Toast.makeText(this, "Welcome "+username, Toast.LENGTH_SHORT).show();
        CardView exit=findViewById(R.id.logout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(home.this,MainActivity.class));
            }
        });
        CardView labtest=findViewById(R.id.labtest);
        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(home.this,Labtest.class));
            }
        });
        CardView findDoctor=findViewById(R.id.bookappointment);
        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,findDoctor.class));
            }
        });
        CardView buymedicine=findViewById(R.id.buymedicine);
        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,buymedicine.class));
            }
        });
        CardView orders=findViewById(R.id.orderdetails);
        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,orderdetails.class));
            }
        });
        CardView articles=findViewById(R.id.healtharticles);
        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,healtharticles.class));
            }
        });
    }
}