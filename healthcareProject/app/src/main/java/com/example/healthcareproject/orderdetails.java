package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class orderdetails extends AppCompatActivity {

    private String[][] orderdetails={};
    ListView lst;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        btn=findViewById(R.id.ODBack);
        lst=findViewById(R.id.lstod);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(com.example.healthcareproject.orderdetails.this,home.class));
            }
        });
        SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
        String username=sp.getString("usename","");
        Database db=new Database(getApplicationContext(),"healthcare",null,1);
        ArrayList dbdata=db.getorder(username);
        orderdetails=new String[dbdata.size()][];
        for(int i=0;i<orderdetails.length;i++){
            orderdetails[i]=new String[5];
            String arrdata=dbdata.get(i).toString();
            String[] strdata=arrdata.split(java.util.regex.Pattern.quote("$"));
            orderdetails[i][0]=strdata[0];
            orderdetails[i][4]=strdata[1];
            if(strdata[7].compareTo("medicine")==0){
                orderdetails[i][3]="Del:"+strdata[4];
            }
            else{
                orderdetails[i][3]="Del:"+strdata[4]+" "+strdata[5];
            }
            orderdetails[i][2]="Rs."+strdata[6];
            orderdetails[i][4]=strdata[7];
            ArrayAdapter adp=new ArrayAdapter(this, android.R.layout.simple_list_item_1,orderdetails);
            lst.setAdapter(adp);
        }
    }
}