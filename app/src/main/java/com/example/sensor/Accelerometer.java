package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelerometer {
    public interface Listener {
        void onRotation(float rx, float ry, float rz);
    }

    private Accelerometer.Listener listener;

    public void setListener(Accelerometer.Listener l) {
        listener = l;
    }

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    Accelerometer(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(android.hardware.SensorEvent event) {
                if (listener != null) {
                    listener.onRotation(event.values[0], event.values[1], event.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    public void register() {
        sensorManager.registerListener(sensorEventListener, sensor, 1000000,1000000);
    }
    public void unregister() {
        sensorManager.unregisterListener(sensorEventListener);
    }
}
