package com.grandream.dagt.utils;

/**
 * Created by chenjing on 2018/3/19.
 */

public class LogUtils {
    public static final boolean FLAG = true;

    public static void v(String tag, String msg) {
        if (FLAG) {
            android.util.Log.v(tag, msg == null? "" : msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (FLAG) {
            android.util.Log.v(tag, msg == null? "" : msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (FLAG) {
            android.util.Log.d(tag, msg == null? "" : msg);
        }
    }
    /**
     * 方便输出调试日志，避免在外面拼字符串
     * @param tag
     * @param msgs
     * @author zhangpeizhong 2015-4-30 下午1:45:58
     */
    public static void d(String tag, String ...msgs) {
        if (FLAG) {
            StringBuilder sb = new StringBuilder();
            for(String ss : msgs)
                sb.append(ss);
            android.util.Log.d(tag, sb.toString());
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (FLAG) {
            android.util.Log.d(tag, msg == null? "" : msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (FLAG) {
            android.util.Log.i(tag, msg == null? "" : msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (FLAG) {
            android.util.Log.i(tag, msg == null? "" : msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (FLAG) {
            android.util.Log.w(tag, msg == null? "" : msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (FLAG) {
            android.util.Log.w(tag, msg == null? "" : msg, tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (FLAG) {
            android.util.Log.w(tag, tr);
        }
    }

    /******
     * 禁止使用e打印日志
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (FLAG) {
            android.util.Log.d(tag, msg == null? "" : msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (FLAG) {
            android.util.Log.e(tag, msg == null? "" : msg, tr);
        }
    }
}
