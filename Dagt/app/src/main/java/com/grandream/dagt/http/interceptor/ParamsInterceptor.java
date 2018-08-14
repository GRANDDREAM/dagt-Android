package com.grandream.dagt.http.interceptor;

import com.grandream.dagt.common.AppContext;
import com.grandream.dagt.utils.Utils;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Chen
 * @version V1.0
 * @Describe
 * @package com.sameway.cnotes.http.interceptor
 * @date 17/3/13 下午4:46
 */
public class ParamsInterceptor implements Interceptor {

    private final static AtomicLong sCIDGenerator = new AtomicLong(System.currentTimeMillis());

    @Override
    public Response intercept(Chain chain) throws IOException {
        Utils.getToken(AppContext.getContext());
        Request request = chain.request();
        Request.Builder builder = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("authorization", Utils.getToken(AppContext.getContext()))
                .addHeader("languages", "zh-CN")
                .addHeader("version", "v1")
                .addHeader("clientid", "android_testclient")
                .addHeader("nonce", "123a2")
                .addHeader("accept", "*/*");


//        builder.addHeader("Authorization", authorization);
        Request newRequest = builder.build();

        return chain.proceed(newRequest);
    }

}
