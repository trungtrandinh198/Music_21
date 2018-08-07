package com.framgia.music_21.data.source.remote;

import com.framgia.music_21.data.model.ListTrack;
import com.framgia.music_21.data.model.Track;
import com.framgia.music_21.data.source.RequestData;
import com.framgia.music_21.data.source.TempData;
import com.framgia.music_21.data.source.TrackDataSources;
import com.framgia.music_21.utils.Constaint;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseDataFromJson implements TrackDataSources.RemoteDataSources {
    private static ParseDataFromJson sParseDataFromJson;

    public static synchronized ParseDataFromJson getInstance() {
        if (sParseDataFromJson == null) {
            sParseDataFromJson = new ParseDataFromJson();
        }
        return sParseDataFromJson;
    }

    private ListTrack paseListJson(String jsonString) {
        ListTrack listTrack = new ListTrack();
        List<Track> trackList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                trackList.add(getTrack(jsonObject.toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listTrack.setTrackList(trackList);
        return listTrack;
    }

    private Track getTrack(String stringJson) {
        Track track = new Track();
        try {
            JSONObject jsonObject = new JSONObject(stringJson);
            track.setUserId(jsonObject.getString(Constaint.ID));
            track.setAvatarUser(jsonObject.getString(Constaint.AVATAR_USER));
            track.setTitle(jsonObject.getString(Constaint.TITLE));
            track.setAvatarUser(
                    jsonObject.getJSONObject(Constaint.USER).getString(Constaint.AVATAR_USER));
            track.setStreamUlr(jsonObject.getString(Constaint.STREAM_ULR));
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
                Track track = null;
                track = getTrack(data);
                callback.onSuccess(track);
            }

            @Override
            public void onFail(Exception e) {

            }
        }).execute(Constaint.ULR + Constaint.TRACK + id + Constaint.CLIENT_ID);
    }

    @Override
    public void getListTrack(String genres, final RequestData<ListTrack> callBack) {
        new GetJsonFromURL(new TempData() {

            @Override
            public void onSuccess(String data) {
                ListTrack listTrack = null;
                listTrack = paseListJson(data);
                callBack.onSuccess(listTrack);
            }

            @Override
            public void onFail(Exception e) {
                callBack.onFail(e);
            }
        }).execute(Constaint.ULR_GENRES + genres);
    }
}
