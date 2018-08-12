package com.framgia.music_21.screen.home;

import com.framgia.music_21.data.model.Track;
import java.util.List;

public interface HomeContact {
    interface View {
        void showTrackHot(List<Track> tracks);

        void onError(Exception e);
    }

    interface Presenter {
        void getTrackHot(String genres);
    }
}
