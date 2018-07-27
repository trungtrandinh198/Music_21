package com.framgia.music_21.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String mId;
    private String mUserName;
    private String mAvatarUrl;
    private String mFullName;

    public User() {
    }

    public User(String id, String userName, String avatarUrl, String fullName) {
        mId = id;
        mUserName = userName;
        mAvatarUrl = avatarUrl;
        mFullName = fullName;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    protected User(Parcel in) {
        mId = in.readString();
        mUserName = in.readString();
        mAvatarUrl = in.readString();
        mFullName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mUserName);
        parcel.writeString(mAvatarUrl);
        parcel.writeString(mFullName);
    }
}
