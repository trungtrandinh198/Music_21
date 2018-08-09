package com.framgia.music_21.screen.genres;

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
import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {
    private List<Genres> mGenres = new ArrayList<>();
    private ItemClickListener mItemClickListener;

    GenresAdapter(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genres, parent, false);
        return new ViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBindView(mGenres.get(position));
    }

    public void updateData(List<Genres> genres) {
        if (genres == null) {
            return;
        }
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageViewBannerGenres;
        private TextView mTextViewNameGenres;
        private ItemClickListener mItemClickListener;

        ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            mTextViewNameGenres = itemView.findViewById(R.id.txt_name_genres);
            mImageViewBannerGenres = itemView.findViewById(R.id.img_banner_genres);
            mItemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        void onBindView(Genres genres) {
            mTextViewNameGenres.setText(genres.getTitle());
            if (genres.getBanner() != null) {
                Glide.with(itemView.getContext())
                        .load(genres.getBanner())
                        .into(mImageViewBannerGenres);
            }
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClicked(getAdapterPosition());
        }
    }
}
