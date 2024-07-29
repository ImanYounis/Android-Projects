package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class medicinebook extends AppCompatActivity {

    EditText edt1,edt2,edt3,edt4;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinebook);
        edt1=findViewById(R.id.fullnamebmb);
        edt2=findViewById(R.id.addressbmb);
        edt3=findViewById(R.id.contactbmb);
        edt4=findViewById(R.id.pinbmb);
        btn=findViewById(R.id.buttonbookbmb);
        Intent it=getIntent();
        String[] price=it.getStringExtra("price").split(java.util.regex.Pattern.quote(":"));
        String date=it.getStringExtra("date");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
                String username=sp.getString("username","");
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                db.addorder(username,edt1.getText().toString(),edt2.getText().toString(),edt3.getText().toString(),Integer.parseInt(edt4.getText().toString()),date,"",Float.parseFloat(price[1].toString()),"medicine");
                db.removecart(username,"medicine");
                Toast.makeText(medicinebook.this, "order placed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(medicinebook.this,home.class));
            }
        });
    }
}