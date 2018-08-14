package com.grandream.dagt.requestbody.user;

/**
 * Created by chenjing on 2018/4/12.
 */

public class DoRegister {
    private String user_name;
    private String verify_code;
    private String sms_code;
    private String password;
    private int register_channel = 1;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public String getSms_code() {
        return sms_code;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRegister_channel() {
        return register_channel;
    }

    public void setRegister_channel(int register_channel) {
        this.register_channel = register_channel;
    }
}
