package com.framgia.music_21.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class TrackList implements Parcelable {
    private List<Track> mTrackList;

    public TrackList() {

    }

    public TrackList(List<Track> trackList) {
        mTrackList = trackList;
    }

    protected TrackList(Parcel in) {
        mTrackList = in.createTypedArrayList(Track.CREATOR);
    }

    public static final Creator<TrackList> CREATOR = new Creator<TrackList>() {
        @Override
        public TrackList createFromParcel(Parcel in) {
            return new TrackList(in);
        }

        @Override
        public TrackList[] newArray(int size) {
            return new TrackList[size];
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
