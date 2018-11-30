package com.app.microyang.callBack;

public interface OnNetListener<T> {

    void onSuccess(T t);

    void onFailed(Exception e);

}
