package com.framgia.music_21.screen.playmusic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.framgia.music_21.R;

public class PlayMusicActivity extends AppCompatActivity implements PlayMusicContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
    }

    @Override
    public void onPlayMusic() {

    }

    @Override
    public void onNextMusic() {

    }

    @Override
    public void onBackMusic() {

    }

    @Override
    public void onPauseMusic() {

    }

    @Override
    public void onLoopMusic() {

    }
}
