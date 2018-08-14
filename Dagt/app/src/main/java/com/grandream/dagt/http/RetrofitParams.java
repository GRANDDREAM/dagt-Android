package com.grandream.dagt.http;

import java.util.ArrayList;

import okhttp3.Interceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * 配置参数
 *
 * @author Chen
 * @version V1.0
 * @package com.sameway.cnotes.http
 * @date 17/3/13 下午3:47
 */
public class RetrofitParams {

    private int connectTimeoutSeconds;
    private int readTimeoutSeconds;
    private int writeTimeoutSeconds;
    private ArrayList<Interceptor> interceptors;
    private Converter.Factory converterFactory;
    private CallAdapter.Factory callAdapterFactor;

    public RetrofitParams() {
    }

    public int getConnectTimeoutSeconds() {
        return connectTimeoutSeconds;
    }

    public RetrofitParams setConnectTimeoutSeconds(int connectTimeoutSeconds) {
        this.connectTimeoutSeconds = connectTimeoutSeconds;
        return this;
    }

    public int getReadTimeoutSeconds() {
        return readTimeoutSeconds;
    }

    public RetrofitParams setReadTimeoutSeconds(int readTimeoutSeconds) {
        this.readTimeoutSeconds = readTimeoutSeconds;
        return this;
    }

    public int getWriteTimeoutSeconds() {
        return writeTimeoutSeconds;
    }

    public RetrofitParams setWriteTimeoutSeconds(int writeTimeoutSeconds) {
        this.writeTimeoutSeconds = writeTimeoutSeconds;
        return this;
    }

    public ArrayList<Interceptor> getInterceptors() {
        return interceptors;
    }

    public RetrofitParams setInterceptors(ArrayList<Interceptor> interceptors) {
        this.interceptors = interceptors;
        return this;
    }

    public Converter.Factory getConverterFactory() {
        return converterFactory;
    }

    public RetrofitParams setConverterFactory(Converter.Factory converterFactory) {
        this.converterFactory = converterFactory;
        return this;
    }

    public CallAdapter.Factory getCallAdapterFactor() {
        return callAdapterFactor;
    }

    public RetrofitParams setCallAdapterFactor(CallAdapter.Factory callAdapterFactor) {
        this.callAdapterFactor = callAdapterFactor;
        return this;
    }
}
