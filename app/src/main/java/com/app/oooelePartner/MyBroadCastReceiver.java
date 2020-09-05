package com.app.oooelePartner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //  Log.e("MyBroadCastReceiver", "onReceive");

        //if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()))
        {
            Intent service = new Intent(context, MyService.class);
            context.startService(service);
            // Log.e("BootCompleteReceiver", " __________BootCompleteReceiver _________");

        }
    }
}