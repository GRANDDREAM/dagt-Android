package com.grandream.dagt.http.interfaces;

/**
 * @author Chen
 * @version V1.0
 * @date 17/3/12
 */
public interface IHttpResult<T> {
    int getResponse_code();

    String getResponse_text();

    T getResponse_data();

}
