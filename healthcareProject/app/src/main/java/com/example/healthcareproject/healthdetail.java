package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class healthdetail extends AppCompatActivity {

    ImageView img;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthdetail);
        tv=findViewById(R.id.hatitle);
        btn=findViewById(R.id.hadback);
        img=findViewById(R.id.imgha);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(healthdetail.this,healtharticles.class));
            }
        });
        Intent it=getIntent();
        tv.setText(it.getStringExtra("name"));
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            int resid= bundle.getInt("image");
            img.setImageResource(resid);
        }

    }
}