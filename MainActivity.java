package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button buttonstart, buttonstop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonstart = (Button) findViewById(R.id.start);
        buttonstop = (Button) findViewById(R.id.stop);
        buttonstart.setOnClickListener(this);
        buttonstop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.start) {
            startService(new Intent(this, MyService.class));
        } else {
            stopService(new Intent(this, MyService.class));
        }
    }
}

class MyService extends Service {
    public MyService(){}
    MediaPlayer player;

    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, R.raw.just_dance);
        player.setLooping(true);
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}
