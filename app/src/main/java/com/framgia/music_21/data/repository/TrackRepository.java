package com.framgia.music_21.data.repository;

import com.framgia.music_21.data.model.ListTrack;
import com.framgia.music_21.data.model.Track;
import com.framgia.music_21.data.source.RequestData;
import com.framgia.music_21.data.source.TrackDataSources;
import com.framgia.music_21.data.source.remote.ParseDataFromJson;

public class TrackRepository implements TrackDataSources.RemoteDataSources {
    private static TrackRepository sInstance;
    private ParseDataFromJson mParseDataFromJson;

    private TrackRepository(ParseDataFromJson parseDataFromJson) {
        mParseDataFromJson = parseDataFromJson;
    }

    public static synchronized TrackRepository getsInstance(ParseDataFromJson parseDataFromJson) {
        if (sInstance == null) {
            sInstance = new TrackRepository(parseDataFromJson);
        }
        return sInstance;
    }

    @Override
    public void getTrack(String id, RequestData<Track> callback) {
        mParseDataFromJson.getTrack(id, callback);
    }

    @Override
    public void getListTrack(String genres, RequestData<ListTrack> callBack) {
        mParseDataFromJson.getListTrack(genres, callBack);
    }
}
