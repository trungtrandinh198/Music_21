package com.framgia.music_21.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class ListTrack implements Parcelable {
    private List<Track> mTrackList;

    public ListTrack() {

    }

    public ListTrack(List<Track> trackList) {
        mTrackList = trackList;
    }

    protected ListTrack(Parcel in) {
        mTrackList = in.createTypedArrayList(Track.CREATOR);
    }

    public static final Creator<ListTrack> CREATOR = new Creator<ListTrack>() {
        @Override
        public ListTrack createFromParcel(Parcel in) {
            return new ListTrack(in);
        }

        @Override
        public ListTrack[] newArray(int size) {
            return new ListTrack[size];
        }
    };

    public List<Track> getTrackList() {
        return mTrackList;
    }

    public void setTrackList(List<Track> trackList) {
        mTrackList = trackList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(mTrackList);
    }
}
