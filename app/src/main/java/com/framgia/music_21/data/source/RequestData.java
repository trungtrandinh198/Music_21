package com.framgia.music_21.data.source;

public interface RequestData<T> {
    void onSuccess(T data);

    void onFail(Exception e);

}
