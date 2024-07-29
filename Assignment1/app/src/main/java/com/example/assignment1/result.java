package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class result extends AppCompatActivity {
    TextView signintv;
    Button signupbtn;
    EditText edt3,edt4;
    FirebaseAuth fbauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        signupbtn=findViewById(R.id.signupbtn);
        signintv=findViewById(R.id.signintv);
        edt3=findViewById(R.id.edtemail);
        edt4=findViewById(R.id.edtpwd);
        fbauth=FirebaseAuth.getInstance();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt3.getText().toString().trim();
                String pwd = edt4.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    edt3.setError("email field can't be left empty");
                } else if (TextUtils.isEmpty(pwd)) {
                    edt4.setError("password field can't be left empty");
                } else {
                    fbauth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(result.this, "signup successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), listviews.class));
                            } else {
                                Toast.makeText(result.this, "signup insuccessful" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            Intent receivingintent=getIntent();
                            signintv.setText(receivingintent.getStringExtra("name")+receivingintent.getStringExtra("pwd"));
                        }
                    });
                }
            }
        });
       // Intent receivingintent=getIntent();
      /*  float q1=Float.parseFloat(receivingintent.getStringExtra("q1m"));
        float q2=Float.parseFloat(receivingintent.getStringExtra("q2m"));
        float q3=Float.parseFloat(receivingintent.getStringExtra("q3m"));
        float q4=Float.parseFloat(receivingintent.getStringExtra("q4m"));
        float a1=Float.parseFloat(receivingintent.getStringExtra("a1m"));
        float a2=Float.parseFloat(receivingintent.getStringExtra("a2m"));
        float a3=Float.parseFloat(receivingintent.getStringExtra("a3m"));
        float a4=Float.parseFloat(receivingintent.getStringExtra("a4m"));
        float mid=Float.parseFloat(receivingintent.getStringExtra("midm"));
        float fin=Float.parseFloat(receivingintent.getStringExtra("finm"));

        float q=(q1+q2+q3+q4);
        float qf=q*20/40;//% marks obtained in quizzes
        float a=(a1+a2+a3+a4);
        float af=a*20/40;//% marks obtained in assignments
        float m=mid*20/20;//%marks obtained in mids
        float f=fin*100/60;//% marks obtained in finals
        float r=(q+a+mid+fin);//total percentage out of 100
        result.setText(String.valueOf(r)+"%");*/
    }
}