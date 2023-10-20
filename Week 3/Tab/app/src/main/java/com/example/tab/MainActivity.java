package com.example.tab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabhost = findViewById(R.id.tabhost);

        tabhost.setup();

        TabHost.TabSpec spec = tabhost.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);

        spec.setIndicator("Tab One");

        tabhost.addTab(spec);

        spec = tabhost.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);

        spec.setIndicator("Tab Two");
        tabhost.addTab(spec);
        
        spec = tabhost.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Tab Three");
        tabhost.addTab(spec);
    }
}