package com.app.oooelePartner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service
{



    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        onTaskRemoved(intent);

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "Service Destroyed!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent("com.app.oooelePartner");
        //Intent intent = new Intent("android.intent.action.BOOT_COMPLETED");
        intent.putExtra("yourvalue", "torestore");
        sendBroadcast(intent);
        super.onDestroy();

    }



    @Override public void onTaskRemoved(Intent rootIntent)
    {
       // Log.e("onTaskRemoved", "Called!");


        //send broadcast to your BroadcastReciever
        Intent intent = new Intent("com.app.oooelePartner");
        //unique String to uniquely identify your broadcastreceiver
        //Intent intent = new Intent("android.intent.action.BOOT_COMPLETED");
        intent.putExtra("yourvalue", "torestore");
        sendBroadcast(intent);
        //intent to restart your service.
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());

        PendingIntent restartServicePendingIntent =
                PendingIntent.getService(getApplicationContext(), 1,
                        restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getApplicationContext()
                .getSystemService(Context.ALARM_SERVICE);
        if (alarmService != null) {
            alarmService.set(
                    AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime() + 1000,
                    restartServicePendingIntent);
        }

        super.onTaskRemoved(rootIntent);

    }}
