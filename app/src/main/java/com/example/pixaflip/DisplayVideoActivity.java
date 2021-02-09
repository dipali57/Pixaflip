package com.example.pixaflip;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class DisplayVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_video);

        VideoView videoView = findViewById(R.id.playVideo);
        String videoPath="android.resource://" + getPackageName() + "/" +R.raw.video;
        Uri uri=Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.start();



        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {


            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent (getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
