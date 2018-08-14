package com.grandream.dagt.http;


import com.grandream.dagt.utils.LogUtils;

/**
 * @author Chen
 * @version V1.0
 * @package com.meiqiongproperty.yiguanjia.http
 */
public class RxRetrogitLog {
    private static final String TAG = "rxretrofit";
    public static boolean DEBUG=true;

    public static void d(String msg) {
        if (DEBUG) {
            LogUtils.d(TAG + buildMessage(msg));
        }
    }

    public static void d(Object obj) {
        if (DEBUG) {
            LogUtils.d(TAG + buildMessage(String.valueOf(obj)));
        }
    }

    public static void v(String msg) {
        if (DEBUG) {
            LogUtils.v(TAG, buildMessage(msg));
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            LogUtils.i(TAG, buildMessage(msg));
        }
    }

    public static void w(String msg) {
        if (DEBUG) {
            LogUtils.w(TAG, buildMessage(msg));
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            LogUtils.e(TAG , buildMessage(msg));
        }
    }

    private static String buildMessage(String msg) {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        return "### " + caller.toString() + msg;
    }
}
