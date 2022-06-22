package com.example.girish.bmicalculator;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ListView lvData;
    ArrayList<Patient> p= new ArrayList<>();
    ArrayAdapter<Patient> ad;
    FirebaseDatabase database;
    DatabaseReference myRef;

    ArrayList<String> k = new ArrayList<>();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        lvData=findViewById(R.id.lvData);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("patient");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                p.clear();
                for (DataSnapshot d: dataSnapshot.getChildren())
                {
                    Patient data = d.getValue(Patient.class);
                    p.add(data);
                    //.add(d.getKey());
                }
                ad = new ArrayAdapter<Patient>(ViewActivity.this,android.R.layout.simple_list_item_1,p);

                lvData.setAdapter(ad);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


    }
}


