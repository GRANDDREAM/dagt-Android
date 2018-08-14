package com.grandream.dagt.http.interfaces;

/**
 * Code 校验
 *
 * @author Chen
 * @version V1.0
 * @date 17/3/12
 */
public interface ICodeVerify {
    boolean checkValid(int code);

    String formatCodeMessage(int code, String message);
}
