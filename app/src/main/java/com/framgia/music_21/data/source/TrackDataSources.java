package com.framgia.music_21.data.source;

import com.framgia.music_21.data.model.Track;
import com.framgia.music_21.data.model.TrackList;

public interface TrackDataSources {

    interface RemoteDataSources extends TrackDataSources {
        void getTrack(String id, RequestData<Track> callback);

        void getListTrack(String genres, RequestData<TrackList> callBack);
    }
}
