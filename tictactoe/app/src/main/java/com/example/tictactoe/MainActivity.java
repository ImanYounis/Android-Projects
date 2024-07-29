package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.tictactoe.data.DBHelper;
import com.example.tictactoe.data.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv;
    ListView lst;
    int turn=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper db=new DBHelper(this);
//        Contact c=new Contact();
//        c.setName("Iman");
//        c.setPhoneno("03123456789");
//        c.setId(001);
//        db.addContact(c);
//        Contact c1=new Contact();
//        c1.setName("Muskan");
//        c1.setPhoneno("03123456780");
//        c1.setId(002);
//        db.addContact(c1);
//        Contact c2=new Contact();
//        c2.setName("Youni");
//        c2.setPhoneno("03111111110");
//        c2.setId(003);
//        db.addContact(c2);
//        Log.d("dblogs","contacts added to db");
//        c.setId(11);
//        c.setName("ImanYounis");
//        c.setPhoneno("03180000000");
//        int affectedRows=db.updateContact(c);
//        Log.d("dblogs","Number of affected rows:"+affectedRows);
//
//        db.deleteContactById(1);
//        db.deleteContactByNameorwhole(c1);

        lst=findViewById(R.id.lst);
        ArrayList<String>mycontacts=new ArrayList<>();
        List<Contact> contacts=db.displayContacts();
        for (Contact contact:contacts) {
            Log.d("dblogs","\nID:"+contact.getId()+"\nName:"+contact.getName()+"\nPhone:"+contact.getPhoneno());
            mycontacts.add(contact.getName()+" ~"+contact.getPhoneno());
        }
        ArrayAdapter adp=new ArrayAdapter(this, android.R.layout.simple_list_item_1,mycontacts);
        lst.setAdapter(adp);
        Log.d("dblogs","You have"+db.getCount()+" contacts in your db");

        tv=findViewById(R.id.tv);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        tv6=findViewById(R.id.tv6);
        tv7=findViewById(R.id.tv7);
        tv8=findViewById(R.id.tv8);
        tv9=findViewById(R.id.tv9);


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn==0){
                    tv1.setText("X");
                } else if (turn==1) {
                    tv1.setText("O");
                }
                gamestate[0]=turn;
                changeTurn();
                checkwinner();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn==0){
                    tv2.setText("X");
                }
                else if (turn==1) {
                    tv2.setText("O");
                }
                gamestate[1]=turn;
                changeTurn();
                checkwinner();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn==0){
                    tv3.setText("X");
                }
                else if (turn==1) {
                    tv3.setText("O");
                }
                gamestate[2]=turn;
                changeTurn();
                checkwinner();
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn==0){
                    tv4.setText("X");
                }
                else if (turn==1) {
                    tv4.setText("O");
                }
                gamestate[3]=turn;
                changeTurn();
                checkwinner();
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn==0){
                    tv5.setText("X");
                }
                else if (turn==1) {
                    tv5.setText("O");
                }
                gamestate[4]=turn;
                changeTurn();
                checkwinner();
            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn==0){
                    tv6.setText("X");
                }
                else if (turn==1) {
                    tv6.setText("O");
                }
                gamestate[5]=turn;
                changeTurn();
                checkwinner();
            }
        });
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn==0){
                    tv7.setText("X");
                }
                else if (turn==1) {
                    tv7.setText("O");
                }
                gamestate[6]=turn;
                changeTurn();
                checkwinner();
            }
        });
        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn==0){
                    tv8.setText("X");
                }
                else if (turn==1) {
                    tv8.setText("O");
                }
                gamestate[7]=turn;
                changeTurn();
                checkwinner();
            }
        });
        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn==0){
                    tv9.setText("X");
                }
                else if (turn==1) {
                    tv9.setText("O");
                }
                gamestate[8]=turn;
                changeTurn();
                checkwinner();
            }
        });
    }
    public void changeTurn(){
        if(turn==0)
            turn=1;
        else turn=0;
    }
    public void checkwinner(){
        int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for (int[] winPosition:winPositions) {
            if(gamestate[winPosition[0]]==gamestate[winPosition[1]]&&gamestate[winPosition[1]]==gamestate[winPosition[2]]&&gamestate[winPosition[0]]!=2){
                if(gamestate[winPosition[0]]==0)
                    tv.setText("X has won");
                else{
                    tv.setText("O has won");
                }
            }

        }
    }
}