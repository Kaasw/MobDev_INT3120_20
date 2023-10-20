package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private int length;
    private Button pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        MediaPlayer mediaPlayer = new MediaPlayer();
        String url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(mp -> mediaPlayer.start());

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pause.getText() == "Pause") {
                    mediaPlayer.pause();

                    length = mediaPlayer.getCurrentPosition();
                    pause.setText("Resume");
                } else {

                    mediaPlayer.seekTo(length);
                    pause.setText("Pause");
                }
            }
        });



    }
}