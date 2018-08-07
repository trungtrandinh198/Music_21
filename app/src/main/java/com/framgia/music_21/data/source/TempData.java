package com.framgia.music_21.data.source;

public interface TempData {
    void onSuccess(String data);

    void onFail(Exception e);
}
