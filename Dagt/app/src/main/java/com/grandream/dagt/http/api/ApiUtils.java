package com.grandream.dagt.http.api;

import android.content.Context;


import com.grandream.dagt.http.HttpManager;
import com.grandream.dagt.http.HttpSubscriber;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;

import rx.Observable;

/**
 * @author Chen
 * @version V1.0
 * @Describe api工具类
 * @package com.sameway.cnotes.http.api
 * @date 17/3/13 下午3:55
 */
public class ApiUtils {

    public static <T> HttpSubscriber<T> toSubscribe(Context context, Observable<HttpResult<T>> observable, final IHttpCallback<T> listener) {
        return toSubscribe(context,observable, listener, true);
    }

    public static <T> HttpSubscriber<T> toSubscribe(Context context, Observable<HttpResult<T>> observable, final IHttpCallback<T> listener, boolean showToast) {
        return (HttpSubscriber<T>) HttpManager.getInstance().toSubscribe(observable, context, listener, showToast);
    }
}
