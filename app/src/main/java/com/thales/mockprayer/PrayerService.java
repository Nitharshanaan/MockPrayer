package com.thales.mockprayer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class PrayerService extends Service {
    private static final String TAG = PrayerService.class.getName();
    public int counter = 0;
    private Timer timer;
    private TimerTask timerTask;

    public PrayerService(Context applicationContext) {
        super();
        Log.i(TAG, "here I am!");
    }

    public PrayerService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG, "onStartCommand received");
        startTimer();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "ondestroy!");
        Intent broadcastIntent = new Intent(this, PrayerServiceRestartBroadcastReceiver.class);
        sendBroadcast(broadcastIntent);
        stoptimertask();
    }

    public void startTimer() {
        Log.i(TAG, "startTimer");
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 5000, 5000);
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                Log.i(TAG, "in timer ++++  " + (counter++));
                //To Do: Here goes our prayer broadcast intent.
            }
        };
    }

    public void stoptimertask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
