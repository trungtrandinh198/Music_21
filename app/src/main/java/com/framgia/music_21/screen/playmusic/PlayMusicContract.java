package com.framgia.music_21.screen.playmusic;

public interface PlayMusicContract {

    interface View {
        void onPlayMusic();

        void onNextMusic();

        void onBackMusic();

        void onPauseMusic();

        void onLoopMusic();
    }

    interface Presenter {

    }
}
