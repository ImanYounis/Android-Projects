package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class buymedicine extends AppCompatActivity {

    String[][] medicines={{"Norvasc 5mg","","","","900"},{"Promivit 250mg","","","","1900"},
            {"Ciproxin 500mg","","","","1690"},{"Panadol","","","","300"},{"MaxCran","","","","900"},};
    String[] medicinedetails={"reduces hypertension\n"+"complete hemogram\n"+"HbA1c\n"+"iron studies\n",
            "improves vitamin D deficiency\n"+"improves blood circulation\n","antibiotic for all types of infections\n"+"gastro infections\n"+"fever\n","pain killer\n","no side effects and natural cranberry extracts\n"+"improves kidney function\n"+"improves Liver function\n"};
    Button btn,btn1;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buymedicine);
        btn=findViewById(R.id.BMBack);
        btn1=findViewById(R.id.BMcart);
        lst=findViewById(R.id.lstBM);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(buymedicine.this,home.class));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(buymedicine.this,medicinecart.class));
            }
        });
        ArrayAdapter adp1=new ArrayAdapter(this, android.R.layout.simple_list_item_1,medicines);
        lst.setAdapter(adp1);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(buymedicine.this,medicinedetail.class);
                it.putExtra("title",medicines[i][0]);
                it.putExtra("medicine",medicinedetails[i]);
                it.putExtra("cost",medicines[i][4]);
                startActivity(it);
            }
        });

    }
}