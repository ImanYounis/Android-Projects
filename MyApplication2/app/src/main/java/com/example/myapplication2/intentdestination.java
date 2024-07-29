package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class intentdestination extends AppCompatActivity {
    TextView r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intentdestination);
        r=findViewById(R.id.r);

        Intent receivingintent=getIntent();
        float om=Float.parseFloat(receivingintent.getStringExtra("obtainedmarks"));
        float tm=Float.parseFloat(receivingintent.getStringExtra("totalmarks"));
        r.setText(String.valueOf(om*100/tm));
    }
}