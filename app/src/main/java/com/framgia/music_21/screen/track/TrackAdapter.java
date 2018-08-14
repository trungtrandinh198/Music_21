package com.framgia.music_21.screen.track;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.framgia.music_21.R;
import com.framgia.music_21.data.model.Track;
import java.util.ArrayList;
import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {
    private List<Track> mTracks = new ArrayList<>();
    private ItemClickListener mItemClickListener;

    public TrackAdapter(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_track, parent, false);
        return new ViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBindView(mTracks.get(position));
    }

    @Override
    public int getItemCount() {
        return mTracks != null ? mTracks.size() : 0;
    }

    public void updateData(List<Track> tracks) {
        if (tracks == null) {
            return;
        }
        mTracks.clear();
        mTracks.addAll(tracks);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageViewAvatarTrack;
        private TextView mTextViewNameTrack, mTextViewSinger;
        ItemClickListener mItemClickListener;

        ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            mTextViewNameTrack = itemView.findViewById(R.id.text_name_track);
            mTextViewSinger = itemView.findViewById(R.id.text_name_singer_track);
            mImageViewAvatarTrack = itemView.findViewById(R.id.image_avatar_track);
            mItemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        void onBindView(Track track) {
            mTextViewNameTrack.setText(track.getTitle());
            mTextViewSinger.setText(track.getUsername());
            if (track.getAvatarUser() != null) {
                Glide.with(itemView.getContext())
                        .load(track.getAvatarUser())
                        .into(mImageViewAvatarTrack);
            }
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClicked(getAdapterPosition());
        }
    }
}
