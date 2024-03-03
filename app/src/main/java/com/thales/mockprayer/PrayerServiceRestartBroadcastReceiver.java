package com.thales.mockprayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PrayerServiceRestartBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(PrayerServiceRestartBroadcastReceiver.class.getSimpleName(), "Service Stops! Start Prayer service!!");
        context.startService(new Intent(context, PrayerService.class));
    }
}
