package com.example.musicvideoplayer;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button musicBtn = findViewById(R.id.music);
        Button videoBtn = findViewById(R.id.video);
        VideoView videoPlayer = findViewById(R.id.videoView);

        MediaPlayer mp = MediaPlayer.create(this, R.raw.f1);

        musicBtn.setOnClickListener( v ->{
            if(mp.isPlaying()){
                mp.pause();
                musicBtn.setText("Play Music");
            }
            else{
                mp.start();
                musicBtn.setText("Pause music");
            }
        });

        videoBtn.setOnClickListener( v ->{
            Uri url = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");

            videoPlayer.setVideoURI(url);

            MediaController mediaController = new MediaController(this);
            videoPlayer.setMediaController(mediaController);
            mediaController.setAnchorView(videoPlayer);

            if(videoPlayer.isPlaying()){
                videoPlayer.pause();
                videoBtn.setText("Play Video");
            }
            else{
                videoPlayer.start();
                videoBtn.setText("Pause Video");
            }
        });







    }
}