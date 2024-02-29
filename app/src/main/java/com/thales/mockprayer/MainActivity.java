package com.thales.mockprayer;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MockPrayer";
    Intent mPrayerServiceIntent;
    Context ctx;
    private PrayerService mPrayerService;

    public Context getCtx() {
        return ctx;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main);
        mPrayerService = new PrayerService(getCtx());
        mPrayerServiceIntent = new Intent(getCtx(), mPrayerService.getClass());
        if (!isMyServiceRunning(mPrayerService.getClass())) {
            startService(mPrayerServiceIntent);
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isPrayerServiceRunning?", true + "");
                return true;
            }
        }
        Log.i("isPrayerServiceRunning?", false + "");
        return false;
    }


    @Override
    protected void onDestroy() {
        stopService(mPrayerServiceIntent);
        Log.i(TAG, "onDestroy!");
        super.onDestroy();

    }
}