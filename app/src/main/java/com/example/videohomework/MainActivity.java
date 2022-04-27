package com.example.videohomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.videohomework.databinding.ActivityMainBinding;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class MainActivity extends AppCompatActivity {

    String videolink = "https://firebasestorage.googleapis.com/v0/b/lacture1-1baf2.appspot.com/o/video%2FRiver%20Nature%20Sounds%20-%20Without%20Music%20_%20Awesome%20Cute%20Nature%20Videos.mp4?alt=media&token=bd534b82-f1e3-469c-a955-cf26e50725fe";
    ActivityMainBinding binding;
    PlayerView pv;
    SimpleExoPlayer player;
    boolean playerwhenread= true;
    long currentposition= 0;
    int currentwindow= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pv = binding.playerView;
    }
    private void initplayer(){
        player = new SimpleExoPlayer.Builder(this).build();
        pv.setPlayer(player);
        MediaItem item=  MediaItem.fromUri(videolink);
        player.setMediaItem(item);
        player.setPlayWhenReady(playerwhenread);
        player.seekTo(currentwindow,currentposition);
        player.prepare();
    }
    private void releaseplayer(){
        if(player != null){
            playerwhenread = player.getPlayWhenReady();
            currentwindow = player.getCurrentWindowIndex();
            currentposition = player.getCurrentPosition();
            player = null;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        initplayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player != null){
            initplayer();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseplayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseplayer();
    }
}