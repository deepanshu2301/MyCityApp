package com.dipanshu.mycityapp.Weather_dir;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.dipanshu.mycityapp.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyServices extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    String temp1 = "16°", type = "COOL";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("TAG", "onStart: " + "called");
        String Location="delhi";
        String url="http://api.openweathermap.org/data/2.5/weather?q=" + Location + "&appid=830b493e798279185f328c76df4a9e03";
        makenetworkcall(url);

        RemoteViews view = new RemoteViews(getPackageName(), R.layout.appwidget);
        view.setTextViewText(R.id.widgettemp, temp1);
        ComponentName theWidget = new ComponentName(this, appwidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(theWidget, view);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date d = new Date();
        Date c = Calendar.getInstance().getTime();
        String dayOfTheWeek = sdf.format(d);


        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        String date = formattedDate.substring(3, 6) + "," + formattedDate.substring(0, 2) + " " + formattedDate.substring(7);


        RemoteViews view1 = new RemoteViews(getPackageName(), R.layout.appwidget);
        view1.setTextViewText(R.id.widgetdate, date);
        ComponentName theWidget1 = new ComponentName(this, appwidget.class);
//        AppWidgetManager manager1 = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(theWidget1, view1);

        RemoteViews view2 = new RemoteViews(getPackageName(), R.layout.appwidget);
        view2.setTextViewText(R.id.widgetday, dayOfTheWeek);
        ComponentName theWidget2 = new ComponentName(this, appwidget.class);
//        AppWidgetManager manager1 = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(theWidget2, view2);

        RemoteViews view3 = new RemoteViews(getPackageName(), R.layout.appwidget);
        view3.setTextViewText(R.id.widgetinfo, type);
        ComponentName theWidget3 = new ComponentName(this, appwidget.class);
//        AppWidgetManager manager1 = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(theWidget3, view3);


        return super.onStartCommand(intent, flags, startId);

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
                final data data1 = gson.fromJson(jsonResponse, data.class);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int aabb = 0;
                        if (data1 != null && data1.getMain() != null) {
                            float aabb1 = Float.parseFloat(data1.getMain().getTemp());
                            aabb = (int) (aabb1 - 273.15);
                            temp1 = String.valueOf(aabb) + "°";
                        }
                        ArrayList<Detail> det = null;
                        if (data1 != null) {
                            det = data1.getWeather();
                        }
                        if (det != null) {
                            type = String.valueOf(det.get(0).getMain());
                        }


                    }
                    }).start();
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "onCreate: " + "called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy: " + "called");

    }

}
