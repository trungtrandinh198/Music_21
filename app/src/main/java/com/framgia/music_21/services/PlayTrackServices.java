package com.framgia.music_21.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import com.framgia.music_21.data.model.Track;
import com.framgia.music_21.utils.Constaint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayTrackServices extends Service implements MediaPlayer.OnPreparedListener {
    private MediaPlayer mMediaPlayer;
    private List<Track> mTrackList;
    private final IBinder mIBinder = new TrackBinder();
    private int mPosition;

    public static Intent getSongsIntent(Context context, List<Track> tracks, int position) {
        Intent intent = new Intent(context, PlayTrackServices.class);
        intent.putParcelableArrayListExtra(Constaint.ARGUMENT_TRACK,
                (ArrayList<? extends Parcelable>) tracks);
        intent.putExtra(Constaint.ARGUMENT_POSITION, position);
        return intent;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        playMusic();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
        mTrackList = intent.getParcelableArrayListExtra(Constaint.ARGUMENT_TRACK);
        mPosition = intent.getIntExtra(Constaint.ARGUMENT_POSITION,0);
        if (mTrackList != null) initPlayTrack();
        return START_STICKY;
    }

    public void initPlayTrack() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(
                    mTrackList.get(mPosition).getStreamUlr() + Constaint.CLIENT_ID);
        } catch (IOException e) {
            e.printStackTrace();
            stopSelf();
        }
    }

    public String getTitle() {
        return mTrackList.get(mPosition).getTitle();
    }

    public String getUser() {
        return mTrackList.get(mPosition).getUsername();
    }

    public String getAvatar() {
        return mTrackList.get(mPosition).getAvatarUser();
    }

    public void playMusic() {

        mMediaPlayer.start();
    }

    public void stopMusic() {
        if (mMediaPlayer == null) return;
        if (mMediaPlayer.isPlaying()) mMediaPlayer.stop();
    }

    public void pauseMusic() {
        if (mMediaPlayer.isPlaying()) mMediaPlayer.pause();
    }

    public void resumeMusic() {
        if (mMediaPlayer.isPlaying()) mMediaPlayer.start();
    }

    public String ulrDownload() {
        return mTrackList.get(mPosition).getDownLoadUrl() + Constaint.CLIENT_ID;
    }

    public class TrackBinder extends Binder {
        public PlayTrackServices getServices() {
            return PlayTrackServices.this;
        }
    }
}
