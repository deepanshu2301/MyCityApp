package com.dipanshu.mycityapp.Weather_dir;//package com.dipanshu.weather;
//
//import android.Manifest;
//import android.app.Notification;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.location.LocationManager;
//import android.provider.Settings;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.NotificationCompat;
//import android.support.v7.app.AlertDialog;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ScrollView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.gson.Gson;
//import com.squareup.picasso.Picasso;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import androidx.work.ListenableWorker;
//import androidx.work.Worker;
//import androidx.work.WorkerParameters;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import static android.content.Context.LOCATION_SERVICE;
//
//public class Myworker extends Worker {
//
//
//    public Myworker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
//        super(context, workerParams);
//    }
//
//    public boolean isOnline() {
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
//            int exitValue = ipProcess.waitFor();
//            return (exitValue == 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
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
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        TextView city = getActivity().findViewById(R.id.name);
//                        if (data1.getName() == null) {
////                            Toast.makeText(getActivity(), "Data not available", Toast.LENGTH_SHORT).show();
//                            String par = "http://api.openweathermap.org/data/2.5/weather?q=delhi&appid=830b493e798279185f328c76df4a9e03";
//                            String par1 = "http://api.openweathermap.org/data/2.5/forecast?q=delhi&cnt=6&appid=830b493e798279185f328c76df4a9e03";
//                            makenetworkcall(par);
//                            makehorinetworkcall(par1);
//                            Toast.makeText(getActivity(), "Location not Detected ! Please enter Manually", Toast.LENGTH_LONG).show();
//                        } else {
//                            horicheck = true;
//                            if (data1 != null) {
//                                city.setText(data1.getName());
//                                mylocation = data1.getName();
//                            }
//                            TextView temp = getActivity().findViewById(R.id.temp);
//                            TextView tma = getActivity().findViewById(R.id.tempmax);
//                            TextView tmi = getActivity().findViewById(R.id.tempmin);
//                            String temp1 = null;
//                            int tmax = 0, tmin = 0;
//                            int aabb = 0;
//                            int currtemp = 0;
//                            if (data1 != null && data1.getMain() != null) {
//                                float aabb1 = Float.parseFloat(data1.getMain().getTemp());
//                                aabb = (int) (aabb1 - 273.15);
//
//                                temp1 = String.valueOf(aabb);
//                                tmax = Integer.parseInt(temp1) + 6;
//                                tmin = Integer.parseInt(temp1) - 4;
//                                currtemp = aabb;
//                                mytemp = currtemp;
//
//                            }
//                            TextView con = getActivity().findViewById(R.id.con);
//                            con.setText("" + data1.getSys().getCountry());
//
//                            if (String.valueOf(aabb).length() == 1) {
//                                temp.setText("0" + aabb);
//                            } else
//                                temp.setText("" + aabb);
//
//                            tma.setText("" + tmax);
//                            tmi.setText("" + tmin);
//
//                            ScrollView ss = getActivity().findViewById(R.id.sview);
//                            if (aabb >= 30) {
//                                ss.setBackgroundResource(R.drawable.above_thirty);
//                            } else if (aabb < 30 && aabb >= 15) {
//                                ss.setBackgroundResource(R.drawable.fifteento30);
//                            } else if (aabb < 15 && aabb >= 0) {
//                                ss.setBackgroundResource(R.drawable.five);
//                            } else {
//                                ss.setBackgroundResource(R.drawable.below5);
//                            }
//                            TextView lati = getActivity().findViewById(R.id.lat);
//                            if (data1 != null && data1.getCoord() != null) {
//                                lati.setText(data1.getCoord().getLat());
//                            }
//
//                            TextView name = getActivity().findViewById(R.id.name), mapclick = getActivity().findViewById(R.id.mapclick);
//                            name.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            TextView cc = getActivity().findViewById(R.id.cc1), mm9 = getActivity().findViewById(R.id.mm9),
//                                    ww1 = getActivity().findViewById(R.id.ww1), pp1 = getActivity().findViewById(R.id.pp1);
//                            cc.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            LinearLayout li = getActivity().findViewById(R.id.cc2);
//                            LinearLayout li1 = getActivity().findViewById(R.id.cc3);
//                            LinearLayout ll3 = getActivity().findViewById(R.id.ll3);
//
//                            li.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            li1.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            LinearLayout mm1 = getActivity().findViewById(R.id.mm1),
//                                    mm2 = getActivity().findViewById(R.id.mm2),
//                                    mm3 = getActivity().findViewById(R.id.mm3),
//                                    mm4 = getActivity().findViewById(R.id.mm4),
//                                    mm5 = getActivity().findViewById(R.id.mm5),
//                                    mm6 = getActivity().findViewById(R.id.mm6),
//                                    mm7 = getActivity().findViewById(R.id.mm7),
//                                    mm8 = getActivity().findViewById(R.id.mm8),
//                                    ww2 = getActivity().findViewById(R.id.ww2),
//                                    ww3 = getActivity().findViewById(R.id.ww3),
//                                    pp2 = getActivity().findViewById(R.id.pp2),
//                                    health = getActivity().findViewById(R.id.health),
//                                    cone = getActivity().findViewById(R.id.coni),
//                                    pp3 = getActivity().findViewById(R.id.pp3);
//
//                            health.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            ll3.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            mm1.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            mm2.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            mm3.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            mm4.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            mm5.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            mm6.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            mm7.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            mm8.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            mm9.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            ww2.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            ww3.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            pp2.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            pp3.setBackgroundColor(Color.argb(99, 00, 00, 00));
//                            cone.setBackgroundColor(Color.argb(99, 00, 00, 00));
////                            mapclick.setBackgroundColor(Color.argb(99, 0, 0, 0));
//                            pp1.setBackgroundColor(Color.argb(99, 00, 00, 00));
//
//
//                            ww1.setBackgroundColor(Color.argb(99, 00, 00, 00));
//
//
//                            TextView sunr = getActivity().findViewById(R.id.sunrise);
//                            if (data1 != null && data1.getSys() != null) {
//                                long unixTimestamp = Long.parseLong(data1.getSys().getSunrise());
//                                long javaTimestamp = unixTimestamp * 1000L;
//                                Date date = new Date(javaTimestamp);
//                                String s1 = new SimpleDateFormat("hh:mm").format(date);
//                                sunr.setText(s1 + " AM");
//                            }
//
//
//                            TextView suns = getActivity().findViewById(R.id.sunset);
//                            if (data1 != null && data1.getSys() != null) {
//                                long unixTimestamp = Long.parseLong(data1.getSys().getSunset());
//                                long javaTimestamp = unixTimestamp * 1000L;
//                                Date date = new Date(javaTimestamp);
//                                String s2 = new SimpleDateFormat("hh:mm").format(date);
//
//                                suns.setText(s2 + " PM");
//                            }
//
//                            TextView longi = getActivity().findViewById(R.id.lon);
//                            if (data1 != null && data1.getCoord() != null) {
//                                longi.setText(data1.getCoord().getLon());
//                            }
//                            TextView description = getActivity().findViewById(R.id.description);
//                            ImageView im = getActivity().findViewById(R.id.img);
//                            ArrayList<Detail> det = null;
//                            if (data1 != null) {
//                                det = data1.getWeather();
//
//
//                            }
//                            if (det != null) {
//                                description.setText(det.get(0).getMain());
//                                type = String.valueOf(det.get(0).getMain());
//                                Log.e("code", ":" + det.get(0).getIcon());
//                                String ur = "http://openweathermap.org/img/w/" + det.get(0).getIcon() + ".png";
////                                Log.e("uri",":"+ur);
//                                Picasso.get().load(ur).into(im);
////                                im.setImageURI(Uri.parse(ur));
//
//                                im.setVisibility(View.VISIBLE);
//                            }
//                            if (det != null) {
//                                ImageView im1 = getActivity().findViewById(R.id.img1);
////                                description.setText(det.get(0).getMain());
////                                Log.e("code", ":" + det.get(0).getIcon());
//                                String ur = "http://openweathermap.org/img/w/" + det.get(0).getIcon() + ".png";
////                                Log.e("uri",":"+ur);
//                                Picasso.get().load(ur).into(im1);
////                                im.setImageURI(Uri.parse(ur));
//
////                                im.setVisibility(View.VISIBLE);
//                            }
//                            TextView humid = getActivity().findViewById(R.id.humidity);
//                            String t3 = "N.A";
//                            if (data1 != null && data1.getMain() != null) {
//                                t3 = data1.getMain().getHumidity() + "  %";
//                            }
//                            humid.setText(t3);
//                            TextView pres = getActivity().findViewById(R.id.pressure);
//                            String t4 = "N.A";
//                            if (data1 != null && data1.getMain() != null) {
//                                t4 = data1.getMain().getPressure() + "  mBar";
//                            }
//                            pres.setText(t4);
//                            TextView visi = getActivity().findViewById(R.id.visibility);
//                            String t5 = "N.A";
//                            if (data1 != null && data1.getVisibility() != null) {
//                                t5 = data1.getVisibility() + "  m";
//                            }
//                            visi.setText(t5);
//                            TextView clouds = getActivity().findViewById(R.id.clouds);
//                            if (data1.getClouds() != null) {
//                                clouds.setText(data1.getClouds().getAll());
//                                myclouds = Integer.parseInt(data1.getClouds().getAll());
//                            }
//                            TextView chances = getActivity().findViewById(R.id.chances);
//                            if (data1.getClouds() != null) {
//                                int ch1 = Integer.parseInt(data1.getClouds().getAll().toString());
//                                int ch = 0;
//                                if (ch1 > 75) {
//                                    ch = 50;
//                                } else if (ch1 <= 75 && ch1 > 50) {
//                                    ch = 20;
//                                } else if (ch1 <= 50 && ch1 > 20) {
//                                    ch = 10;
//                                } else {
//                                    ch = 0;
//                                }
//
//
//                                chances.setText("" + ch);
//                                ImageView img1 = getActivity().findViewById(R.id.q1), img2 = getActivity().findViewById(R.id.q2), img3 = getActivity().findViewById(R.id.q3), img4 = getActivity().findViewById(R.id.q4);
//                                TextView tt1 = getActivity().findViewById(R.id.tt1);
//                                if (ch < 70) {
//                                    img1.setBackgroundResource(R.drawable.image1);
//                                    img2.setBackgroundResource(R.drawable.image2);
//                                    img3.setBackgroundResource(R.drawable.image3);
//                                    img4.setBackgroundResource(R.drawable.image4);
//                                    tt1.setText("Great Day for Outdoor Activities !");
//                                } else {
//
//                                    img1.setBackgroundResource(R.drawable.i1);
//                                    img2.setBackgroundResource(R.drawable.i2);
//                                    img3.setBackgroundResource(R.drawable.i3);
//                                    img4.setBackgroundResource(R.drawable.i4);
//                                    tt1.setText("Great Day for Indoor Activities !");
//                                }
//
//                            }
//
//                            TextView speed = getActivity().findViewById(R.id.speed);
//                            String t6 = "0";
//                            if (data1.getWind() != null) {
//                                t6 = data1.getWind().getSpeed() + "  km/hr";
//                                mywind = Float.parseFloat(data1.getWind().getSpeed());
//                            }
//                            speed.setText(t6);
//
//                            Intent intent = new Intent(getActivity(), MainActivity1.class);
//
//
//                            PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 123, intent, PendingIntent.FLAG_UPDATE_CURRENT);
////                this pending intent will be use to launch intent. it will wait till the notifciation is clicked to do the defined work
//
//                            StringBuffer sender = new StringBuffer();
//                            StringBuffer sender1 = new StringBuffer();
//                            sender1.append("");
//                            if (mytemp >= 30) {
//                                sender.append("Warm day ahead !Carry some Drinks");
//                            } else if (mytemp <= 20) {
//                                sender.append("Cold day ahead !Carry some Warmies");
//                            } else if (mytemp > 20 && mytemp < 30) {
//                                sender.append("Pleasent Day ahead. Enjoy !");
//                            }
//                            if (myclouds >= 75) {
//                                sender.append("• Take umbrella");
//                            } else if (myclouds < 75 && myclouds >= 40) {
//                                sender.append("• Cloudy");
//                            } else if (myclouds < 40 && myclouds >= 10) {
//                                sender.append("• Gently Cloudy");
//                            } else {
//                                sender.append("• Scattered Clouds");
//                            }
//                            if (mywind >= 5) {
//                                if (sender.length() > 40) {
//                                    sender1.append("• Windy.");
//                                } else {
//                                    sender.append("• Windy");
//                                }
//                            } else {
//                                if (sender.length() > 40) {
//                                    sender1.append("• Gentle Winds.");
//                                } else {
//                                    sender.append("• Gentle Winds");
//                                }
//                            }
////                sender.append(".");
//
//                            Notification notification = new NotificationCompat.Builder(getActivity(), channelid)
//                                    .setSmallIcon(R.drawable.notif)
////                        .setContentTitle("Weather")
//                                    .setContentText("" + strAddress + "•" + type + "•" + mytemp + "°C")
//                                    .setPriority(1)
//                                    .setStyle(new NotificationCompat.InboxStyle()
//                                            .addLine("" + mylocation + "•" + type + "•" + mytemp)
//                                            .addLine(sender)
//                                            .addLine(sender1))
//                                    .setContentIntent(pendingIntent)
//                                    .setAutoCancel(true)
////                        set auto cancel is used to remove the notification after clicking over it.
//                                    .build();
//                            notificationManager.notify(123, notification);
//
//                            TextView degree = getActivity().findViewById(R.id.degree);
//                            String t7 = "0";
//                            if (data1.getWind() != null && data1.getWind().getDeg() != null) {
//                                t7 = data1.getWind().getDeg() + "  deg (N)";
//                            }
//                            degree.setText(t7);
//                            notificationflag = 1;
//                            TextView textView = getActivity().findViewById(R.id.mapclick);
//                            textView.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    Intent i = new Intent(getActivity(), MapsActivity.class);
//                                    i.putExtra("lat", data1.getCoord().getLat());
//                                    i.putExtra("lon", data1.getCoord().getLon());
//                                    startActivity(i);
//
//                                }
//                            });
//
//
//                        }
//                    }
//                });
//            }
//        });
//    }
//
//    @NonNull
//    @Override
//    public Result doWork() {
//        String url = "http://api.openweathermap.org/data/2.5/weather?zip=110033,in&appid=830b493e798279185f328c76df4a9e03";
//
//        boolean fla = isOnline();
//        lm = (LocationManager) getAActivity().getSystemService(LOCATION_SERVICE);
//
//        Boolean ab = false;
//        ab = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        if (ab == false) {
//            new AlertDialog.Builder(
//                    getActivity())
//                    .setTitle("GPS ERROR")
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .setMessage("Your GPS seems to be disabled, Please enable it?")
//                    .setCancelable(false)
//
//                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//                            System.exit(0);
//                        }
//                    })
//                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            System.exit(0);
//                        }
//                    })
//                    .show();
//
//        } else {
//
//            if (fla == false) {
//
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("NETWORK ALERT")
//                        .setMessage("This app needs an active internet connection.Please make sure you'r device is connected or just connect your device to any of these networks.")
//                        .setCancelable(false)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setPositiveButton("WIFI", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getActivity(), "Start the app after connecting", Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//
////                            Toast.makeText(getActivity(), "Start the app after connecting", Toast.LENGTH_LONG).show();
//                                System.exit(0);
//                            }
//                        })
//                        .setNegativeButton("CELLULAR", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
////                            startActivity(new Intent(sett.ACTION_OP));
//                                Toast.makeText(getActivity(), "Start the app after connecting", Toast.LENGTH_LONG).show();
//
//                                Intent intent = new Intent(Intent.ACTION_MAIN);
//                                intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
//                                startActivity(intent);
//
//                                Toast.makeText(getActivity(), "Start the app after connecting", Toast.LENGTH_LONG).show();
//                                System.exit(0);
//                            }
//                        })
//                        .show();
//
//            } else {
//
//                ScrollView ss = getActivity().findViewById(R.id.sview);
//                ss.setVisibility(View.VISIBLE);
//                ActivityCompat.requestPermissions(getActivity(),
//                        new String[]{
//                                Manifest.permission.ACCESS_COARSE_LOCATION,
//                                Manifest.permission.ACCESS_FINE_LOCATION}, 123);
////            lm = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
//
//                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//                        && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                    startLocationUpdates(lm);
//                } else {
//                    //request the location
//                    ActivityCompat.requestPermissions(getActivity(),
//                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                                    Manifest.permission.ACCESS_COARSE_LOCATION
//                            },
//                            12345);
//                }
//
//                boolean network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//                Location location;
//
//                if (network_enabled) {
//
//                    location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
////                location=lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,);
//
//                    if (location != null) {
//                        longitude = location.getLongitude();
//                        latitude = location.getLatitude();
//                    } else {
//                        longitude = 77.22;
//                        latitude = 28.65;
////                    Toast.makeText(getActivity(),"Please turn on your Location",Toast.LENGTH_LONG).show();
//                    }
//                }
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Geocoder gc = new Geocoder(getActivity());
//                        if (gc.isPresent()) {
//                            List<Address> list = null;
//                            try {
//                                list = gc.getFromLocation(latitude, longitude, 1);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            Address address = list.get(0);
//                            StringBuffer str = new StringBuffer();
//                            str.append("Name:" + address.getLocality() + "\n");
//                            str.append("Sub-Admin Ares:" + address.getSubAdminArea() + "\n");
//                            str.append("Admin Area:" + address.getAdminArea() + "\n");
//                            str.append("Country:" + address.getCountryName() + "\n");
//                            str.append("Country Code:" + address.getCountryCode() + "\n");
//
//                            strAddress = address.getLocality();
//                            Log.e("CURRENT ADDRESS", "" + address.getLocality());
//                             url = "http://api.openweathermap.org/data/2.5/weather?q=" + strAddress + "&appid=830b493e798279185f328c76df4a9e03";
//
//                        }
//                    }
//                });
//            makenetworkcall(url);
//        return null;
//    }
//}
