package com.framgia.music_21.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Genres implements Parcelable {
    private String mTitle;
    private String mBanner;

    public Genres() {
    }

    public Genres(String title, String banner) {
        mTitle = title;
        mBanner = banner;
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

    protected Genres(Parcel in) {
        mTitle = in.readString();
        mBanner = in.readString();
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
        parcel.writeString(mTitle);
        parcel.writeString(mBanner);
    }
}
