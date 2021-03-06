package com.framgia.music_21.utils;

import com.framgia.music_21.BuildConfig;

public class Constaint {
    public static final String ULR = "http://api.soundcloud.com/";
    public static final String TRACK = "tracks/";
    public static final String PLAYLIST = "playlists/";
    public static final String GENRES = "&genres=";
    public static final String CLIENT_ID = "?client_id=" + BuildConfig.API_KEY;
    public static final String ULR_GENRES = ULR + TRACK + CLIENT_ID + GENRES;
    //Contstraint connecttion http
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 10000;
    public static final int CONNECT_TIMEOUT = 15000;
    //constraint Track
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String UIR = "uir";
    public static final String USER_NAME = "username";
    public static final String AVATAR_USER = "avatar_url";
    public static final String PLAY_TRACK_COUNT = "reposts_count";
    public static final String STREAM_ULR = "stream_url";
    public static final String DOWNLOAD_URL = "download_url";
    public static final String USER = "user";
    //genres
    public static final String MUSIC = "music";
    public static final String ROCK = "rock";
    public static final String COUNTRY = "contry";
    public static final String HIPHOP = "hip hop";
    public static final String HOT = "hot";
    //image
    public static final String IMAGE_MUSIC =
            "https://blog.britishmuseum.org/wp-content/uploads/2018/04/xEATW-ID.jpg.pagespeed.ic"
                    + ".zm87HyfrqF.webp";
    public static final String IMAGE_HOT =
            "https://nbrdesignstudio.com/wp-content/uploads/2015/08/hotmusic.png";
    public static final String IMAGE_COUTRY =
            "https://i.ytimg.com/vi/BmcqOEA4rNg/maxresdefault.jpg";
    public static final String IMAGE_HIPHOP =
            "http://www.hiphopinternational.com/wp-content/uploads/2017/11/S-RANK-USA.jpg";
    public static final String ARGUMENT_POSITION = "ARGUMENT_POSITION";
    public static final String EXTRAS_SONG = "EXTRAS_SONG";
    public static final String ARGUMENT_TRACK = "ARGUMENT_TRACK";
}
