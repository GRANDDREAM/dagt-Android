package com.grandream.dagt.http.exception;

/**
 * 返回异常
 *
 * @author Chen
 * @version V1.0
 * @date 17/3/12
 */
public class ServerResultException extends RuntimeException {

    private int code;

    public ServerResultException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
