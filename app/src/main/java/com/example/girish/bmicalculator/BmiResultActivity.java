package com.example.girish.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;


public class BmiResultActivity extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4;
    Button btnBack,btnSave,btnShare;
    SharedPreferences sp;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Date c = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        btnBack=findViewById(R.id.btnBack);
        btnSave=findViewById(R.id.btnSave);
        btnShare=findViewById(R.id.btnShare);
        database = FirebaseDatabase.getInstance();
        myRef= database.getReference("patient");

        sp=getSharedPreferences("myp1",MODE_PRIVATE);
        String n = sp.getString("name","");
        String ag = sp.getString("age","");
        String p = sp.getString("phone","");
        String g = sp.getString("gender","");

         Intent b =getIntent();
        final String bmi = b.getStringExtra("c"); //bmi
        final String msg1 = b.getStringExtra("msg1");


        DecimalFormat precision = new DecimalFormat("0.00");

        double a = Double.parseDouble(bmi);
        final String msg ="Name:"+n + "\nAge: "+ag +"\nPhone Number: "+p + "\nSex: "+g+"\nBmi: "+bmi + "\n"+msg1;
        if(a<18.5){
           tv1.setText("Your Bmi is: "+String.format(bmi,"%.2f")+" and you are UnderWeight");

            tv2.setText("Below 18.5 is UnderWeight");
            tv4.setText("Between 18.5 to 25 is Normal\nBetween 25 to 30 is OverWeight\nMore than 30 is Obese");


        }
        if(a>=18.5 && a<=25) {
            tv1.setText("Your Bmi is: " + String.format(bmi, "%.2f") + " and you are Normal");

            tv3.setText("Below 18.5 is UnderWeight");
            tv2.setText("Between 18.5 to 25 is Normal");

            tv4.setText("Between 25 to 30 is OverWeight\nMore than 30 is Obese");




        }
        if(a>=25 && a<=30) {
            tv1.setText("Your Bmi is: " + String.format(bmi, "%.2f") + " and you are OverWeight");


            tv3.setText("Below 18.5 is UnderWeight\nBetween 18.5 to 25 is Normal");

            tv2.setText("Between 25 to 30 is OverWeight");
            tv4.setText("More than 30 is Obese");


        }
        if(a>=30) {
            tv1.setText("Your Bmi is: " +String.format(bmi,"%.2f" ) + " and you are Obese");


            tv3.setText("Below 18.5 is UnderWeight\nBetween 18.5 to 25 is Normal\nBetween 25 to 30 is OverWeight");

            tv2.setText("More than 30 is Obese");

        }


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a =new Intent(BmiResultActivity.this,BmiDataActivity.class);
                startActivity(a);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent a = new Intent(Intent.ACTION_SEND);
                a.putExtra(Intent.EXTRA_TEXT, msg);

                a.setType("text/plain");
                startActivity(a);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Patient p =new Patient(Double.parseDouble(bmi),c);
                myRef.push().setValue(p);
                Toast.makeText(BmiResultActivity.this, "Record Added", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
