package com.bake.recommended_music_final.player;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bake.recommended_music_final.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayMusicVideoActivity extends YouTubeBaseActivity {
    YouTubePlayerView playerView;
    YouTubePlayer player;

    private static String API_KEY = "AIzaSyCnJH9Elr_UWHRSCnUvIHbT7OOzM7Ew4k0";
    private static String videoId = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music_video);
        videoId = this.getIntent().getStringExtra("videoId");
        initPlayer();

        Button button_play = findViewById(R.id.button_play);
        button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideo();
            }
        });

        //누르면 ViewMorePopup 연결시키기
        ImageView button_back = findViewById(R.id.imageView_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayMusicVideoActivity.super.onBackPressed();
            }
        });
    }

    public void initPlayer() {
        playerView = findViewById(R.id.playerView);
        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                player = youTubePlayer;

                player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                    }

                    @Override
                    public void onLoaded(String id) {
                        Log.d("PlayerView", "onLoaded 호출됨: " + id);

                        player.play();
                    }

                    @Override
                    public void onAdStarted() {
                    }

                    @Override
                    public void onVideoStarted() {
                    }

                    @Override
                    public void onVideoEnded() {
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        });
    }

    public void playVideo() {
        if (player != null) {
            if (player.isPlaying()) {
                player.pause();
            }
            player.cueVideo(videoId);
        }
    }

}
