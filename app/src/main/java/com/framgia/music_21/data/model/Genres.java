package com.framgia.music_21.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class Genres implements Parcelable {
    private String mId;
    private String mTitle;
    private String mBanner;
    private ArrayList<String> mArrayListIdSong;

    public Genres() {
    }

    public Genres(String id, String title, String banner, ArrayList<String> arrayListIdSong) {
        mId = id;
        mTitle = title;
        mBanner = banner;
        mArrayListIdSong = arrayListIdSong;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getBanner() {
        return mBanner;
    }

    public void setBanner(String banner) {
        mBanner = banner;
    }

    public static Creator<Genres> getCREATOR() {
        return CREATOR;
    }

    public ArrayList<String> getArrayListIdSong() {
        return mArrayListIdSong;
    }

    public void setArrayListIdSong(ArrayList<String> arrayListIdSong) {
        mArrayListIdSong = arrayListIdSong;
    }

    protected Genres(Parcel in) {
        mId = in.readString();
        mTitle = in.readString();
        mBanner = in.readString();
        mArrayListIdSong = in.createStringArrayList();
    }

    public static final Creator<Genres> CREATOR = new Creator<Genres>() {
        @Override
        public Genres createFromParcel(Parcel in) {
            return new Genres(in);
        }

        @Override
        public Genres[] newArray(int size) {
            return new Genres[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mBanner);
        parcel.writeStringList(mArrayListIdSong);
    }
}
