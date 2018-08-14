package com.grandream.dagt.utils;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Build;
import android.provider.ContactsContract;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.grandream.dagt.R;
import com.grandream.dagt.common.AppContext;

/**
 * Created by chenjing on 2018/4/12.
 */

public class CommonUtil {
    private  static int mToastCount = 5;

    /**
     *
     * 功能描述:显示Toast形式弹框(单行显示时间为Toast.LENGTH_SHORT， 多行显示时间为Toast.LENGTH_LONG)
     * <pre>
     * </pre>
     *
     * @param message
     */
    public static void showToast(String message) {

        if (StringUtils.emptyOrNull(message)) {
            return;
        }

        Toast toast = new Toast(AppContext.getInstance());
        View view = LayoutInflater.from(AppContext.getInstance()).inflate(R.layout.common_toast, null);
        TextView tv = (TextView)view.findViewById(R.id.toast_message);
        tv.setText(message);
        toast.setView(view);
        toast.setDuration(computeMsgLength(tv, message));
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }



    private static int computeMsgLength(TextView tv, String msg) {

        Paint p = tv.getPaint();

        if (null == p) {
            return Toast.LENGTH_SHORT;
        }

        int maxWidth = DeviceUtil.getScreenSize(AppContext.getInstance().getResources().getDisplayMetrics())[0]
                - DeviceUtil.getPixelFromDip(AppContext.getInstance().getResources().getDisplayMetrics(), 50) - 24;

        float msgLength = p.measureText(msg);

        return msgLength > maxWidth ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;

    }

    /**
     *
     * 功能描述:为首页的广告条添加动画.
     * <pre>
     * </pre>
     *
     * @param rootView
     */
    @TargetApi(11)
    public static void addAnimForAds(View rootView) {

        ViewGroup vg = null;
        if (DeviceUtil.getSDKVersionInt() >= 11 && rootView instanceof ViewGroup) {
            vg = (ViewGroup)rootView;
            LayoutTransition layoutTransition = new LayoutTransition();
            layoutTransition.setDuration(300);
            vg.setLayoutTransition(layoutTransition);
        }
    }

    @TargetApi(11)
    public static void processSpecialModel(WebView webView) {

        if (null != webView && DeviceUtil.getSDKVersionInt() >= 11) {
            if ("Huawei".equalsIgnoreCase(Build.BRAND) && null != Build.MODEL && (Build.MODEL.contains("T8951") || Build.MODEL.contains("t8951"))) {
                webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            } else if (DeviceUtil.getSDKVersionInt() >= 19) {
                webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        }

    }

    /**
     *
     * 功能描述:获取联系人电话号码
     * <pre>
     * </pre>
     *
     * @param phoneCount
     * @param contactId
     * @return
     */
    public static String getPhoneNumber(int phoneCount, int contactId) {
        String phoneNumber = "";
        if (phoneCount > 0) {
            Cursor phones = AppContext.getInstance().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
            if (null != phones) {
                if (phones.moveToFirst()) {
                    phoneNumber = phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                phones.close();
            }
        }
        return phoneNumber;
    }

    /**
     *
     * 功能描述:获取联系人email地址
     *
     * @param contactId
     * @return
     */
    public static String getEmail(int contactId) {
        String emailValue = "";
        Cursor emails = AppContext.getInstance().getContentResolver()
                .query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
        if (null != emails) {
            if (emails.moveToFirst()) {
                emailValue = emails.getString(emails.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DATA));
            }
            emails.close();
        }
        return emailValue;
    }



    /**
     * 将EditText的password char to "*"
     * @param editText  editText 控件对象
     */
    public static void convertPasswordCharToStar(EditText editText){

        editText.setTransformationMethod(new StarPasswordTransformationMethod());
    }


    private static class StarPasswordTransformationMethod extends PasswordTransformationMethod {

        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        private class PasswordCharSequence implements CharSequence {

            private CharSequence mSource;

            public PasswordCharSequence(CharSequence source) {
                //Store char sequence
                mSource = source;
            }

            public char charAt(int index) {
                //This is the important part
                return '*';
            }

            public int length() {
                //Return default
                return mSource.length();
            }

            public CharSequence subSequence(int start, int end) {
                //Return default
                return mSource.subSequence(start, end);
            }
        }
    };

    private static long lastClickTime=0;

  /*  public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }*/

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 1500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
