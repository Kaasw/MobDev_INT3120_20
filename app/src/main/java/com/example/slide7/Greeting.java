package com.example.slide7;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Greeting extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);
        Button backButton = findViewById(R.id.backButton);
        textView = findViewById(R.id.message);
        Intent intent = this.getIntent();
        String data = intent.getStringExtra("messages");
        String fullName = intent.getStringExtra("name");
        textView.setText(data);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", fullName);
                setResult(78, intent);
                Greeting.super.onBackPressed();
            }
        });

    }


}