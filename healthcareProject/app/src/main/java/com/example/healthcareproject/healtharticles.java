package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class healtharticles extends AppCompatActivity {

    ListView lst;
    Button btn;
    private String[][] healtharticles={{"Walking Daily","","","","Click for more details"},{"Healthy Gut","","","","Click for more details"},
            {"Stop Smoking","","","","Click for more details"},{"COVID-19","","","","Click for more details"},{"Helathy LifeStyle","","","","Click for more details"}};
    private int[] images={R.drawable.walk,R.drawable.gut,R.drawable.smoking,R.drawable.covid19,R.drawable.lifestyle};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healtharticles);
        btn=findViewById(R.id.haback);
        lst=findViewById(R.id.lstha);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(healtharticles.this,home.class));
            }
        });
        ArrayAdapter adp=new ArrayAdapter(this, android.R.layout.simple_list_item_1,healtharticles);
        lst.setAdapter(adp);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(getApplicationContext(),healthdetail.class);
                it.putExtra("name",healtharticles[i][0]);
                it.putExtra("image",images[i]);
            }
        });
    }
}