package com.example.externalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String file = "hello.txt";

    private Button save;
    private Button read;
    private EditText editText;
    private TextView textView;
    private TextView source;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        read = findViewById(R.id.readButton);
        save = findViewById(R.id.save);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        source = findViewById(R.id.source);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void save() throws IOException {
        String text = editText.getText().toString();
        File external = new File(getExternalFilesDir(null), file);
        FileOutputStream fos = new FileOutputStream(external);
        fos.write(text.getBytes());
        fos.close();
    }

    public void read() throws IOException {
        File external = new File(getExternalFilesDir(null), file);
        FileInputStream fis = new FileInputStream(external);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String text;

        while ((text = br.readLine()) != null) {
            sb.append(text).append("\n");
        }

        textView.setText(sb.toString());
        source.setText(getExternalFilesDir(null) + "/" + file);
    }
}