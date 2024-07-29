package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class labtestdetail extends AppCompatActivity {

    EditText edtdetail;
    Button btn,btn1;
    TextView tv,tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtestdetail);
        edtdetail=findViewById(R.id.eddetail);
        btn=findViewById(R.id.LTDBack);
        btn1=findViewById(R.id.LTDcart);
        tv=findViewById(R.id.testtype);
        tv1=findViewById(R.id.cost);
        edtdetail.setKeyListener(null);
        Intent it=getIntent();
        String title=it.getStringExtra("title");
        String details=it.getStringExtra("pckg");
        String cost=it.getStringExtra("cost");
        tv.setText(title);
        tv1.setText("Total Cost:"+cost);
        edtdetail.setText(details);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(labtestdetail.this,Labtest.class));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
                String username=sp.getString("username","");
                String product=title;
                float price=Float.parseFloat(cost);
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if (db.checkcart(username, product)==1) {
                    Toast.makeText(labtestdetail.this, "Product already exists in your cart", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addtocart(username,product,price,"lab");
                    Toast.makeText(labtestdetail.this, "Product added to cart successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(labtestdetail.this,Labtest.class));
                }
            }
        });
    }
}