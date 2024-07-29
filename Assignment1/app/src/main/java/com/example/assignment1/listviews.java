package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class listviews extends AppCompatActivity {
//ListView lst;
//Button btn;
//AlertDialog.Builder alert;
//TextView result;
    TextView signuptv;
    Button signinbtn;
    EditText edt1,edt2;
    FirebaseAuth fbauth;
   // FirebaseFirestore fbfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviews);

        signinbtn=findViewById(R.id.loginbtn);
        signuptv=findViewById(R.id.signuptv);
        edt1=findViewById(R.id.ledtemail);
        edt2=findViewById(R.id.ledtpwd);
        fbauth=FirebaseAuth.getInstance();

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt1.getText().toString().trim();
                String pwd = edt2.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    edt1.setError("email field can't be left empty");
                } else if (TextUtils.isEmpty(pwd)) {
                    edt2.setError("password field can't be left empty");
                } else {
                    fbauth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(listviews.this, "signin successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(listviews.this, "signin insuccessful" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        signuptv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),result.class));
            }
        });
//        result=findViewById(R.id.result);
//        btn=(Button) findViewById(R.id.btnintent);
//        Intent loginintent=new Intent(listviews.this, result.class);
//        startActivity(loginintent);
//        lst=findViewById(R.id.lst);
//        String[] arrCities={"ISB","FSB","RWP","LAH","KHI","SARGODHA","CHAKWAL","SIALKOT"};
//        ArrayAdapter<String> adp=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrCities);
//       // ArrayAdapter<String> adp=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.girlsname));
//    lst.setAdapter(adp);
//    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//           //Toast.makeText(listviews.this, lst.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
//        result.setText(lst.getItemAtPosition(i).toString());
//        }
//    });
//    result.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            alert=new AlertDialog.Builder(listviews.this);
//            alert.setMessage("i AM A ALERT");
//            alert.setTitle("Exit");
//            alert.setCancelable(false);
//            alert.setIcon(R.drawable.ic_launcher_background);
//            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    Toast.makeText(listviews.this,"you clicked yes",Toast.LENGTH_SHORT).show();
//
//                }
//            });
//            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    Toast.makeText(listviews.this,"you clicked no",Toast.LENGTH_SHORT).show();
//
//                }
//            });
//            alert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    Toast.makeText(listviews.this,"you clicked cancel",Toast.LENGTH_SHORT).show();
//
//                }
//            });
//            alert.show();
//        }
//    });
    }
}