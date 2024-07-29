package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class constraintrel extends AppCompatActivity {

    Button btn;
    TextView tv;
    CoordinatorLayout corlin;
    ProgressBar pv;
    Spinner spn;
 //   RadioButton rb;
  //  CheckBox chk;

  // ListView lst;
   RatingBar ratingBar;
   SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraintrel);
        corlin=findViewById(R.id.corlin);
        pv=findViewById(R.id.pv);
        pv.setVisibility(View.INVISIBLE);
        tv=findViewById(R.id.tv);
        sb=findViewById(R.id.seekBar);
        ratingBar=findViewById(R.id.ratingBar);
        spn=findViewById(R.id.spn);

        btn = findViewById(R.id.btn1);
        registerForContextMenu(btn);

        SharedPreferences sp=getSharedPreferences("file",MODE_PRIVATE);
        SharedPreferences.Editor spedit= sp.edit();

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str=adapterView.getItemAtPosition(i).toString();
                spedit.putString("favcolor",str);
                spedit.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tv.setText("nothing selected");
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pv.setVisibility(View.VISIBLE);
                Snackbar snackbar=Snackbar.make(corlin,"this is a snackbar", BaseTransientBottomBar.LENGTH_INDEFINITE);

                        snackbar.setAction("Settings", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(constraintrel.this, "you used snackbar", Toast.LENGTH_SHORT).show();
                            }
                        }).setActionTextColor(Color.YELLOW).setBackgroundTint(Color.BLUE).setTextColor(Color.WHITE)
                        .show();
                PopupMenu popupMenu=new PopupMenu(constraintrel.this,btn);
                popupMenu.getMenuInflater().inflate(R.menu.options,popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.open:
                                Toast.makeText(constraintrel.this, "doc opened in popup", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.close:
                                Toast.makeText(constraintrel.this, "doc closed in popup", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.save:
                                Toast.makeText(constraintrel.this, "doc saved in popup", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
                pv.setVisibility(View.GONE);
//                if(rb.isChecked()) {
//                    tv.setText(rb.getText().toString());
//                    SharedPreferences sp=getSharedPreferences("myfile",MODE_PRIVATE);
//                    tv.setText(sp.getString("key",""));
//                }
//                else if (chk.isChecked()) {
//                    SharedPreferences sp=getSharedPreferences("myfile",MODE_PRIVATE);
//                    SharedPreferences.Editor spedit=sp.edit();
//                    spedit.putString("key",chk.getText().toString());
//                    spedit.commit();
//                }
           }
        });
        btn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btn.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btn.setText(String.valueOf(i));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                btn.setBackgroundColor(Color.YELLOW);
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                tv.setText(String.valueOf(v));
            }
        });
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv.setText(String.valueOf(i));
                tv.setTextColor(Color.rgb(i+3,i*5,i+20));
                tv.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv.setText("tracking");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv.setText("stopped tracking");
            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.options, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.open:
                Toast.makeText(this, "doc opened", Toast.LENGTH_SHORT).show();
                break;
            case R.id.close:
                Toast.makeText(this, "doc closed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:
                Toast.makeText(this, "doc saved", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.open:
                Toast.makeText(this, "doc opened", Toast.LENGTH_SHORT).show();
                break;
            case R.id.close:
                Toast.makeText(this, "doc closed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:
                Toast.makeText(this, "doc saved", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onPause(){
        super.onPause();
        Toast.makeText(constraintrel.this,"in onpause",Toast.LENGTH_SHORT).show();
        SharedPreferences sp=getSharedPreferences("file",MODE_PRIVATE);
        SharedPreferences.Editor spedit=sp.edit();
        Spinner spn;
        ImageView img=findViewById(R.id.img);
        spn=findViewById(R.id.spn);
        String str=spn.getSelectedItem().toString();
        String image=img.getDrawable().toString();
        spedit.putString("image",image);
        spedit.putString("favcolor",str);
        spedit.commit();
    }
    public void onResume(){
        super.onResume();
        Toast.makeText(constraintrel.this,"in onresume",Toast.LENGTH_SHORT).show();
        SharedPreferences sp=getSharedPreferences("file",MODE_PRIVATE);
        String str=sp.getString("favcolor","");
        tv.setText(str);
        Button btn=findViewById(R.id.btn1);
        ImageView img=findViewById(R.id.img);
        if(str.equalsIgnoreCase("red"))
        btn.setTextColor(Color.RED);
        else if (str.equalsIgnoreCase("blue")) {
            btn.setTextColor(Color.BLUE);
            img.setImageResource(R.drawable.alhukmaa);
        } else if (str.equalsIgnoreCase("yellow")) {
            btn.setTextColor(Color.YELLOW);
            img.setImageResource(R.drawable.iiu);
        }
        else {
            btn.setBackgroundColor(Color.GREEN);
            img.setImageResource(R.drawable.pisj);
        }

    }
}