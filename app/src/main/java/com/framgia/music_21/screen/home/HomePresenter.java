package com.framgia.music_21.screen.home;

import com.framgia.music_21.data.model.TrackList;
import com.framgia.music_21.data.repository.TrackRepository;
import com.framgia.music_21.data.source.RequestData;

public class HomePresenter implements HomeContact.Presenter {
    private HomeContact.View mView;
    private TrackRepository mTrackRepository;

    public HomePresenter(HomeContact.View view, TrackRepository trackRepository) {
        mView = view;
        mTrackRepository = trackRepository;
    }

    @Override
    public void getTrackHot(String genres) {
        mTrackRepository.getListTrack(genres, new RequestData<TrackList>() {
            @Override
            public void onSuccess(TrackList data) {
                mView.showTrackHot(data.getTrackList());
            }

            @Override
            public void onFail(Exception e) {
                mView.onError(e);
            }
        });
    }
}
