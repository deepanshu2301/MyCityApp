package com.dipanshu.mycityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TRAVEL extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel);


        Button flighttbtn=findViewById(R.id.flights);
        flighttbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),flights.class);
                startActivity(i);
            }
        });
        Button traintbtn=findViewById(R.id.trains);
        traintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),IRCTC.class);
                startActivity(i);
            }
        });
        Button busbtn=findViewById(R.id.bus);
        busbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),REDBUS.class);
                startActivity(i);
            }
        });
        Button cabbtn=findViewById(R.id.cab);
        cabbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),cabs.class);
                startActivity(i);
            }
        });
        Button carbtn=findViewById(R.id.car);
        carbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),carrental.class);
                startActivity(i);
            }
        });
    }
}
