package com.dipanshu.mycityapp.Weather_dir;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;

/**
 * Implementation of App Widget functionality.
 */
public class appwidget extends AppWidgetProvider {

    String newdata="16°COOL";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
//        onUpdate(context,AppWidgetManager.getInstance(context),appWidgetId);
    }

    void updateAppWidget(final Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {


        Intent intent = new Intent(context,MyServices.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }

    }

//    String newdata="16COOL";
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        PendingIntent pendingIntent=null;
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);


            final AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            final Intent i = new Intent(context, MyServices.class);

            if (pendingIntent == null) {
                pendingIntent = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(i);
                } else {
                    context.startService(i);
                }
            }

            manager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), 1800000, pendingIntent);

            }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

