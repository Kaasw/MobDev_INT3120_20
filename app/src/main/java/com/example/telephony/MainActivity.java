package com.example.telephony;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TelephonyManager telephonyManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        String networkCountryISO = telephonyManager.getNetworkCountryIso();
        String SIMCountryISO = telephonyManager.getSimCountryIso();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_PHONE_STATE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_PHONE_STATE}, 1);
            }
            return;
        }
        String softwareVersion = telephonyManager.getDeviceSoftwareVersion();
        String voiceMailNumber = telephonyManager.getVoiceMailNumber();

        String strphoneType = "";

        int phoneType = telephonyManager.getPhoneType();
        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strphoneType = "CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                strphoneType = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strphoneType = "NONE";
                break;
        }
        boolean isRoaming = telephonyManager.isNetworkRoaming();
        String info = "Phone Details:\n";
        info += "\n Network Country ISO: " + networkCountryISO;
        info += "\n SIM Country ISO: " + SIMCountryISO;
        info += "\n Software Version: " + softwareVersion;
        info += "\n Voice Mail Number: " + voiceMailNumber;
        info += "\n Phone Network Type: " + strphoneType;
        info += "\n In Roaming? : " + isRoaming;

        textView.setText(info);

    }
}