package com.dipanshu.mycityapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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

public class events extends AppCompatActivity {
    String city="delhi";
    Context ctx=getBaseContext();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        Button enterbtn=findViewById(R.id.cityenter);
        enterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.eventcity);
                city=editText.getText().toString();
                String url="https://www.eventbriteapi.com/v3/events/search/?location.address="+city+"&token=4PMZ25C623YOSHC65ZZ3";
                makenetworkcall(url);
            }
        });


    }
    private void makenetworkcall(String url) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                call.enqueue(this);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonResponse = response.body().string();
                Gson gson = new Gson();
                final eventdata data1 = gson.fromJson(jsonResponse, eventdata.class);
//                Toast.makeText(ctx,""+data1.events.size(),Toast.LENGTH_LONG).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ArrayList<details> array=data1.events;
//                        Toast.makeText(ctx,""+array.size(),Toast.LENGTH_LONG).show();
                        RecyclerView recyclerView = findViewById(R.id.eventrecycle);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        EventAdapter adapter = new EventAdapter(array, (AppCompatActivity) ctx);
                        recyclerView.setAdapter(adapter);


                        }

                });
            }
        });
    }

}
