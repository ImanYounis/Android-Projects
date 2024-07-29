package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Labtest extends AppCompatActivity {

    String[][] packages={{"Package1:Lipid Profile","","","","TotalCost:900"},{"Package2:Blood Chemistry","","","","TotalCost:1900"},
            {"Package3:Thyroid Profile","","","","TotalCost:1690"},{"Package4:Full Body Checkup","","","","TotalCost:9300"},{"Package5:Ultrasound","","","","TotalCost:3900"},};
    String[] packagedetails={"blood glucose fasting\n"+"complete hemogram\n"+"HbA1c\n"+"iron studies\n",
    "hdl\n"+"ldl\n","T3\n"+"T4\n"+"TSH\n","COVID19 Antibody\n","heamoglobin\n"+"Uric Acid(pH)\n"+"Liver function test\n"};
    Button btn,btn1;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtest);
        btn=findViewById(R.id.LTBack);
        lst=findViewById(R.id.lstlab);
        btn1=findViewById(R.id.LTcart);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Labtest.this,home.class));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Labtest.this,cart.class));
            }
        });
        ArrayAdapter adp1=new ArrayAdapter(this, android.R.layout.simple_list_item_1,packages);
        lst.setAdapter(adp1);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(Labtest.this,labtestdetail.class);
                it.putExtra("title",packages[i][0]);
                it.putExtra("pckg",packagedetails[i]);
                it.putExtra("cost",packages[i][1]);
                startActivity(it);
            }
        });
    }
}