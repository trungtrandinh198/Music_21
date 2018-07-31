package com.framgia.music_21.screen.download;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.framgia.music_21.R;

public class DownLoadAdapter extends RecyclerView.Adapter<DownLoadAdapter.ViewHolder>{
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewNameSongDowLoad;
        private TextView mTextViewLoadingDownLoad;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewNameSongDowLoad = itemView.findViewById(R.id.txt_name_song_download);
            mTextViewLoadingDownLoad = itemView.findViewById(R.id.txt_loading_download);
        }
    }
}
