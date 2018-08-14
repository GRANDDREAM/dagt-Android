package com.grandream.dagt.http.interfaces;

import android.app.Activity;
import android.content.Context;

import com.grandream.dagt.http.model.HttpResult;

import rx.Subscriber;

/**
 * @author Chen
 * @version V1.0
 * @date 17/3/12
 */
public interface IHttpCallback<T> {
    void onNext(HttpResult<T> t);

    void onError(int code, String message);

//    void onSuccess(T t);
}
