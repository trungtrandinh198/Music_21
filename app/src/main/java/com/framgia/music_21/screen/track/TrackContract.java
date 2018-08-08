package com.framgia.music_21.screen.track;

import com.framgia.music_21.data.model.Track;
import java.util.List;

public interface TrackContract {

    interface View {
        void showGenres(List<Track> tracks);

        void onError(Exception e);
    }

    interface Presenter {
        void getTrackByGenres(String genres);
    }
}
