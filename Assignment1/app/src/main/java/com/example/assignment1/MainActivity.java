package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView emailtv,readtv;
    Button logoutbtn,readbtn,savebtn;
    FirebaseAuth fbauth;
    EditText edt5,edt6;
    FirebaseFirestore fbfirestore;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailtv=findViewById(R.id.emailtv);
        readtv=findViewById(R.id.readtv);
        logoutbtn=findViewById(R.id.logoutbtn);
        readbtn=findViewById(R.id.readbtn);
        savebtn=findViewById(R.id.savebtn);
        lst=findViewById(R.id.lst);
        edt5=findViewById(R.id.enteremail);
        edt6=findViewById(R.id.enterpassword);

        fbauth=FirebaseAuth.getInstance();
        fbfirestore=FirebaseFirestore.getInstance();

        emailtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailtv.setText(fbauth.getCurrentUser().getEmail());
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> stdinfo=new HashMap<>();
                stdinfo.put("email",edt5.getText().toString());
                stdinfo.put("password",edt6.getText().toString());

                CollectionReference mycollec=fbfirestore.collection("authData");
                DocumentReference mydoc=fbfirestore.document("users");
                mydoc.set(stdinfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "data saved", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"data saving error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionReference mycollec=fbfirestore.collection("authData");
                DocumentReference mydoc=fbfirestore.document("users");
                mydoc.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        String[] arr=new String[2];
                        arr[0]=value.get("email").toString();
                        arr[1]=value.get("password").toString();
                        ArrayAdapter<String> adp=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,arr);
                        lst.setAdapter(adp);
                        readtv.setText(value.get("email").toString()+value.get("password").toString());
                        edt5.setText(String.valueOf(value.get("email")));
                        edt6.setText(String.valueOf(value.get("password")));
                        Intent loginintent=new Intent(MainActivity.this,result.class);
                        loginintent.putExtra("name",edt5.getText().toString());
                        loginintent.putExtra("pwd",edt6.getText().toString());
                        startActivity(loginintent);

                    }
                });
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fbauth.signOut();
                Toast.makeText(MainActivity.this,"signedout successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),listviews.class));
            }
        });
    }
}