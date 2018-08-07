package com.framgia.music_21.data.repository;

import com.framgia.music_21.data.model.TrackList;
import com.framgia.music_21.data.model.Track;
import com.framgia.music_21.data.source.RequestData;
import com.framgia.music_21.data.source.TrackDataSources;
import com.framgia.music_21.data.source.remote.TrackRemoteDataSources;

public class TrackRepository implements TrackDataSources.RemoteDataSources {
    private static TrackRepository sInstance;
    private TrackRemoteDataSources mTrackRemoteDataSources;

    private TrackRepository(TrackRemoteDataSources trackRemoteDataSources) {
        mTrackRemoteDataSources = trackRemoteDataSources;
    }

    public static synchronized TrackRepository getsInstance(TrackRemoteDataSources trackRemoteDataSources) {
        if (sInstance == null) {
            sInstance = new TrackRepository(trackRemoteDataSources);
        }
        return sInstance;
    }

    @Override
    public void getTrack(String id, RequestData<Track> callback) {
        mTrackRemoteDataSources.getTrack(id, callback);
    }

    @Override
    public void getListTrack(String genres, RequestData<TrackList> callBack) {
        mTrackRemoteDataSources.getListTrack(genres, callBack);
    }
}
