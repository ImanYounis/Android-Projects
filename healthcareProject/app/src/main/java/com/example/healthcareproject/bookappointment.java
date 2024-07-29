package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class bookappointment extends AppCompatActivity {

    TextView tv;
    EditText edt1,edt2,edt3,edt4;
    Button btnbook,btnback,datebtn,timebtn;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookappointment);
        tv=findViewById(R.id.tvtitle);
        edt1=findViewById(R.id.editTexta);
        edt2=findViewById(R.id.editTextc);
        edt3=findViewById(R.id.editTextf);
        edt4=findViewById(R.id.editTextfees);
        btnbook=findViewById(R.id.buttonbook);
        btnback=findViewById(R.id.buttonback);
        datebtn=findViewById(R.id.buttondate);
        timebtn=findViewById(R.id.buttontime);

        edt1.setKeyListener(null);
        edt2.setKeyListener(null);
        edt3.setKeyListener(null);
        edt4.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        String drname=it.getStringExtra("name");
        String contact=it.getStringExtra("contact");
        String address=it.getStringExtra("hospital");
        String fees=it.getStringExtra("fees");
        edt1.setText(address);
        edt2.setText(contact);
        edt3.setText(drname);
        edt4.setText(fees);
        tv.setText(title);

        initDatePicker();
        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        initTimePicker();
        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bookappointment.this,findDoctor.class));
            }
        });
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
                String username=sp.getString("username","");
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkappointment(username,title+"=>"+drname,address,contact,datebtn.getText().toString(),timebtn.getText().toString())==1) {
                    Toast.makeText(bookappointment.this, "Appointment already exists", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addorder(username,title+"=>"+drname,address,contact,0, datebtn.getText().toString(), timebtn.getText().toString(), Float.parseFloat(fees), "appointment");
                    Toast.makeText(bookappointment.this, "order placed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(bookappointment.this, home.class));
                }
            }
        });

    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                datebtn.setText(i2+"/"+i1+"/"+i);
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
                timebtn.setText(i+":"+i1);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hours=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);
        timePickerDialog=new TimePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK,timeSetListener,hours,mins,true);
    }
}