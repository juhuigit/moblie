package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver wifistateReceiver = new BroadcastReceiver() {
        public void onReceive (Context context, Intent intent) {
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifistate) {
                case WifiManager.WIFI_STATE_ENABLED:
                    Log.d("TEST", "WIFI_STATE_ENABLED");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.registerReceiver(this.wifistateReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
    }

    protected void onDestroy(){
        super.onDestroy();
        this.unregisterReceiver(wifistateReceiver);
    }
}