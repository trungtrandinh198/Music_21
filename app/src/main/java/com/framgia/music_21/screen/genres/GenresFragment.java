package com.framgia.music_21.screen.genres;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.framgia.music_21.R;
import com.framgia.music_21.data.model.Track;
import com.framgia.music_21.data.repository.TrackRepository;
import com.framgia.music_21.data.source.RequestData;
import com.framgia.music_21.data.source.remote.ParseDataSources;

public class GenresFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genres, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerViewGenres = view.findViewById(R.id.recyclerview_genres_genres);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewGenres.setLayoutManager(linearLayoutManager);
    }
}
