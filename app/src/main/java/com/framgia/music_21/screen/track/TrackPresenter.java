package com.framgia.music_21.screen.track;

import com.framgia.music_21.data.model.TrackList;
import com.framgia.music_21.data.repository.TrackRepository;
import com.framgia.music_21.data.source.RequestData;

public class TrackPresenter implements TrackContract.Presenter {
    private TrackContract.View mView;
    private TrackRepository mTrackRepository;

    public TrackPresenter(TrackContract.View view, TrackRepository trackRepository) {
        mView = view;
        mTrackRepository = trackRepository;
    }

    @Override
    public void getTrackByGenres(String genres) {
        mTrackRepository.getListTrack(genres, new RequestData<TrackList>() {
            @Override
            public void onSuccess(TrackList data) {
                mView.showGenres(data.getTrackList());
            }

            @Override
            public void onFail(Exception e) {
                mView.onError(e);
            }
        });
    }
}
