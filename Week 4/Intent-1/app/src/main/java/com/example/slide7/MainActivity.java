package com.example.slide7;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private TextView feedback;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    Log.d(TAG, "onActivityResult");
                    if(o.getResultCode() == 78 ) {
                        Intent intent = o.getData();
                        if (intent != null) {
                            String data = intent.getStringExtra("result");
                            feedback.setText("Hello " + data);
                        }
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        feedback = findViewById(R.id.feedback);
        Button sendGreeting = findViewById(R.id.send);
        sendGreeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessages();
            }
        });

    }

    public void sendMessages() {
        TextView textView = findViewById(R.id.typeName);
        String fullname = textView.getText().toString();
        String messages = "Say hello";
        Intent intent = new Intent(this, Greeting.class);
        intent.putExtra("name", fullname);
        intent.putExtra("messages", messages);
        setResult(12 ,intent);
        activityResultLauncher.launch(intent);


    }


}