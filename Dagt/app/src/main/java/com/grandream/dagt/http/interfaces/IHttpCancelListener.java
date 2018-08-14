package com.grandream.dagt.http.interfaces;

/**
 * Http 请求cancel listener
 *
 * @author Chen
 * @version V1.0
 * @date 17/3/12
 */
public interface IHttpCancelListener {
    void onCancel();
    void cancelAll();
}
