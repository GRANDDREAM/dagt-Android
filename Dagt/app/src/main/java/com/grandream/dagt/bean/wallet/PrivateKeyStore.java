package com.grandream.dagt.bean.wallet;

/**
 * Created by chenjing on 2018/5/10.
 */

public class PrivateKeyStore {

    /**
     * success : 1成功 0失败
     * private_key : dsfafasfsdf
     */

    private int success;
    private String private_key;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }
}
