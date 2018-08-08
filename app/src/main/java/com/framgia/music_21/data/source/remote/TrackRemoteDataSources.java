package com.framgia.music_21.data.source.remote;

import com.framgia.music_21.data.model.Track;
import com.framgia.music_21.data.model.TrackList;
import com.framgia.music_21.data.source.RequestData;
import com.framgia.music_21.data.source.TempData;
import com.framgia.music_21.data.source.TrackDataSources;
import com.framgia.music_21.utils.Constaint;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrackRemoteDataSources implements TrackDataSources.RemoteDataSources {
    private static TrackRemoteDataSources sTrackRemoteDataSources;

    public static synchronized TrackRemoteDataSources getInstance() {
        if (sTrackRemoteDataSources == null) {
            sTrackRemoteDataSources = new TrackRemoteDataSources();
        }
        return sTrackRemoteDataSources;
    }

    private TrackList parseListJson(String jsonString) {
        TrackList listTrack = new TrackList();
        List<Track> trackList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                trackList.add(getTrackJson(jsonObject.toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listTrack.setTrackList(trackList);
        return listTrack;
    }

    private Track getTrackJson(String stringJson) {
        Track track = new Track();
        Track.Builder builder = new Track.Builder();

        try {
            JSONObject jsonObject = new JSONObject(stringJson);
            track = builder.setId(String.valueOf(jsonObject.getInt(Constaint.ID)))
                    .setTitle(jsonObject.getString(Constaint.TITLE))
                    .setTitle(jsonObject.getString(Constaint.TITLE))
                    .setPlayTrackCount(jsonObject.getJSONObject(Constaint.USER)
                            .getString(Constaint.AVATAR_USER))
                    .setAvatarUser(jsonObject.getJSONObject(Constaint.USER)
                            .getString(Constaint.AVATAR_USER))
                    .setUserId(jsonObject.getJSONObject(Constaint.USER).getString(Constaint.ID))
                    .setUsername(
                            jsonObject.getJSONObject(Constaint.USER).getString(Constaint.USER_NAME))
                    .setDownLoadUrl(jsonObject.getString(Constaint.DOWNLOAD_URL))
                    .setStreamUlr(jsonObject.getString(Constaint.STREAM_ULR))
                    .buid();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return track;
    }

    @Override
    public void getTrack(String id, final RequestData<Track> callback) {
        new GetJsonFromURL(new TempData() {
            @Override
            public void onSuccess(String data) {
                Track track = getTrackJson(data);
                callback.onSuccess(track);
            }

            @Override
            public void onFail(Exception e) {

            }
        }).execute(Constaint.ULR + Constaint.TRACK + id + Constaint.CLIENT_ID);
    }

    @Override
    public void getListTrack(String genres, final RequestData<TrackList> callBack) {
        new GetJsonFromURL(new TempData() {

            @Override
            public void onSuccess(String data) {
                TrackList trackList = parseListJson(data);
                callBack.onSuccess(trackList);
            }

            @Override
            public void onFail(Exception e) {
                callBack.onFail(e);
            }
        }).execute(Constaint.ULR_GENRES + genres);
    }
}
