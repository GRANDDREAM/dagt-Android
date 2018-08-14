package com.grandream.dagt.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Base64;
import android.view.WindowManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.grandream.dagt.activity.MainActivity;
import com.grandream.dagt.base.BaseFragmentActivity;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.common.AppContext;
import com.grandream.dagt.common.ViewConstant;
import com.grandream.dagt.fragment.user.LogingFragment;
import com.grandream.dagt.fragment.user.SecondLoginFragment;
import com.grandream.dagt.ui.dialog.DialogManager;
import com.grandream.dagt.ui.dialog.DialogType;
import com.grandream.dagt.ui.dialog.exchange.DialogExchangeModel;
import com.grandream.dagt.ui.trendchart.DateValue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by chenjing on 2018/3/20.
 */

public class Utils {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static List<DateValue> getTrend() {
        List<DateValue> dvTrend = new ArrayList<>();
        dvTrend.add(new DateValue(1776.82f, "1"));
        dvTrend.add(new DateValue(4757.98f, "2"));
        dvTrend.add(new DateValue(2692.82f, "3"));
        dvTrend.add(new DateValue(6719.98f, "4"));
        dvTrend.add(new DateValue(5728.6f, "5"));
        dvTrend.add(new DateValue(6774.12f, "6"));
        dvTrend.add(new DateValue(7725.48f, "7"));
        dvTrend.add(new DateValue(8731.27f, "8"));
        dvTrend.add(new DateValue(10751.02f, "9"));
        dvTrend.add(new DateValue(11751.02f, "10"));
        dvTrend.add(new DateValue(12751.02f, "11"));
        dvTrend.add(new DateValue(13751.02f, "12"));
        dvTrend.add(new DateValue(14751.02f, "13"));
        dvTrend.add(new DateValue(13f, "14"));
        return dvTrend;
    }


    public static String md5(String string) {
        if (string != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(string.getBytes());
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 十六进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转化成16进制形式的字符串
     *
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


    /**
     * 获取body
     *
     * @param body
     * @return
     */
    public static RequestBody getRequestBody(Object body) {
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(body));
        return requestBody;
    }


    public static void SaveData(Context context, String token, int expires_in) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ViewConstant.AUTHORIZATION, Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("token", token);
        editor.putInt("expires_in", expires_in);
        editor.commit();//提交修改
    }

    /**
     * 获取token
     *
     * @param context
     * @return
     */
    public static String GetToken(Context context) {
        SharedPreferences share = context.getSharedPreferences(ViewConstant.AUTHORIZATION, Activity.MODE_PRIVATE);
        return share.getString("token", "");
    }

    public static void saveUserStatus(Context context, int id, String user_name, String country, String avatar) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ViewConstant.USER_INFO, Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("user_name", user_name);
        editor.putString("country", country);
        editor.putInt("id", id);
        editor.putBoolean("ISFIRST", true);
        editor.putString("avatar", avatar);
        editor.commit();//提交修改
    }


    public static boolean ClearUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ViewConstant.USER_INFO, Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("id");
        return editor.commit();
    }

    public static void toLoging(Context context) {
        if (Utils.getIsFirst(context)) {
            Bundle bundle = new Bundle();
            bundle.putString("user_name", Utils.getUser_name(context));
            GenericFragmentActivity.start((Activity) context, SecondLoginFragment.class, bundle);
        } else {
            GenericFragmentActivity.start((Activity) context, LogingFragment.class, new Bundle());
        }
    }

    public static void toLoging(Context context, Activity activity) {
        if (Utils.getIsFirst(context)) {
            Bundle bundle = new Bundle();
            bundle.putString("user_name", Utils.getUser_name(context));
            GenericFragmentActivity.start(activity, SecondLoginFragment.class, bundle);
        } else {
            GenericFragmentActivity.start(activity, LogingFragment.class, new Bundle());
        }
    }

    public static void getNewToken(final Context context) {
        ClearUserInfo(context);
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
                            Utils.SaveData(context, jsonObject.getString("access_token"), jsonObject.getInt("expires_in"));
                            AppContext.getInstance().initHttp();
                            toHome(context);
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

    public static void toHome(Context context) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.changeFragment(0);
        mainActivity.select(0);
    }


    /**
     * 手势密码状态
     *
     * @param context
     * @param isOPen
     */
    public static void saveGesutreStatus(Context context, Boolean isOPen) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ViewConstant.GESTURE_STATUS, Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putBoolean("ISOPEN", isOPen);
        editor.commit();//提交修改
    }


    /**
     * 手势密码状态获取
     */
    public static boolean getIsOpen(Context context) {
        SharedPreferences share = context.getSharedPreferences(ViewConstant.GESTURE_STATUS, Activity.MODE_PRIVATE);
        boolean isopen = share.getBoolean("ISOPEN", false);
        return isopen;
    }

    /**
     * 獲取用戶是否是第一次登陆
     */
    public static boolean getIsFirst(Context context) {
        SharedPreferences share = context.getSharedPreferences(ViewConstant.USER_INFO, Activity.MODE_PRIVATE);
        boolean ISFIRST = share.getBoolean("ISFIRST", false);
        return ISFIRST;
    }

    /**
     * 獲取用戶id
     */
    public static String getUser_id(Context context) {
        SharedPreferences share = context.getSharedPreferences(ViewConstant.USER_INFO, Activity.MODE_PRIVATE);
        int id = share.getInt("id", 0);
        if (id == 0)
            return null;
        else
            return id + "";

    }

    /**
     * 获取图片
     */
    public static String getAvatar(Context context) {
        SharedPreferences share = context.getSharedPreferences(ViewConstant.USER_INFO, Activity.MODE_PRIVATE);
        String avatar = share.getString("avatar", "");
        return avatar;
    }

    public static String getUser_name(Context context) {
        SharedPreferences share = context.getSharedPreferences(ViewConstant.USER_INFO, Activity.MODE_PRIVATE);
        return share.getString("user_name", "");
    }


    public static String getToken(Context context) {
        SharedPreferences share = context.getSharedPreferences(ViewConstant.AUTHORIZATION, Activity.MODE_PRIVATE);
        String token = share.getString("token", "");
        return "bearer " + token;
    }

    public static int getExpires_in(Context context) {
        SharedPreferences share = context.getSharedPreferences(ViewConstant.AUTHORIZATION, Activity.MODE_PRIVATE);
        return share.getInt("expires_in", 3600);
    }


    public static boolean isLogin(Context context) {
        SharedPreferences share = context.getSharedPreferences(ViewConstant.USER_INFO, Activity.MODE_PRIVATE);
        int id = share.getInt("id", 0);
        if (id == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        byte[] bitmapArray = Base64.decode(string.split(",")[1], Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        return bitmap;
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int toInteger(Object obj) {
        if (obj != null && !"".equals(obj)) {
            return Integer.parseInt(obj.toString());
        }
        return 0;
    }

    public static double toDouble(Object obj) {
        if (obj != null && !"".equals(obj)) {
            return Double.parseDouble(obj.toString());
        }
        return 0.0;
    }


    public static void ShowDialog(String msg, String error, Fragment manager, Context context) {
        DialogExchangeModel.DialogExchangeModelBuilder builder = new DialogExchangeModel.DialogExchangeModelBuilder(
                DialogType.SINGLE, error);
        DialogExchangeModel DialogExchangeModel = builder
                .setBackable(true).setSpaceable(true)
                .setDialogContext(msg).creat();
        DialogManager.showDialogFragment(manager.getFragmentManager(),
                DialogExchangeModel, manager,
                (BaseFragmentActivity) context);
    }

    public static void ShowClickDialog(String msg, String error, Fragment manager, Context context, String text) {
        DialogExchangeModel.DialogExchangeModelBuilder builder = new DialogExchangeModel.DialogExchangeModelBuilder(
                DialogType.SINGLE, error);
        DialogExchangeModel DialogExchangeModel = builder
                .setBackable(false).setSpaceable(false)
                .setDialogContext(msg).setSingleText(text).creat();
        DialogManager.showDialogFragment(manager.getFragmentManager(),
                DialogExchangeModel, manager,
                (BaseFragmentActivity) context);
    }


    public static void setFullSpan(StaggeredGridLayoutManager.LayoutParams layoutParams) {
        if (layoutParams != null && !layoutParams.isFullSpan()) {
            layoutParams.setFullSpan(true);
        }
    }

    public static Point getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point(wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getHeight());
        return point;
    }

    public static Bitmap create2DCode(String str) throws WriterException {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.MAX_SIZE, "UTF-8");
        BitMatrix matrix = (new MultiFormatWriter()).encode(str, BarcodeFormat.QR_CODE, 500, 520, hints);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];

        for (int bitmap = 0; bitmap < height; ++bitmap) {
            for (int x = 0; x < width; ++x) {
                if (matrix.get(x, bitmap)) {
                    pixels[bitmap * width + x] = -16777216;
                }
            }
        }

        Bitmap var8 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        var8.setPixels(pixels, 0, width, 0, 0, width, height);
        return var8;
    }

    // 将部分文字变成*号
    public static String replaceToXing(String content, int start, int end) {
        String newContent = "";
        if (content != null && !"".equals(content)) {
            String startStr = "";
            String endStr = "";
            String replaceStr = "";
            String str1 = "";

            int length = content.length();

            if (start < length && end < length && start <= end) {
                startStr = content.substring(0, start - 1);
                endStr = content.substring(end);
                replaceStr = content.substring(start - 1, end);

                for (int i = 0; i < replaceStr.length(); i++) {
                    str1 = "*" + str1;
                }
            }
            newContent = startStr + str1 + endStr;
        }
        return newContent;
    }


    /**
     * 把json 转换为ArrayList 形式
     *
     * @return
     */
    public static List<HashMap<String, Object>> getList(String jsonString) {
        List<HashMap<String, Object>> list = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;
            list = new ArrayList<HashMap<String, Object>>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                list.add(getMap(jsonObject.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将json 数组转换为Map 对象
     *
     * @param jsonString
     * @return
     */
    public static HashMap<String, Object> getMap(String jsonString) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonString);
            @SuppressWarnings("unchecked")
            Iterator<String> keyIter = jsonObject.keys();
            String key = null;
            Object value = null;
            HashMap<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = (String) keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断应用是否在前台
     *
     * @param context
     * @return
     */
    public static boolean isForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }
        //见以下各种判断
        return false;
    }


}
