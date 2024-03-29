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
                sendIntent();
            }
        };
    }

    public void stoptimertask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void sendIntent() {
        Log.i(TAG, "Sending intent com.thalesifec.intent.action.EVENT_TIMELINE_EXTERNAL_EVENT_ADD with sample json");
        Intent intent = new Intent("com.thalesifec.intent.action.EVENT_TIMELINE_EXTERNAL_EVENT_ADD");
        intent.putExtra("com.thalesifec.cas.intent.extra.EXTRA_EVENT_TIMELINE_EXTERNAL_EVENT_JSON", getResources().getString(R.string.sample_json));
        //intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        sendBroadcast(intent);
        //Log.i(TAG, "Sent intent for EVENT_TIMELINE_EXTERNAL_EVENT_ADD with foreground flag added");
        Log.i(TAG, "Sent intent for EVENT_TIMELINE_EXTERNAL_EVENT_ADD");
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
