package com.dipanshu.mycityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class citydetails extends AppCompatActivity {

    EditText etview;
    String cityname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citydetails);


        Button btn = findViewById(R.id.enter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etview = findViewById(R.id.etcity);
                cityname=etview.getText().toString();
                Intent intent = new Intent(getBaseContext(),weather.class);
                intent.putExtra("cityname",cityname);
                startActivity(intent);
            }
        });
    }
}
