package com.framgia.music_21.data.source;

import com.framgia.music_21.data.model.ListTrack;
import com.framgia.music_21.data.model.Track;

public interface TrackDataSources {

    interface RemoteDataSources extends TrackDataSources {
        void getTrack(String id, RequestData<Track> callback);

        void getListTrack(String genres, RequestData<ListTrack> callBack);
    }
}
