package com.dipanshu.mycityapp.Weather_dir;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class AlarmClass extends AppCompatActivity {

    //    Intent intent = getIntent();
    NotificationManager notificationManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            String channelid="000000";
//            NotificationChannel notificationChannel = new NotificationChannel(channelid, "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//        Toast.makeText(getBaseContext(),"I AM HERE",Toast.LENGTH_SHORT).show();
//        String channelid = "123";
//        Notification notification = new NotificationCompat.Builder(getBaseContext(), channelid)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentTitle("Title")
//                .setContentText("Hey, Alarm is Fired !")
//                .setPriority(1)
//                .setAutoCancel(true)
//                .build();
//        Log.e("TAG", "onClick: " + "notification is set");
//        notificationManager.notify(123, notification);
//        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(Myworker.class).build();
//        WorkManager.getInstance().enqueue(request);


    }

}
