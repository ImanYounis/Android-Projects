package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class labtestbook extends AppCompatActivity {

    EditText edt1,edt2,edt3,edt4;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtestbook);
        edt1=findViewById(R.id.fullname);
        edt2=findViewById(R.id.address);
        edt3=findViewById(R.id.contact);
        edt4=findViewById(R.id.pin);
        btn=findViewById(R.id.buttonLbook);
        Intent it=getIntent();
        String[] price=it.getStringExtra("price").split(java.util.regex.Pattern.quote(":"));
        String date=it.getStringExtra("date");
        String time=it.getStringExtra("time");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
                String username=sp.getString("username","");
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                db.addorder(username,edt1.getText().toString(),edt2.getText().toString(),edt3.getText().toString(),Integer.parseInt(edt4.getText().toString()),date,time,Float.parseFloat(price[1].toString()),"lab");
                db.removecart(username,"lab");
                Toast.makeText(labtestbook.this, "order placed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(labtestbook.this,home.class));
            }
        });
    }
}