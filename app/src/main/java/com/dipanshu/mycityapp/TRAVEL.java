package com.dipanshu.mycityapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dipanshu.mycityapp.Weather_dir.MainActivity1;
import com.dipanshu.mycityapp.Weather_dir.data;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TRAVEL extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel);

        Intent intent = getIntent();
        final String city = intent.getStringExtra("City");
        final String cityf = intent.getStringExtra("Cityf");

        Button flighttbtn = findViewById(R.id.flights);
        flighttbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), flights.class);
                i.putExtra("City", city);
                startActivity(i);
            }
        });
        Button traintbtn = findViewById(R.id.trains);
        traintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), IRCTC.class);
                i.putExtra("Cityf", cityf);
                i.putExtra("City", city);
                startActivity(i);
            }
        });
        Button busbtn = findViewById(R.id.bus);
        busbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), REDBUS.class);
                startActivity(i);
            }
        });
        Button cabbtn = findViewById(R.id.cab);
        cabbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), cabs.class);
                startActivity(i);
            }
        });
        Button carbtn = findViewById(R.id.car);
        carbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), carrental.class);
                startActivity(i);
            }
        });
    }

//    private void makenetworkcall(String url) {
//
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(url).build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//                call.enqueue(this);
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                String jsonResponse = response.body().string();
//                Gson gson = new Gson();
//                final data data1 = gson.fromJson(jsonResponse, data.class);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        TextView city = findViewById(R.id.name);
//                        if (data1.getName() == null) {
////                            Toast.makeText(getActivity(), "Data not available", Toast.LENGTH_SHORT).show();
//                            String par = "http://api.openweathermap.org/data/2.5/weather?q=delhi&appid=830b493e798279185f328c76df4a9e03";
//                            makenetworkcall(par);
////                            Toast.makeText(getActivity(), "Location not Detected ! Please enter Manually", Toast.LENGTH_LONG).show();
//                        } else {
//                            if (data1 != null) {
//                                city.setText(data1.getName());
//                            }
//                            String temp1 = null;
//                            if (data1 != null && data1.getMain() != null) {
//                                float aabb1 = Float.parseFloat(data1.getMain().getTemp());
//
//                                int aabb = (int) (aabb1 - 273.15);
//                            }
//                        }
//                    }
//
//                });
//            }
//
//        });
//    }
}