package com.grandream.dagt.activity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.grandream.dagt.BuildConfig;
import com.grandream.dagt.R;
import com.grandream.dagt.base.BaseFragmentActivity;
import com.grandream.dagt.common.AppContext;
import com.grandream.dagt.common.ViewConstant;
import com.grandream.dagt.service.TokenCountdownService;
import com.grandream.dagt.utils.LogUtils;
import com.grandream.dagt.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by chenjing on 18/2/6.
 */

public class SplashActivity extends BaseFragmentActivity {
    private boolean isStartHome = false;
    // 应用外跳入
    private boolean isFromOutApp = false;
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private static final String VERSION_CODE = "version_code";
    private static final int MSG_DEFAULT_DISPLAY = 0x10001;
    private ImageView mBootupImageView;
    public static final String CLOCK_ACTION = "com.grandream.dagt";
    public static int TIME;//倒计时2个小时


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_DEFAULT_DISPLAY:
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getToken();
                        }
                    }, 3000);
                    break;
            }
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.boot_up);
        super.onCreate(savedInstanceState);
        mBootupImageView = (ImageView) findViewById(R.id.bootup_image);
        init();
        if (TextUtils.isEmpty(Utils.GetToken(this))) {
            mHandler.sendEmptyMessage(MSG_DEFAULT_DISPLAY);
        } else {
            nextStep();
        }
    }


    private void init() {
        mContext = AppContext.getInstance().getApplicationContext();
        // 避免通过其他方式启动程序后，再通过程序列表中的launcher启动，重新走启动流程
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action != null
                    && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras() != null) {
                boolean exit = intent.getExtras().getBoolean(
                        ViewConstant.EXIT_APP, false);
                if (exit) {
                    quit();
                }
            }

        } else {
            isFromOutApp = false;
        }
    }


    private void nextStep() {
        Intent intent;
        intent = new Intent(SplashActivity.this,
                MainActivity.class);
        startActivity(intent);
    }

    // 退出应用
    public void quit() {
        try {
            finish();
            ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> aps = am.getRunningAppProcesses();
            final String pkgName = getPackageName();
            for (ActivityManager.RunningAppProcessInfo rap : aps) {
                if (rap.processName.startsWith(pkgName)
                        && !rap.processName.endsWith(":push")) {
                    android.os.Process.killProcess(rap.pid);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null && intent.getExtras() != null) {
            boolean exit = intent.getExtras().getBoolean(ViewConstant.EXIT_APP,
                    false);
            isStartHome = intent.getExtras().getBoolean(
                    ViewConstant.START_HOME, false);
            if (exit) {
                quit();
            } else if (isStartHome) {
                Intent mIntent = null;
                mIntent = new Intent(SplashActivity.this,
                        MainActivity.class);
                startActivity(mIntent);

            }
        }
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void getToken() {
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
                        LogUtils.d("成功" + arg0.toString());
                        try {
                            JSONObject jsonObject = arg0.getJSONObject("response_data");
                            LogUtils.d("INFO", jsonObject.toString());
                            Utils.SaveData(getApplicationContext(), jsonObject.getString("access_token"), jsonObject.getInt("expires_in"));
                            AppContext.getInstance().initHttp();
                            TIME = jsonObject.getInt("expires_in");
                            startService(new Intent(SplashActivity.this, TokenCountdownService.class));//启动计时服务
                            nextStep();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
