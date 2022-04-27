package com.example.diegohernandezmaiaraalmeida_comp304sec004_lab6_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class SimpleService extends Service {
    //replace with your package name
    public static final String INFO_INTENT =
            "com.example.inika.samplelab6_2.INFO_UPDATE";

    public SimpleService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(INFO_INTENT);
        broadcastIntent.putExtra(INFO_INTENT,
                "Hello there! A simple service is sending this message to you!");
        this.sendBroadcast(broadcastIntent);
        //
        return START_STICKY;
        public static final String INFO_INTENT = "com.example.inika.samplelab6_2.INFO_UPDATE";
        //This will handle the broadcast
        public BroadcastReceiver receiver = new BroadcastReceiver() {
            //@Override
            public void onReceive(Context context, Intent intent) {
                //textView.setText("Here");
                String action = intent.getAction();
                if (action.equals(SimpleService.INFO_INTENT)) {
                    String info = intent.getStringExtra(INFO_INTENT);
                    textView.setText(info);
                }
            }
        };
    }
    public void onResume()
    {
        super.onResume();
        //This needs to be in the activity that will end up receiving the
        broadcast
        registerReceiver(receiver, new IntentFilter(INFO_INTENT));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}

