package com.grandream.dagt.http;

import android.support.annotation.IntDef;


import com.grandream.dagt.http.interfaces.ICodeVerify;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Chen
 * @version V1.0
 * @Describe
 * @package com.sameway.cnotes.http
 * @date 17/3/13 下午4:44
 */
public class CodeVerify implements ICodeVerify {

    @IntDef({Code.SUCCESS})
    @Retention(RetentionPolicy.SOURCE)
    @interface Code {
        int SUCCESS = 200;
    }

    @Override
    public boolean checkValid(int code) {
        return code == Code.SUCCESS;
    }


    @Override
    public String formatCodeMessage(int code, String message) {
        return message;
    }

}