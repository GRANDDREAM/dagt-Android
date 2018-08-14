package com.grandream.dagt.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.grandream.dagt.utils.cache.BusinessController;

import java.util.List;

/**
 * Created by chenjing on 2018/3/9.
 */

public class DeviceUtil {

    /**
     * 获取设备的型号
     */
    public static String getDeviceModel() {
        String model = Build.MODEL;

        if (model == null) {
            return "";
        } else {
            return model;
        }
    }

    /**
     * 获取设备 SDK版本名
     */
    @SuppressWarnings("deprecation")
    public static String getSDKVersion() {
        return Build.VERSION.SDK;
    }

    /**
     * 获取设备 SDK版本号
     */
    public static int getSDKVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 判断是否存在SD卡
     *
     * @return
     */
    public static boolean isSdCardExist() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取设备的IMEI号
     */
    public static String getIMEI(Context context) {
        TelephonyManager teleMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return teleMgr.getDeviceId();
    }

    /**
     * 获取设备的IMSI号
     */
    public static String getIMSI(Context context) {
        TelephonyManager teleMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return teleMgr.getSubscriberId();
    }


    /**
     * 获取动画设置
     */
    @SuppressWarnings("deprecation")
    public static boolean getAnimationSetting() {
        ContentResolver cv = BusinessController.getApplication().getContentResolver();
        String animation = android.provider.Settings.System.getString(cv, android.provider.Settings.System.TRANSITION_ANIMATION_SCALE);
        return StringUtils.toDouble(animation) > 0;
    }

    /**
     * 获取屏幕的宽高
     *
     * @param dm 设备显示对象描述
     * @return int数组, int[0] - width, int[1] - height
     */
    public static int[] getScreenSize(DisplayMetrics dm) {
        int[] result = new int[2];
        result[0] = dm.widthPixels;
        result[1] = dm.heightPixels;
        return result;
    }

    /**
     * Dip转换为实际屏幕的像素值
     *
     * @param dm  设备显示对象描述
     * @param dip dip值
     * @return 匹配当前屏幕的像素值
     */
    public static int getPixelFromDip(DisplayMetrics dm, float dip) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, dm) + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static float px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return pxValue / scale + 0.5f;
    }

    /**
     * 判断当前设备的数据服务是否有效
     *
     * @return true - 有效，false - 无效
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectMgr == null) {
            return false;
        }

        NetworkInfo nwInfo = connectMgr.getActiveNetworkInfo();
        return !(nwInfo == null || !nwInfo.isAvailable());
    }

    public static boolean isAppInstalled(Context context, String pkgName) {
        if (context == null) {
            return false;
        }

        try {
            context.getPackageManager().getPackageInfo(pkgName, PackageManager.PERMISSION_GRANTED);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * (检查终端是否支持指定的action)
     */
    public static boolean isIntentAvailable(Context context, String action) {
        return isIntentAvailable(context, new Intent(action));
    }

    /**
     * (检查终端是否支持指定的intent)
     */
    public static boolean isIntentAvailable(Context context, Intent intent) {
        if (context == null || intent == null) {
            return false;
        }

        PackageManager pkgManager = context.getPackageManager();
        if (pkgManager == null) {
            return false;
        }
        List<ResolveInfo> list = pkgManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     * 获取屏幕分辨率
     * @param context
     * @return
     */
    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();// 手机屏幕的宽度
        int height = windowManager.getDefaultDisplay().getHeight();// 手机屏幕的高度
        int result[] = { width, height };
        return result;
    }
}
