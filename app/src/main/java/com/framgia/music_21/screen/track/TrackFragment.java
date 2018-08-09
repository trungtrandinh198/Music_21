package com.framgia.music_21.screen.track;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_21.R;
import com.framgia.music_21.data.model.Track;
import com.framgia.music_21.data.repository.TrackRepository;
import com.framgia.music_21.data.source.remote.TrackRemoteDataSources;
import java.util.List;

public class TrackFragment extends Fragment implements TrackContract.View {

    private static final String ARGUMENT_GENRES = "ARGUMENT_GENRES";
    private TrackAdapter mTrackAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track, container, false);
        initView(view);
        initData();
        return view;
    }

    public static TrackFragment getInstance(String genre) {
        TrackFragment trackFragment = new TrackFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_GENRES, genre);
        trackFragment.setArguments(args);
        return trackFragment;
    }

    private void initView(View view) {
        RecyclerView recyclerViewTracks = view.findViewById(R.id.recyclerview_track_track);
        mTrackAdapter = new TrackAdapter();
        recyclerViewTracks.setAdapter(mTrackAdapter);
    }

    private void initData() {
        TrackRepository trackRepository =
                TrackRepository.getsInstance(TrackRemoteDataSources.getInstance());
        TrackPresenter trackPresenter = new TrackPresenter(this, trackRepository);
        trackPresenter.getTrackByGenres(getArguments().getString(ARGUMENT_GENRES));
    }

    @Override
    public void showGenres(List<Track> tracks) {
        mTrackAdapter.updateData(tracks);
    }

    @Override
    public void onError(Exception e) {

    }
}
