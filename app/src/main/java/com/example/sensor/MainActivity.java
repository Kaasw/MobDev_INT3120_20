package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Gyroscope gyroscope;
    private Light light;
    private TextView gyroscopeX;
    private TextView gyroscopeY;
    private TextView gyroscopeZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gyroscopeX = findViewById(R.id.gyroscopeX);
        gyroscopeY = findViewById(R.id.gyroscopeY);
        gyroscopeZ = findViewById(R.id.gyroscopeZ);

        light = new Light(this);
        gyroscope = new Gyroscope(this);

        light.setListener(new Light.Listener() {
            @Override
            public void onRotation(float x) {
                gyroscopeX.setText("" + x);

            }
        });

        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // this will send notification to
        // both the sensors to register
        gyroscope.register();
        light.register();
    }

    // create on pause method
    @Override
    protected void onPause() {
        super.onPause();

        // this will send notification in
        // both the sensors to unregister
        gyroscope.unregister();
        light.unregister();
    }

}