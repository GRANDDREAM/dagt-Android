package com.grandream.dagt.requestbody.user;

/**
 * Created by chenjing on 2018/4/17.
 */

public class ResetLogPwd {
    private String user_id;
    private String original_password;//原始密码
    private String new_password;//新密碼
    private String confirm_password;//確認密碼

    public ResetLogPwd(String user_id, String original_password, String new_password, String confirm_password) {
        this.user_id = user_id;
        this.original_password = original_password;
        this.new_password = new_password;
        this.confirm_password = confirm_password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOriginal_password() {
        return original_password;
    }

    public void setOriginal_password(String original_password) {
        this.original_password = original_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}
