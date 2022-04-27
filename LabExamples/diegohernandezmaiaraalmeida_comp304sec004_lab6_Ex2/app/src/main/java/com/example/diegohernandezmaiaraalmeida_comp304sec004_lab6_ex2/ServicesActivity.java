package com.example.diegohernandezmaiaraalmeida_comp304sec004_lab6_ex2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ServicesActivity extends AppCompatActivity {
    private TextView textView;
    //replace with your package name
    public static final String INFO_INTENT =
            "com.example.inika.samplelab6_2.INFO_UPDATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        textView = (TextView) findViewById(R.id.textView);
    }
    //
    public void startService(View view) {
        startService(new Intent(getBaseContext(), SimpleService.class));
    }
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(),
                SimpleService.class));
    }
}
