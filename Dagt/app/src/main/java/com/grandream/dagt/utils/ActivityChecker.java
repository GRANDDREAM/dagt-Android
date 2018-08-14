package com.grandream.dagt.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by chenjing on 2018/3/9.
 */

public class ActivityChecker {
    /**
     * <pre>
     * 判断是否可以忽略回调
     * 在对服务器发送请求后，页面已经关闭，但是回调没有及时销毁
     * 而这时候产生的回调如果对页面进行了某些操作都会导致应用异常
     *
     * 处理方法：
     * 在产生回调时先用此方法检查相关状态
     * </pre>
     *
     * @param
     * @return true表示不需要再执行回调动作, false表示可以接着执行回调动作
     */
    public static boolean needBreak(Object... objects) {
        if (objects == null)
            return true;

        for (Object obj : objects) {
            if (obj instanceof Activity && !isValid((Activity) obj)) {
                return true;
            } else if (obj instanceof Fragment && !isValid((Fragment) obj)) {
                return true;
            } else if (!isValid(obj)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValid(Activity activity) {
        if (activity == null)
            return false;

        return !activity.isFinishing();
    }

    private static boolean isValid(Fragment fragment) {
        if (fragment == null)
            return false;

        return isValid(fragment.getActivity());
    }

    private static boolean isValid(Object obj) {
        return obj != null;
    }
}
