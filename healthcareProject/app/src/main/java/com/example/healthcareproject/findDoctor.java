package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class findDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        CardView exit=findViewById(R.id.FDback);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),home.class));
            }
        });
        CardView family=findViewById(R.id.physician);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(findDoctor.this,doctordetail.class);
                intent.putExtra("title","Family Physician");
                startActivity(intent);
            }
        });
        CardView dietician=findViewById(R.id.dietician);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(findDoctor.this,doctordetail.class);
                intent.putExtra("title","Dietician");
                startActivity(intent);
            }
        });
        CardView surgeon=findViewById(R.id.surgeon);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(findDoctor.this,doctordetail.class);
                intent.putExtra("title","Surgeon");
                startActivity(intent);
            }
        });
        CardView cardio=findViewById(R.id.cardiologist);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(findDoctor.this,doctordetail.class);
                intent.putExtra("title","Cardiologist");
                startActivity(intent);
            }
        });
        CardView dentist=findViewById(R.id.dentist);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(findDoctor.this,doctordetail.class);
                intent.putExtra("title","Dentist");
                startActivity(intent);
            }
        });
    }
}