package com.framgia.music_21.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Track implements Parcelable {
    private String mId;
    private String mTitle;
    private String mUserId;
    private String mUir;
    private String mUsername;
    private String mAvatarUser;
    private String mPlayTrackCount;
    private String mStreamUlr;
    private String mDownLoadUrl;

    public Track() {
    }

    public Track(String id, String title, String userId, String uir, String username,
            String avatarUser, String playTrackCount, String streamUlr, String downLoadUrl) {
        mId = id;
        mTitle = title;
        mUserId = userId;
        mUir = uir;
        mUsername = username;
        mAvatarUser = avatarUser;
        mPlayTrackCount = playTrackCount;
        mStreamUlr = streamUlr;
        mDownLoadUrl = downLoadUrl;
    }

    private Track(Builder builder) {
        mId = builder.mId;
        mTitle = builder.mTitle;
        mUserId = builder.mUserId;
        mUir = builder.mUir;
        mUsername = builder.mUsername;
        mAvatarUser = builder.mAvatarUser;
        mPlayTrackCount = builder.mPlayTrackCount;
        mStreamUlr = builder.mStreamUlr;
        mDownLoadUrl = builder.mDownLoadUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getUir() {
        return mUir;
    }

    public void setUir(String uir) {
        mUir = uir;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getAvatarUser() {
        return mAvatarUser;
    }

    public void setAvatarUser(String avatarUser) {
        mAvatarUser = avatarUser;
    }

    public String getPlayTrackCount() {
        return mPlayTrackCount;
    }

    public void setPlayTrackCount(String playTrackCount) {
        mPlayTrackCount = playTrackCount;
    }

    public String getStreamUlr() {
        return mStreamUlr;
    }

    public void setStreamUlr(String streamUlr) {
        mStreamUlr = streamUlr;
    }

    public String getDownLoadUrl() {
        return mDownLoadUrl;
    }

    public void setDownLoadUrl(String downLoadUrl) {
        mDownLoadUrl = downLoadUrl;
    }

    public static Creator<Track> getCREATOR() {
        return CREATOR;
    }

    protected Track(Parcel in) {
        mId = in.readString();
        mTitle = in.readString();
        mUserId = in.readString();
        mUir = in.readString();
        mUsername = in.readString();
        mAvatarUser = in.readString();
        mPlayTrackCount = in.readString();
        mStreamUlr = in.readString();
        mDownLoadUrl = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
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
        parcel.writeString(mUserId);
        parcel.writeString(mUir);
        parcel.writeString(mUsername);
        parcel.writeString(mAvatarUser);
        parcel.writeString(mPlayTrackCount);
        parcel.writeString(mStreamUlr);
        parcel.writeString(mDownLoadUrl);
    }

    public static class Builder {
        private String mId;
        private String mTitle;
        private String mUserId;
        private String mUir;
        private String mUsername;
        private String mAvatarUser;
        private String mPlayTrackCount;
        private String mStreamUlr;
        private String mDownLoadUrl;

        public Builder() {

        }

        public Track buid() {
            return new Track(this);
        }

        public Builder setId(String id) {
            mId = id;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setUserId(String userId) {
            mUserId = userId;
            return this;
        }

        public Builder setUir(String uir) {
            mUir = uir;
            return this;
        }

        public Builder setUsername(String username) {
            mUsername = username;
            return this;
        }

        public Builder setAvatarUser(String avatarUser) {
            mAvatarUser = avatarUser;
            return this;
        }

        public Builder setPlayTrackCount(String playTrackCount) {
            mPlayTrackCount = playTrackCount;
            return this;
        }

        public Builder setStreamUlr(String streamUlr) {
            mStreamUlr = streamUlr;
            return this;
        }

        public Builder setDownLoadUrl(String downLoadUrl) {
            mDownLoadUrl = downLoadUrl;
            return this;
        }
    }
}
