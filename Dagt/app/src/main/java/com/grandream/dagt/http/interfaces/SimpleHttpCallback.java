package com.grandream.dagt.http.interfaces;

/**
 * @author Chen
 * @version V1.0
 * @date 17/3/12
 */
public abstract class SimpleHttpCallback<T> implements IHttpCallback<T> {
    @Override
    public void onError(int code, String message) {

    }
}