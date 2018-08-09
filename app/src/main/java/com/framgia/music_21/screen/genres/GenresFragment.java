package com.framgia.music_21.screen.genres;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_21.R;
import com.framgia.music_21.data.model.Genres;
import com.framgia.music_21.screen.track.TrackFragment;
import com.framgia.music_21.utils.Constaint;
import java.util.ArrayList;
import java.util.List;

public class GenresFragment extends Fragment implements GenresContract.View, ItemClickListener {
    private GenresAdapter mGenresAdapter;
    private List<Genres> mGenresList = new ArrayList<>();

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
        mGenresAdapter = new GenresAdapter(this);
        recyclerViewGenres.setAdapter(mGenresAdapter);
        addDataGenres();
    }

    @Override
    public void onItemClicked(int position) {
        Fragment fragment = TrackFragment.getInstance(mGenresList.get(position).getTitle());
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void addDataGenres() {
        if (mGenresList != null) {
            mGenresList.clear();
        }
        mGenresList.add(new Genres(Constaint.MUSIC, Constaint.IMAGE_MUSIC));
        mGenresList.add(new Genres(Constaint.HOT, Constaint.IMAGE_HOT));
        mGenresList.add(new Genres(Constaint.COUNTRY, Constaint.IMAGE_COUTRY));
        mGenresList.add(new Genres(Constaint.HIPHOP, Constaint.IMAGE_HIPHOP));
        mGenresAdapter.updateData(mGenresList);
    }
}
