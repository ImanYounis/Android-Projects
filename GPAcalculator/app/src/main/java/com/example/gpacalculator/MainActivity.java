package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

    Button savebtn,readbtn,logoutbtn;
    FirebaseAuth fbauth;
    ListView lst;
FirebaseFirestore fbfirestore;
    TextView readtv,emailtv;


    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        savebtn=findViewById(R.id.savedatebtn);
        readbtn=findViewById(R.id.readdatabtn);
        logoutbtn=findViewById(R.id.signoutbtn);
        readtv=findViewById(R.id.readtv);
        emailtv=findViewById(R.id.signedintv);
        lst=findViewById(R.id.lst);

        fbauth=FirebaseAuth.getInstance();
        fbfirestore=FirebaseFirestore.getInstance();

        String signedinemail=fbauth.getCurrentUser().getEmail();
        emailtv.setText(signedinemail);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String>datatosave=new HashMap<>();
                datatosave.put("Name","Pakistan");
                datatosave.put("capital","islamabad");
                datatosave.put("provinces","4");

                CollectionReference mycollec=fbfirestore.collection("std");
                DocumentReference stddoc=mycollec.document(fbauth.getCurrentUser().getUid());
                stddoc.set(datatosave).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "data saved", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"data saving error due to following:"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionReference mycollec=fbfirestore.collection("std");
                DocumentReference stddoc=mycollec.document("F18");
                stddoc.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        String[] arr=new String[3];
                        readtv.setText(value.get("capital")+"\n"+value.get("name")+"\n"+value.get("provinces").toString());
                        arr[0]=value.get("name").toString();
                        arr[1]=value.get("capital").toString();
                        arr[2]=value.get("provinces").toString();

                        ArrayAdapter<String> adp=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,arr);
                        lst.setAdapter(adp);
                    }
                });
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fbauth.signOut();
                Toast.makeText(MainActivity.this, "signed out successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),signin.class));
            }
        });
    }
/*
    String cr1,cr2,cr3,cr4,cr5;
   // Character g1,g2,g3,g4,g5;
    String g1,g2,g3,g4,g5;
    float gp1,gp2,gp3,gp4,gp5;
    ImageView img;
Button menubtn;
    TextView tv;
    Button btncalc,btncgpa;
    Spinner spnCr,spnCr2,spnCr3,spnCr4,spnCr5;
    Spinner spnGr,spnGr2,spnGr3,spnGr4,spnGr5;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //MenuInflater inflater=getMenuInflater();
         getMenuInflater().inflate(R.menu.option_menu,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.newmenu:
                Toast.makeText(this, "you clicked new", Toast.LENGTH_SHORT).show();
                break;
            case R.id.open:
                Toast.makeText(this, "you clicked open", Toast.LENGTH_SHORT).show();
                break;

            case R.id.close:
                Toast.makeText(this, "you clicked close", Toast.LENGTH_SHORT).show();
                break;

            case R.id.about:
                Toast.makeText(this, "you clicked about", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.rleft:
                Toast.makeText(this, "you clicked left", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rright:
                Toast.makeText(this, "you clicked rotate right", Toast.LENGTH_SHORT).show();
                break;

            case R.id.pimg:
                Toast.makeText(this, "you clicked print image", Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.img);
       // menubtn=findViewById(R.id.menubtn);
        registerForContextMenu(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(MainActivity.this,img);
                popupMenu.getMenuInflater().inflate(R.menu.option_menu,popupMenu.getMenu());
                popupMenu.show();
            }
        });
///////////////////////////////////////////////////////////
        tv=findViewById(R.id.tvgpa);

        spnCr=findViewById(R.id.spnCr);
        spnCr2=findViewById(R.id.spnCr2);
        spnCr3=findViewById(R.id.spnCr3);
        spnCr4=findViewById(R.id.spnCr4);
        spnCr5=findViewById(R.id.spnCr5);

        spnGr=findViewById(R.id.spnGr);
        spnGr2=findViewById(R.id.spnGr2);
        spnGr3=findViewById(R.id.spnGr3);
        spnGr4=findViewById(R.id.spnGr4);
        spnGr5=findViewById(R.id.spnGr5);

        btncgpa=findViewById(R.id.btncgpa);
        ArrayList<Float> gpalist=new ArrayList<Float>();

        btncalc=findViewById(R.id.btncalc);
        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   tv.setText();
//                g1+","+cr1+"\n"+g2+","+cr2+"\n"+g3+","+cr3+"\n"+g4+","+cr4+"\n"+g5+","+cr5
/*
//subj1
                if(cr1.equals("1")&&g1.equals("A"))
                    gp1=4;
                else if(cr1.equals("1")&&g1.equals("B+"))
                    gp1=3.5f;
                else if(cr1.equals("1")&&g1.equals("B"))
                    gp1=3.0f;
                else if(cr1.equals("1")&&g1.equals("C+"))
                    gp1=2.5f;
                else if(cr1.equals("1")&&g1.equals("C"))
                    gp1=2.0f;
                else if(cr1.equals("1")&&g1.equals("D+"))
                    gp1=1.5f;
                else if(cr1.equals("1")&&g1.equals("D"))
                    gp1=1.0f;
                else if(g1.equals("F"))
                    gp1=0;
                else if(cr1.equals("2")&&g1.equals("A"))
                    gp1=8.0f;
                else if(cr1.equals("2")&&g1.equals("B+"))
                    gp1=7.0f;
                else if(cr1.equals("2")&&g1.equals("B"))
                    gp1=6.0f;
                else if(cr1.equals("2")&&g1.equals("C+"))
                    gp1=5.5f;
                else if(cr1.equals("2")&&g1.equals("C"))
                    gp1=4.0f;
                else if(cr1.equals("2")&&g1.equals("D+"))
                    gp1=3.0f;
                else if(cr1.equals("2")&&g1.equals("D"))
                    gp1=2.0f;

                else if(cr1.equals("3")&&g1.equals("A"))
                    gp1=12.0f;
                else if(cr1.equals("3")&&g1.equals("B+"))
                    gp1=10.5f;
                else if(cr1.equals("3")&&g1.equals("B"))
                    gp1=9.0f;
                else if(cr1.equals("3")&&g1.equals("C+"))
                    gp1=7.5f;
                else if(cr1.equals("3")&&g1.equals("C"))
                    gp1=6.0f;
                else if(cr1.equals("3")&&g1.equals("D+"))
                    gp1=4.5f;
                else if(cr1.equals("3")&&g1.equals("D"))
                    gp1=4.0f;

                else if(cr1.equals("4")&&g1.equals("A"))
                    gp1=16.0f;
                else if(cr1.equals("4")&&g1.equals("B+"))
                    gp1=14.5f;
                else if(cr1.equals("4")&&g1.equals("B"))
                    gp1=12.0f;
                else if(cr1.equals("4")&&g1.equals("C+"))
                    gp1=10.0f;
                else if(cr1.equals("4")&&g1.equals("C"))
                    gp1=8.0f;
                else if(cr1.equals("4")&&g1.equals("D+"))
                    gp1=6.0f;
                else if(cr1.equals("4")&&g1.equals("D"))
                    gp1=4.0f;





//subj2
                if(cr2.equals("1")&&g2.equals("A"))
                    gp2=4.0f;
                else if(cr2.equals("1")&&g2.equals("B+"))
                    gp2=3.5f;
                else if(cr2.equals("1")&&g2.equals("B"))
                    gp2=3.0f;
                else if(cr2.equals("1")&&g2.equals("C+"))
                    gp2=2.5f;
                else if(cr2.equals("1")&&g2.equals("C"))
                    gp2=2.0f;
                else if(cr2.equals("1")&&g2.equals("D+"))
                    gp2=1.5f;
                else if(cr2.equals("1")&&g2.equals("D"))
                    gp2=1.0f;
                else if(g2.equals("F"))
                    gp2=0;
                else if(cr2.equals("2")&&g2.equals("A"))
                    gp2=8.0f;
                else if(cr2.equals("2")&&g2.equals("B+"))
                    gp2=7.0f;
                else if(cr2.equals("2")&&g2.equals("B"))
                    gp2=6.0f;
                else if(cr2.equals("2")&&g2.equals("C+"))
                    gp2=5.0f;
                else if(cr2.equals("2")&&g2.equals("C"))
                    gp2=4.0f;
                else if(cr2.equals("2")&&g2.equals("D+"))
                    gp2=3.0f;
                else if(cr2.equals("2")&&g2.equals("D"))
                    gp2=2.0f;

                else if(cr2.equals("3")&&g2.equals("A"))
                    gp2=12.0f;
                else if(cr2.equals("3")&&g2.equals("B+"))
                    gp2=10.5f;
                else if(cr2.equals("3")&&g2.equals("B"))
                    gp2=9.0f;
                else if(cr2.equals("3")&&g2.equals("C+"))
                    gp2=7.5f;
                else if(cr2.equals("3")&&g2.equals("C"))
                    gp2=6.0f;
                else if(cr2.equals("3")&&g2.equals("D+"))
                    gp2=4.5f;
                else if(cr2.equals("3")&&g2.equals("D"))
                    gp2=4.0f;

                else if(cr2.equals("4")&&g2.equals("A"))
                    gp2=16.0f;
                else if(cr2.equals("4")&&g2.equals("B+"))
                    gp2=14.5f;
                else if(cr2.equals("4")&&g2.equals("B"))
                    gp2=12.0f;
                else if(cr2.equals("4")&&g2.equals("C+"))
                    gp2=10.0f;
                else if(cr2.equals("4")&&g2.equals("C"))
                    gp2=8.0f;
                else if(cr2.equals("4")&&g2.equals("D+"))
                    gp2=6.0f;
                else if(cr2.equals("4")&&g2.equals("D"))
                    gp2=4.0f;





//subj3
                if(cr3.equals("1")&&g3.equals("A"))
                    gp3=4.0f;
                else if(cr3.equals("1")&&g3.equals("B+"))
                    gp3=3.5f;
                else if(cr3.equals("1")&&g3.equals("B"))
                    gp3=3.0f;
                else if(cr3.equals("1")&&g3.equals("C+"))
                    gp3=2.5f;
                else if(cr3.equals("1")&&g3.equals("C"))
                    gp3=2.0f;
                else if(cr3.equals("1")&&g3.equals("D+"))
                    gp3=1.5f;
                else if(cr3.equals("1")&&g3.equals("D"))
                    gp3=1.0f;
                else if(g3.equals("F"))
                    gp3=0;
                else if(cr3.equals("2")&&g3.equals("A"))
                    gp3=8.0f;
                else if(cr3.equals("2")&&g3.equals("B+"))
                    gp3=7.0f;
                else if(cr3.equals("2")&&g3.equals("B"))
                    gp3=6.0f;
                else if(cr3.equals("2")&&g3.equals("C+"))
                    gp3=5.0f;
                else if(cr3.equals("2")&&g3.equals("C"))
                    gp3=4.0f;
                else if(cr3.equals("2")&&g3.equals("D+"))
                    gp3=3.0f;
                else if(cr3.equals("2")&&g3.equals("D"))
                    gp3=2.0f;

                else if(cr3.equals("3")&&g3.equals("A"))
                    gp3=12.0f;
                else if(cr3.equals("3")&&g3.equals("B+"))
                    gp3=10.5f;
                else if(cr3.equals("3")&&g3.equals("B"))
                    gp3=9.0f;
                else if(cr3.equals("3")&&g3.equals("C+"))
                    gp3=7.5f;
                else if(cr3.equals("3")&&g3.equals("C"))
                    gp3=6.0f;
                else if(cr3.equals("3")&&g3.equals("D+"))
                    gp3=4.5f;
                else if(cr3.equals("3")&&g3.equals("D"))
                    gp3=4.0f;

                else if(cr3.equals("4")&&g3.equals("A"))
                    gp3=16.0f;
                else if(cr3.equals("4")&&g3.equals("B+"))
                    gp3=14.5f;
                else if(cr3.equals("4")&&g3.equals("B"))
                    gp3=12.0f;
                else if(cr3.equals("4")&&g3.equals("C+"))
                    gp3=10.0f;
                else if(cr3.equals("4")&&g3.equals("C"))
                    gp3=8.0f;
                else if(cr3.equals("4")&&g3.equals("D+"))
                    gp3=6.0f;
                else if(cr3.equals("4")&&g3.equals("D"))
                    gp3=4.0f;

//subj4
                if(cr4.equals("1")&&g4.equals("A"))
                    gp4=4.0f;
                else if(cr4.equals("1")&&g4.equals("B+"))
                    gp4=3.5f;
                else if(cr4.equals("1")&&g4.equals("B"))
                    gp4=3.0f;
                else if(cr4.equals("1")&&g4.equals("C+"))
                    gp4=2.5f;
                else if(cr4.equals("1")&&g4.equals("C"))
                    gp4=2.0f;
                else if(cr4.equals("1")&&g4.equals("D+"))
                    gp4=1.5f;
                else if(cr4.equals("1")&&g4.equals("D"))
                    gp4=1.0f;
                else if(g4.equals("F"))
                    gp4=0;
                else if(cr4.equals("2")&&g4.equals("A"))
                    gp4=8.0f;
                else if(cr4.equals("2")&&g4.equals("B+"))
                    gp4=7.0f;
                else if(cr4.equals("2")&&g4.equals("B"))
                    gp4=6.0f;
                else if(cr4.equals("2")&&g4.equals("C+"))
                    gp4=5.0f;
                else if(cr4.equals("2")&&g4.equals("C"))
                    gp4=4.0f;
                else if(cr4.equals("2")&&g4.equals("D+"))
                    gp4=3.0f;
                else if(cr4.equals("2")&&g4.equals("D"))
                    gp4=2.0f;

                else if(cr4.equals("3")&&g4.equals("A"))
                    gp4=12.0f;
                else if(cr4.equals("3")&&g4.equals("B+"))
                    gp4=10.5f;
                else if(cr4.equals("3")&&g4.equals("B"))
                    gp4=9.0f;
                else if(cr4.equals("3")&&g4.equals("C+"))
                    gp4=7.5f;
                else if(cr4.equals("3")&&g4.equals("C"))
                    gp4=6.0f;
                else if(cr4.equals("3")&&g4.equals("D+"))
                    gp4=4.5f;
                else if(cr4.equals("3")&&g4.equals("D"))
                    gp4=4.0f;

                else if(cr4.equals("4")&&g4.equals("A"))
                    gp4=16.0f;
                else if(cr4.equals("4")&&g4.equals("B+"))
                    gp4=14.5f;
                else if(cr4.equals("4")&&g4.equals("B"))
                    gp4=12.0f;
                else if(cr4.equals("4")&&g4.equals("C+"))
                    gp4=10.0f;
                else if(cr4.equals("4")&&g4.equals("C"))
                    gp4=8.0f;
                else if(cr4.equals("4")&&g4.equals("D+"))
                    gp4=6.0f;
                else if(cr4.equals("4")&&g4.equals("D"))
                    gp4=4.0f;






//subj5
                if(cr5.equals("1")&&g5.equals("A"))
                    gp5=4.0f;
                else if(cr5.equals("1")&&g5.equals("B+"))
                    gp5=3.5f;
                else if(cr5.equals("1")&&g5.equals("B"))
                    gp5=3.0f;
                else if(cr5.equals("1")&&g5.equals("C+"))
                    gp5=2.5f;
                else if(cr5.equals("1")&&g5.equals("C"))
                    gp5=2.0f;
                else if(cr5.equals("1")&&g5.equals("D+"))
                    gp5=1.5f;
                else if(cr5.equals("1")&&g5.equals("D"))
                    gp5=1.0f;
                else if(g5.equals("F"))
                    gp5=0;
                else if(cr5.equals("2")&&g5.equals("A"))
                    gp5=8.0f;
                else if(cr5.equals("2")&&g5.equals("B+"))
                    gp5=7.0f;
                else if(cr5.equals("2")&&g5.equals("B"))
                    gp5=6.0f;
                else if(cr5.equals("2")&&g5.equals("C+"))
                    gp5=5.0f;
                else if(cr5.equals("2")&&g5.equals("C"))
                    gp5=4.0f;
                else if(cr5.equals("2")&&g5.equals("D+"))
                    gp5=3.0f;
                else if(cr5.equals("2")&&g5.equals("D"))
                    gp5=2.0f;

                else if(cr5.equals("3")&&g5.equals("A"))
                    gp5=12.0f;
                else if(cr5.equals("3")&&g5.equals("B+"))
                    gp5=10.5f;
                else if(cr5.equals("3")&&g5.equals("B"))
                    gp5=9.0f;
                else if(cr5.equals("3")&&g5.equals("C+"))
                    gp5=7.5f;
                else if(cr5.equals("3")&&g5.equals("C"))
                    gp5=6.0f;
                else if(cr5.equals("3")&&g5.equals("D+"))
                    gp5=4.5f;
                else if(cr5.equals("3")&&g5.equals("D"))
                    gp5=4.0f;

                else if(cr5.equals("4")&&g5.equals("A"))
                    gp5=16.0f;
                else if(cr5.equals("4")&&g5.equals("B+"))
                    gp5=14.5f;
                else if(cr5.equals("4")&&g5.equals("B"))
                    gp5=12.0f;
                else if(cr5.equals("4")&&g5.equals("C+"))
                    gp5=10.0f;
                else if(cr5.equals("4")&&g5.equals("C"))
                    gp5=8.0f;
                else if(cr5.equals("4")&&g5.equals("D+"))
                    gp5=6.0f;
                else if(cr5.equals("4")&&g5.equals("D"))
                    gp5=4.0f;*/
//or by using method to calculate gradepoints of each subject

 /*              gp1=gpa(cr1,g1);
                gp2=gpa(cr2,g2);
                gp3=gpa(cr3,g3);
                gp4=gpa(cr4,g4);
                gp5=gpa(cr5,g5);


                float result=(gp1+gp2+gp3+gp4+gp5)/
                        (Integer.parseInt(cr1)+Integer.parseInt(cr2)+Integer.parseInt(cr3)+Integer.parseInt(cr4)+Integer.parseInt(cr5));
                /*if(result<=4.0&&result>=3)
                tv.setTextColor(Color.GREEN);
                else if(result<3&&result>=2.5)
                    tv.setTextColor(Color.YELLOW);
                else if(result<2.5)
                    tv.setTextColor(Color.RED);
                 */
//                tv.setText(String.valueOf(result));
//                gpalist.add(result);
//
//
//            }
//        });
//        btncgpa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                float cgpa=0.0f;
//                for (Float sgpa:gpalist) {
//                    cgpa=cgpa+sgpa;
//                }
//                if(gpalist.size()<=8)
//                    tv.setText(String.valueOf(cgpa/gpalist.size()));
////for rounding off to 2 decimal places: in tv.setText: no need for string.valueof then   String.format("CGPA: %.2f", cgpa
//            }
//        });
//
//        spnGr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                g1= String.valueOf(adapterView.getItemAtPosition(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spnCr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                cr1= String.valueOf(adapterView.getItemAtPosition(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        spnGr2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                g2= String.valueOf(adapterView.getItemAtPosition(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spnCr2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                cr2= String.valueOf(adapterView.getItemAtPosition(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        spnGr3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                g3= String.valueOf(adapterView.getItemAtPosition(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spnCr3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                cr3= String.valueOf(adapterView.getItemAtPosition(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        spnGr4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                g4= String.valueOf(adapterView.getItemAtPosition(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spnCr4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                cr4= String.valueOf(adapterView.getItemAtPosition(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        spnGr5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                g5= String.valueOf(adapterView.getItemAtPosition(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spnCr5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                cr5= String.valueOf(adapterView.getItemAtPosition(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//
//    }
//    public float gpa(String cr,String g){
//        float gp,points=0;
//        switch (g){
//            case "A":
//                points=4.0f;
//                break;
//            case "B+":
//                points=3.5f;
//                break;
//            case "B":
//                points=3.0f;
//                break;
//            case "C+":
//                points=2.5f;
//                break;
//            case "C":
//                points=2.0f;
//                break;
//            case "D+":
//                points=1.5f;
//                break;
//            case "D":
//                points=1.0f;
//                break;
//            default:
//                points=0.0f;
//                break;
//        }
//        gp=Integer.parseInt(cr)*points;
//        return gp;
//    }
}
