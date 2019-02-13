package com.dipanshu.mycityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class hotels extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotels);

        Button oyobtn=findViewById(R.id.oyo);
        oyobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),OYO.class);
                startActivity(i);
            }
        });
        Button trivagobtn=findViewById(R.id.trivago);
        trivagobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),TRIVAGO.class);
                startActivity(i);
            }
        });
    }
}
