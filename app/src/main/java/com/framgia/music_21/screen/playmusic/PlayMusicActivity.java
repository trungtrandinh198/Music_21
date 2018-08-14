package com.framgia.music_21.screen.playmusic;

import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.framgia.music_21.R;
import com.framgia.music_21.data.model.Track;
import com.framgia.music_21.services.PlayTrackServices;
import com.framgia.music_21.utils.Constaint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PlayMusicActivity extends AppCompatActivity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private static final String PATH_DOWNLOAD = "file://sdcard/Download/";
    private static final String PATH = "/";
    private static final String PATH_MP3 = ".mp3";

    private PlayTrackServices mPlayTrackServices;
    private boolean mIsPlaying;
    private TextView mTextViewTitle, mTextViewUser, mTextViewStart, mTextViewEnd;
    private ImageView mImageViewAvatar, mImageViewPlay, mImageViewBack, mImageViewPause,
            mImageViewExit, mImageViewNext, mImageViewDowload;
    private SeekBar mSeekBarTimeLine;

    public static Intent getInsstance(Context context, List<Track> tracks, int position) {
        Intent intent = new Intent(context, PlayMusicActivity.class);
        intent.putParcelableArrayListExtra(Constaint.ARGUMENT_TRACK,
                (ArrayList<? extends Parcelable>) tracks);
        intent.putExtra(Constaint.ARGUMENT_POSITION, position);
        return intent;
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PlayTrackServices.TrackBinder binder = (PlayTrackServices.TrackBinder) iBinder;
            mPlayTrackServices = binder.getServices();
            setView();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        getData();
        initView();
    }

    private void initView() {
        mTextViewTitle = findViewById(R.id.txt_name_song_playmusic);
        mTextViewUser = findViewById(R.id.txt_name_singger_playmusic);
        mImageViewAvatar = findViewById(R.id.img_avataSong);
        mImageViewPlay = findViewById(R.id.img_playsong);
        mImageViewBack = findViewById(R.id.img_backsong);
        mImageViewDowload = findViewById(R.id.img_download);
        mImageViewExit = findViewById(R.id.img_backactivity);
        mSeekBarTimeLine = findViewById(R.id.seekbar_timeline);
        mTextViewStart = findViewById(R.id.text_timestart);
        mTextViewEnd = findViewById(R.id.text_timeend);

        mSeekBarTimeLine.setOnSeekBarChangeListener(this);
    }

    private void setView() {
        mTextViewTitle.setText(mPlayTrackServices.getTitle());
        mTextViewUser.setText(mPlayTrackServices.getUser());
        Glide.with(this).load(mPlayTrackServices.getAvatar()).into(mImageViewAvatar);
        mImageViewPlay.setImageResource(R.drawable.ic_play);
        mSeekBarTimeLine.setMax(mPlayTrackServices.mMediaPlayer.getDuration());
        mTextViewEnd.setText(converTime(mPlayTrackServices.mMediaPlayer.getDuration()));
        updateTime();
    }

    public void getData() {
        List<Track> trackList = getIntent().getParcelableArrayListExtra(Constaint.ARGUMENT_TRACK);
        int position = getIntent().getIntExtra(Constaint.ARGUMENT_POSITION, 0);
        Intent musicServices =
                PlayTrackServices.getSongsIntent(getApplication(), trackList, position);
        startService(musicServices);
        bindService(musicServices, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_playsong:
                playTrack();
                break;
            case R.id.img_backsong:
                setView();
                mPlayTrackServices.previousSong();
                break;
            case R.id.img_nextsong:
                setView();
                mPlayTrackServices.nextSong();
                break;
            case R.id.img_download:
                downLoad(mPlayTrackServices.ulrDownload());
                break;
            case R.id.img_style:
                break;
            case R.id.img_backactivity:
                finish();
                break;
        }
    }

    private void playTrack() {
        if (!mPlayTrackServices.isPlaying()) {
            mPlayTrackServices.playMusic();
            mImageViewPlay.setImageResource(R.drawable.ic_pause);
            mIsPlaying = true;
        } else {
            mPlayTrackServices.pauseMusic();
            mImageViewPlay.setImageResource(R.drawable.ic_play);
            mIsPlaying = false;
        }
    }

    private void downLoad(String url) {
        DownloadManager downloadManager =
                (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request mRequest = new DownloadManager.Request(uri);
        mRequest.setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        mRequest.setDestinationUri(Uri.parse(PATH_DOWNLOAD + mTextViewTitle.getText()));
        mRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                PATH + mTextViewTitle.getText() + PATH_MP3);
        Long reference = downloadManager.enqueue(mRequest);
    }

    public String converTime(int time) {
        SimpleDateFormat timefm = new SimpleDateFormat("mm:ss");
        return timefm.format(time);
    }

    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSeekBarTimeLine.setProgress(mPlayTrackServices.mMediaPlayer.getCurrentPosition());
                mTextViewStart.setText(
                        converTime(mPlayTrackServices.mMediaPlayer.getCurrentPosition()));
                handler.postDelayed(this,1000);
            }
        }, 0);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mPlayTrackServices.mMediaPlayer.seekTo(mSeekBarTimeLine.getProgress());
    }
}
