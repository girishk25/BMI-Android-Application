package com.example.girish.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etPhone;
    Button btnRegister;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etPhone = findViewById(R.id.etPhone);
        rgGender=findViewById(R.id.rgGender);
        rbMale=findViewById(R.id.rbMale);
        rbFemale=findViewById(R.id.rbFemale);
        btnRegister = findViewById(R.id.btnRegister);
        sp = getSharedPreferences("myp1",MODE_PRIVATE);



    //    String name = etName.getText().toString();
        String n = sp.getString("name","");
        if(n.length()!=0)
        {
            Intent a = new Intent(MainActivity.this,BmiDataActivity.class);
            startActivity(a);
            finish();
        }
        else {

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {


                        String name = etName.getText().toString();

                        if (name.length() < 2) {
                            Toast.makeText(MainActivity.this, "Minimum 2 characters Required", Toast.LENGTH_SHORT).show();
                            etName.requestFocus();
                            return;
                        }

                        String age = etAge.getText().toString();
                        int ag = Integer.parseInt(age);
                        if (ag < 15 || ag > 115) {
                            Toast.makeText(MainActivity.this, "Invalid Age\nAge between 15-115", Toast.LENGTH_SHORT).show();
                            etAge.requestFocus();
                            return;
                        }

                        String phone = etPhone.getText().toString();

                        if (phone.length() < 10 || phone.length() > 10) {

                            Toast.makeText(MainActivity.this, "Invalid Number", Toast.LENGTH_SHORT).show();
                            etPhone.requestFocus();
                            return;
                        }

                        int id = rgGender.getCheckedRadioButtonId();
                        RadioButton rb = findViewById(id);
                        String gender = rb.getText().toString();


                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("name", name);
                        editor.putString("age", age);
                        editor.putString("phone", phone);
                        editor.putString("gender", gender);
                        editor.commit();

                        Intent a = new Intent(MainActivity.this, BmiDataActivity.class);
                     /*   a.putExtra("name", name);
                        a.putExtra("age", age);
                        a.putExtra("phone", phone);
                        a.putExtra("gender", gender);*/


                        startActivity(a);
                        finish();

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}