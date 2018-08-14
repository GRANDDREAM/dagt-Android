package com.grandream.dagt.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.grandream.dagt.BuildConfig;
import com.grandream.dagt.common.AppContext;
import com.grandream.dagt.common.ViewConstant;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.LogUtils;
import com.grandream.dagt.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chenjing on 2018/6/28.
 */

public class TokenCountdownService extends Service {
    public static final String CLOCK_SERVICE_ACTION = "clock_service_actoin";
    private static int TIME;
    private boolean controllOpt = true;

    public TokenCountdownService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        countTime();//执行计时功能
        return Service.START_STICKY;
    }

    //实现计时功能
    private void countTime() {
        TIME = Utils.getExpires_in(getBaseContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (controllOpt) {
                    try {
                        Thread.sleep(1000);
                        if (TIME <= 0) {
                            LogUtils.d("INFO", TIME + "");
                            getToken();
                            break;
                        }
                        TIME -= 1000;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //广播接受者，接受来自ClockActivity的广播以便暂停、继续、停止广播
    private BroadcastReceiver serviceController = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String method = intent.getStringExtra("method");
            switch (method) {
                case "gettoken":
                    getToken();
                    break;
            }
        }
    };


    public void getToken() {
        Utils.ClearUserInfo(getBaseContext());
        final JSONObject jo = new JSONObject();
        try {
            jo.put("grant_type", "client_credentials");
            jo.put("client_id", "android_testclient");
            jo.put("client_secret", "android_testpass");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest order = new JsonObjectRequest(com.android.volley.Request.Method.POST,
                ViewConstant.AUTH_API + "get-token", jo,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject arg0) {
                        try {
                            JSONObject jsonObject = arg0.getJSONObject("response_data");
                            Utils.SaveData(getApplicationContext(), jsonObject.getString("access_token"), jsonObject.getInt("expires_in"));
                            AppContext.getInstance().initHttp();
                            countTime();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                LogUtils.d("失败" + arg0.toString());
            }
        });
        order.setRetryPolicy(new DefaultRetryPolicy(20 * 1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppContext.getInstance().queue.add(order);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.unregisterReceiver(serviceController);

    }
}
