package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class AirplaneChange extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (isAirplaneModeOn(context.getApplicationContext())) {
            Toast.makeText(context, "Bluetooth is on", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Bluetooth is off", Toast.LENGTH_SHORT).show();
        }
    }

    private static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.Global.BLUETOOTH_ON, 0) != 0;
    }
}