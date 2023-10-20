package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private Random mGenerator = new Random();
    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }



}