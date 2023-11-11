package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etA, etB, etC;
    Button buttonLCM, buttonPrime;
    IMyAidInterface mClac;
    ServiceConnection srcConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mClac = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etA = (EditText) findViewById(R.id.etA);
        etB = (EditText) findViewById(R.id.etB);
        etC = (EditText) findViewById(R.id.etC);

        buttonLCM = (Button) findViewById(buttonLCM);
        buttonPrime = (Button) findViewById(buttonPrime);

        buttonLCM.setOnClickListener(this);
        buttonPrime.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonLCM) {
            int LCM = 0;
            try {
                LCM = mClac.getLCM(Integer.parseInt(etA.getText().toString()),
                        Integer.parseInt(etB.getText().toString()));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
}