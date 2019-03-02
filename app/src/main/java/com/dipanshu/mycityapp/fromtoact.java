package com.dipanshu.mycityapp;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dipanshu.mycityapp.Weather_dir.MyCityApp;

public class fromtoact extends AppCompatActivity{


    String from1,to1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fromtodetails);


        Button btn=findViewById(R.id.fromtobtn);
        Button expmore=findViewById(R.id.expmore);
        Button livewall=findViewById(R.id.livewall);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText from = findViewById(R.id.frombtn);
                EditText to = findViewById(R.id.tobtn);
                from1=from.getText().toString();
                to1=to.getText().toString();
                Intent intent = new Intent(getBaseContext(),TRAVEL.class);
                intent.putExtra("City",to1);
                intent.putExtra("Cityf",from1);
                startActivity(intent);

            }
        });
        expmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        livewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(fromtoact.this,ScrollingActivity.class);
                startActivity(i);


            }
        });


    }
}