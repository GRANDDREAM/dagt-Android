package com.grandream.dagt.http;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 配置
 *
 * @author Chen
 * @version V1.0
 * @date 17/1/13
 */
public class RxRetrofitConfig {
    private String mBaseUrl;
    private long mConnectTimeoutMilliseconds;
    private List<Interceptor> mInterceptors;
    private List<Converter.Factory> mConverterFactories;
    private List<CallAdapter.Factory> mAdapterFactories;

    private static final long DEFAULT_CONNECT_TIMEOUT = 15 * 1000;

    /**
     * 默认配置
     *
     * @param baseUrl
     * @return
     */
    public static RxRetrofitConfig createDefault(String baseUrl, boolean logger) {
        Builder builder = new Builder();
        //输出log
        if (logger) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC));
        }
        return builder.baseUrl(baseUrl)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    RxRetrofitConfig(Builder builder) {
        this.mBaseUrl = builder.mBaseUrl;
        this.mConnectTimeoutMilliseconds = builder.mConnectTimeoutMilliseconds;
        this.mInterceptors = new ArrayList<>(builder.interceptors);
        this.mConverterFactories = new ArrayList<>(builder.converterFactories);
        this.mAdapterFactories = new ArrayList<>(builder.adapterFactories);
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public long getConnectTimeoutMilliseconds() {
        return mConnectTimeoutMilliseconds;
    }


    public List<Interceptor> getInterceptors() {
        return mInterceptors;
    }


    public List<Converter.Factory> getConverterFactories() {
        return mConverterFactories;
    }

    public List<CallAdapter.Factory> getAdapterFactories() {
        return mAdapterFactories;
    }


    public static final class Builder {
        String mBaseUrl;
        long mConnectTimeoutMilliseconds;
        List<Interceptor> interceptors = new ArrayList<>();
        List<Converter.Factory> converterFactories = new ArrayList<>();
        List<CallAdapter.Factory> adapterFactories = new ArrayList<>();

        public Builder() {

        }


        public Builder baseUrl(String baseUrl) {
            this.mBaseUrl = baseUrl;
            return this;
        }


        public Builder connectTimeout(long timeoutMilliseconds) {
            this.mConnectTimeoutMilliseconds = timeoutMilliseconds;
            return this;
        }


        public Builder addInterceptor(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }


        public Builder addConverterFactory(Converter.Factory factory) {
            this.converterFactories.add(factory);
            return this;
        }


        public Builder addCallAdapterFactory(CallAdapter.Factory factory) {
            this.adapterFactories.add(factory);
            return this;
        }


        public RxRetrofitConfig build() {
            return new RxRetrofitConfig(this);
        }
    }
}
