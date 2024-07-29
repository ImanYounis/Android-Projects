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

public class medicinedetail extends AppCompatActivity {

    EditText edtdetail;
    Button btn,btn1;
    TextView tv,tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinedetail);
        edtdetail=findViewById(R.id.eddetailbmd);
        btn=findViewById(R.id.BMDBack);
        btn1=findViewById(R.id.BMDcart);
        tv=findViewById(R.id.bmdname);
        tv1=findViewById(R.id.costbmd);
        edtdetail.setKeyListener(null);
        Intent it=getIntent();
        String title=it.getStringExtra("title");
        String details=it.getStringExtra("medicine");
        String cost=it.getStringExtra("cost");
        tv.setText(title);
        tv1.setText("Total Cost:"+cost);
        edtdetail.setText(details);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(medicinedetail.this,buymedicine.class));
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
                    Toast.makeText(medicinedetail.this, "Product already exists in your cart", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addtocart(username,product,price,"medicine");
                    Toast.makeText(medicinedetail.this, "Product added to cart successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(medicinedetail.this,buymedicine.class));
                }
            }
        });
    }
}