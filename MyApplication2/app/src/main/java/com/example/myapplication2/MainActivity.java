package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button btn,btngoto,btnmenu;
    EditText edtobt,edtmax;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        btn=(Button) findViewById(R.id.btn);
        edtobt=(EditText) findViewById(R.id.edtobt);
        edtmax=(EditText) findViewById(R.id.edtmax);
        result=(TextView) findViewById(R.id.result);
        btngoto=(Button) findViewById(R.id.btngoto);
        btnmenu=findViewById(R.id.btnmenu);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float om,tm,bmi;
                String r;
                om=Float.parseFloat(edtobt.getText().toString());
                tm=Float.parseFloat(edtmax.getText().toString());
                bmi=tm/(om*om);
                if(bmi<18.5) {
                    r = "Under Weight";
                    result.setTextColor(Color.RED);
                }
                else if(bmi>=18.5&&bmi<=24.9){
                    r="Normal";
                    result.setTextColor(Color.GREEN);
                }
                else if(bmi>=25&&bmi<=29.9){
                    r="Overweight";
                    result.setTextColor(Color.YELLOW);
                }
                else if(bmi>=30&&bmi<=39.9){
                    r="Obesity";
                    result.setTextColor(Color.BLUE);
                }
                else{
                    r="Extreme Obesity";
                    result.setTextColor(Color.RED);
                }

                result.setText(
                        String.valueOf(bmi+" "+r)
                );
            }
        });
    btngoto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String obtmarks=edtobt.getText().toString();
            String maxmarks=edtmax.getText().toString();
            Intent loginintent=new Intent(MainActivity.this,intentdestination.class);
            loginintent.putExtra("obtainedmarks",obtmarks);
            loginintent.putExtra("totalmarks",maxmarks);
            startActivity(loginintent);

        }
    });
    btnmenu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),constraintrel.class));
        }
    });


     /*   Toast.makeText(this, "welcome to my app", Toast.LENGTH_SHORT).show();
        edt=(EditText) findViewById(R.id.edtobt);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=edt.getText().toString();
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                btn.setBackgroundColor(Color.RED);
            }
        });*/
    }

}