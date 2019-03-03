package com.dipanshu.mycityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dipanshu.mycityapp.Weather_dir.MainActivity1;

public class TOOLS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);


        Button newsbtn=findViewById(R.id.news);
        newsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),newsclass.class);
                startActivity(i);
            }
        });
        Button forexbtn=findViewById(R.id.forex);
        forexbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),ForexMainActivity.class);
                startActivity(i);
            }
        });


        Button weatherbtn=findViewById(R.id.weather);
        weatherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity1.class);
                startActivity(i);
            }
        });

        Button accidentbtn=findViewById(R.id.shakebtnloc);
        accidentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), ShakeMainActivity.class);
                startActivity(i);
            }
        });

        Button emergency=findViewById(R.id.emergencyno);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), EmergencyActivity.class);
                startActivity(i);
            }
        });

        Button pollution=findViewById(R.id.pollution);
        pollution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), mainpolution.class);
                startActivity(i);
            }
        });

    }
}
