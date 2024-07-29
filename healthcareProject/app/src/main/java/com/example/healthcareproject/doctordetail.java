package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class doctordetail extends AppCompatActivity {
    TextView tv;
    ListView lst;
    Button btn;
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String>item;

    private final String[][] physicians={{"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"}};
    private final String[][] dentist={{"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"}};
    private final String[][] cardiologist={{"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"}};
    private final String[][] dietician={{"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"}};
    private final String[][] surgeon={{"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"},
            {"Doctor Name: Me","Hospital:JNH","Experience:5yrs","ContactNo:+923181234567","Consultation Fees:600RS"}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctordetail);
        tv=findViewById(R.id.doctortype);
        btn=findViewById(R.id.DDBack);
        lst=findViewById(R.id.lst);

        String[][] doctordetails={};
        Intent it=getIntent();
        String title= it.getStringExtra("title");
        tv.setText(title);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(doctordetail.this,findDoctor.class));
            }
        });
        if(title.compareTo("Family Physician")==0){
            doctordetails=physicians;
        } else if (title.compareTo("Dietician")==0) {
            doctordetails=dietician;
        }
        else if (title.compareTo("Cardiologist")==0) {
            doctordetails=cardiologist;
        }
        else if (title.compareTo("Dentist")==0) {
            doctordetails=dentist;
        }
        else if (title.compareTo("Surgeon")==0) {
            doctordetails=surgeon;
        }
        final String[][] finaldoctors=doctordetails;
        list=new ArrayList();

        for(int i=0;i<doctordetails.length;i++){
           item=new HashMap<String,String>();
            item.put("line1",doctordetails[i][0]);
            item.put("line2",doctordetails[i][1]);
            item.put("line3",doctordetails[i][2]);
            item.put("line4",doctordetails[i][3]);
            item.put("line5",doctordetails[i][4]);
            list.add(item);
        }
        sa= new SimpleAdapter(this,list,R.layout.multilist,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});
       // ArrayAdapter adp=new ArrayAdapter(doctordetail.this, android.R.layout.simple_list_item_1,doctordetails);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(doctordetail.this,bookappointment.class);
                it.putExtra("title",title);
                it.putExtra("fees",finaldoctors[i][4]);
                it.putExtra("contact",finaldoctors[i][3]);
                it.putExtra("hospital",finaldoctors[i][1]);
                it.putExtra("name",finaldoctors[i][0]);
                startActivity(it);
            }
        });
    }
}