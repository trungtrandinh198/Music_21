package com.framgia.music_21.screen.home;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_21.R;
import com.framgia.music_21.data.model.Track;
import com.framgia.music_21.data.repository.TrackRepository;
import com.framgia.music_21.data.source.remote.TrackRemoteDataSources;
import com.framgia.music_21.screen.playmusic.PlayMusicActivity;
import com.framgia.music_21.screen.track.ItemClickListener;
import com.framgia.music_21.screen.track.TrackAdapter;
import com.framgia.music_21.screen.track.TrackContract;
import com.framgia.music_21.screen.track.TrackPresenter;
import com.framgia.music_21.utils.Constaint;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContact.View, ItemClickListener {
    private TrackAdapter mTrackAdapter;
    private List<Track> mTrackList = new ArrayList<>();

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerViewTracks = view.findViewById(R.id.recyclerview_genres_home);
        mTrackAdapter = new TrackAdapter(this);
        recyclerViewTracks.setAdapter(mTrackAdapter);
    }

    private void initData() {
        TrackRepository trackRepository =
                TrackRepository.getsInstance(TrackRemoteDataSources.getInstance());
        HomePresenter homePresenter = new HomePresenter(this, trackRepository);
        homePresenter.getTrackHot(Constaint.HOT);
    }

    @Override
    public void showTrackHot(List<Track> tracks) {
        assert tracks != null;
        mTrackAdapter.updateData(tracks);
        mTrackList = tracks;
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onItemClicked(int position) {
        getActivity().startActivity(
                new Intent(PlayMusicActivity.getInsstance(getActivity(), mTrackList, position)));
    }
}
