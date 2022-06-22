package com.example.girish.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BmiDataActivity extends AppCompatActivity {

    Spinner spnFeet,spnInches;
    EditText etWeight;
    TextView textView;
    Button btnCalculate,btnHistory;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_data);

        spnFeet= findViewById(R.id.spnFeet);
        spnInches=findViewById(R.id.spnInches);
        etWeight=findViewById(R.id.etWeight);
        textView=findViewById(R.id.textView);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnHistory=findViewById(R.id.btnHistory);



        sp=getSharedPreferences("myp1",MODE_PRIVATE);
        String n = sp.getString("name","");
        String a = sp.getString("age","");
        String p = sp.getString("phone","");
        String g = sp.getString("gender","");





        textView.setText("Welcome "+n);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("n", n);
        editor.putString("a", a);
        editor.putString("p", p);
        editor.putString("g", g);
        editor.commit();
        final ArrayList<String> feet = new ArrayList<>();
        feet.add("0");
        feet.add("1");
        feet.add("2");
        feet.add("3");
        feet.add("4");
        feet.add("5");
        feet.add("6");
        feet.add("7");
        feet.add("8");
        feet.add("9");
        feet.add("10");


                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, feet);

        spnFeet.setAdapter(arrayAdapter);

        final ArrayList<String> inches = new ArrayList<>();
        inches.add("0");
        inches.add("1");
        inches.add("2");
        inches.add("3");
        inches.add("4");
        inches.add("5");
        inches.add("6");
        inches.add("7");
        inches.add("8");
        inches.add("9");
        inches.add("10");
        inches.add("11");
        inches.add("12");


        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,inches);

        spnInches.setAdapter(arrayAdapter1);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double bmi;
                    int f = spnFeet.getSelectedItemPosition();
                    if(f==0) {
                        Toast.makeText(BmiDataActivity.this, "Enter Height", Toast.LENGTH_SHORT).show();
                    }

                    else {

                        int i = spnInches.getSelectedItemPosition();

                        String weight = etWeight.getText().toString();
                        DecimalFormat precision = new DecimalFormat("0.00");
                        double w = Double.parseDouble(weight);
                        double fm = f * 0.3048; //feet in m
                        double im = i * 0.0254;//inches in m
                        double hm = fm + im;// height in m
                        bmi = w / (hm * hm);
                        double p=Double.parseDouble(precision.format(bmi));
                        String c = String.valueOf(p);

                    String msg1="";
                    if(bmi<18.5){
                        msg1="You are UnderWeight";
                    }
                    if(bmi>=18.5 && bmi<=25) {
                        msg1 = "You are Normal";
                    }
                    if(bmi>=25 && bmi<=30) {
                        msg1="You are OverWeight";
                    }
                    if(bmi>=30) {
                        msg1="You are Obese";
                    }

                    Intent b =new Intent(BmiDataActivity.this,BmiResultActivity.class);
                    b.putExtra("c",c);
                    b.putExtra("msg1",msg1);



                    startActivity(b);}////
                }
                catch (Exception e)
                {
                    Toast.makeText(BmiDataActivity.this, "Enter Weight", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent a= new Intent(BmiDataActivity.this,ViewActivity.class);
                startActivity(a);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m1,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(R.id.aboutus == item.getItemId()) {

            Toast.makeText(this, "App Created By Girish", Toast.LENGTH_SHORT).show();
        }
        if(R.id.website == item.getItemId()) {
            Intent a = new Intent(Intent.ACTION_VIEW);
            a.setData(Uri.parse("http://"+"www.google.com"));
            startActivity(a);
        }

        return super.onOptionsItemSelected(item);
    }

}
