package com.grandream.dagt.http;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.grandream.dagt.http.exception.ServerResultException;
import com.grandream.dagt.http.interfaces.ICodeVerify;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.interfaces.IHttpResult;
import com.grandream.dagt.http.model.HttpResult;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;

/**
 * http 管理类
 *
 * @author Chen
 * @version V1.0
 * @package com.sameway.cnotes.http
 * @date 17/3/13 下午3:48
 */
public class HttpManager {
    private Retrofit retrofit;
    private ICodeVerify codeVerify;

    private HttpManager() {
        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                RxRetrogitLog.e("RxJavaPlugins Error = " + e);
            }
        });
    }

    public static HttpManager getInstance() {
        return HttpManager.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        static HttpManager instance = new HttpManager();
    }

    public void create(String baseUrl, ICodeVerify codeVerify, RetrofitParams params) {
        this.codeVerify = codeVerify;
        Converter.Factory converterFactory = params.getConverterFactory();
        CallAdapter.Factory callAdapterFactory = params.getCallAdapterFactor();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(converterFactory != null ? converterFactory : GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(callAdapterFactory != null ? callAdapterFactory : RxJavaCallAdapterFactory.create())
                .client(createClient(params))
                .build();
    }

    private OkHttpClient createClient(RetrofitParams params) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // 设置超时
        int connectTimeoutSeconds = params.getConnectTimeoutSeconds();
        if (connectTimeoutSeconds > 0) {
            builder.connectTimeout(connectTimeoutSeconds, TimeUnit.SECONDS);
        }

        int readTimeoutSeconds = params.getReadTimeoutSeconds();
        if (readTimeoutSeconds > 0) {
            builder.readTimeout(readTimeoutSeconds, TimeUnit.SECONDS);
        }

        int writeTimeoutSeconds = params.getWriteTimeoutSeconds();
        if (writeTimeoutSeconds > 0) {
            builder.writeTimeout(writeTimeoutSeconds, TimeUnit.SECONDS);
        }

        // Log信息拦截器
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        ArrayList<Interceptor> interceptors = params.getInterceptors();
        if (interceptors != null && interceptors.size() > 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        return builder.build();
    }

    public <ApiType> ApiType getApiService(Class<ApiType> type) {
        return retrofit.create(type);
    }

    public <T, Result extends IHttpResult<T>> HttpSubscriber<HttpResult<T>> toSubscribe(Observable<Result> observable, Context context, IHttpCallback<T> listener) {
        return toSubscribe(observable, (HttpSubscriber<HttpResult<T>>) new HttpSubscriber<>(context, listener));
    }

    public <T, Result extends IHttpResult<T>> HttpSubscriber<HttpResult<T>> toSubscribe(Observable<Result> observable, Context context, IHttpCallback<T> listener, boolean isShowToast) {
        return toSubscribe(observable, (HttpSubscriber<HttpResult<T>>) new HttpSubscriber<>(context, listener, isShowToast));
    }

    public <T, Result extends IHttpResult<T>> HttpSubscriber<HttpResult<T>> toSubscribe(final Observable<Result> observable, final HttpSubscriber<HttpResult<T>> httpSubscriber) {
        final Observable<T> observableNew = observable.map(new Func1<Result, T>() {
            @Override
            public T call(Result result) {
                if (result == null) {
                    throw new IllegalStateException("数据为空~");
                }
                int code = result.getResponse_code();
                if (code == 4013) {
                    httpSubscriber.onCancel();
                }
                if (!codeVerify.checkValid(result.getResponse_code())) {
                    throw new ServerResultException(code, codeVerify.formatCodeMessage(code, result.getResponse_text()));
                }
                return (T) result;
            }
        });
        observableNew.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<? super T>) httpSubscriber);
        return httpSubscriber;
    }


}
