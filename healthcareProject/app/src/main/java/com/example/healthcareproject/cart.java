package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class cart extends AppCompatActivity {

    TextView tv;
    ListView lst;
    Button btn1,btn2,btn3,btn4;
    private String[][] packages={};
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        tv=findViewById(R.id.cost);
        btn1=findViewById(R.id.buttondate);
        btn2=findViewById(R.id.buttontime);
        btn3=findViewById(R.id.checkoutcart);
        btn4=findViewById(R.id.cartBack);
        lst=findViewById(R.id.lstcart);
        SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
        String username=sp.getString("username","");
        Database db=new Database(this,"healthcare",null,1);
        float total=0;
        ArrayList dbdata=db.getcart(username,"lab");
        Toast.makeText(this,""+dbdata,Toast.LENGTH_SHORT).show();

        packages=new String[dbdata.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }
        for(int i=0;i<dbdata.size();i++){
            String arrdata=dbdata.get(i).toString();
            String[] strdata=arrdata.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strdata[0];
            packages[i][4]="Cost:"+strdata[1]+"Rs";
            total=total+Float.parseFloat(strdata[1]);
        }
        tv.setText("Total Cost:"+total);
        ArrayAdapter adp1=new ArrayAdapter(this, android.R.layout.simple_list_item_1,packages);
        lst.setAdapter(adp1);

        initDatePicker();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        initTimePicker();
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cart.this,Labtest.class));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(cart.this,labtestbook.class);
                it.putExtra("price",tv.getText().toString());
                it.putExtra("date",btn1.getText().toString());
                it.putExtra("time",btn2.getText().toString());
                startActivity(it);
            }
        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                btn1.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int date=cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog=new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK,dateSetListener,year,month,date);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                btn2.setText(i+":"+i1);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hours=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);
        timePickerDialog=new TimePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK,timeSetListener,hours,mins,true);
    }
}