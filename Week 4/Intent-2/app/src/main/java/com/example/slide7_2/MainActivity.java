package com.example.slide7_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openWeb(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/guide/components/intents-filters") );
        startActivity(intent);
    }

    public void playMusic(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("spotify:playlist:3iS9HVHHZ04XjmTLUzNc91:play"));
        startActivity(intent);
    }

    public void showMap(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.5020952,-81.6789717"));
        startActivity(intent);
    }
}