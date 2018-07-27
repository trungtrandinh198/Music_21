package com.framgia.music_21.screen.genres;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.framgia.music_21.R;
import com.framgia.music_21.data.model.Genres;
import java.util.ArrayList;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {
    private ArrayList<Genres> mGenres;
    private Context mContext;

    public GenresAdapter(Context context, ArrayList<Genres> genres) {
        mContext = context;
        mGenres = genres;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genres, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBindView(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        if (mGenres != null) {
            return mGenres.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewBannerGenres;
        private TextView mTextViewNameGenres;

        ViewHolder(View itemView) {
            super(itemView);
            mTextViewNameGenres = itemView.findViewById(R.id.txt_name_genres);
            mImageViewBannerGenres = itemView.findViewById(R.id.img_banner_genres);
        }

        void onBindView(Genres genres) {
            mTextViewNameGenres.setText(genres.getTitle());
            if (genres.getBanner() != null) {
                Glide.with(mContext).load(genres.getBanner()).into(mImageViewBannerGenres);
            }
        }
    }
}
