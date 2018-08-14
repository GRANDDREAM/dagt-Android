package com.grandream.dagt.http.model;

import com.google.gson.annotations.Expose;
import com.grandream.dagt.http.interfaces.IHttpResult;

/**
 * @author Chen
 * @version V1.0
 * @Describe IHttpResult
 * @package com.sameway.cnotes.http.model
 * @date 17/3/13 下午3:59
 */
public class HttpResult<T> implements IHttpResult<T> {

    @Expose
    private int response_code;
    @Expose
    private String response_text;
    @Expose
    private T response_data;


    @Override
    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    @Override
    public String getResponse_text() {
        return response_text;
    }

    public void setResponse_text(String response_text) {
        this.response_text = response_text;
    }

    @Override
    public T getResponse_data() {
        return response_data;
    }

    public void setResponse_data(T response_data) {
        this.response_data = response_data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "response_code=" + response_code +
                ", response_text='" + response_text + '\'' +
                ", response_data=" + response_data +
                '}';
    }
}