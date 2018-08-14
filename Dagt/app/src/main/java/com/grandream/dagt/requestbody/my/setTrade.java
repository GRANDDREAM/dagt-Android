package com.grandream.dagt.requestbody.my;

/**
 * Created by chenjing on 2018/5/3.
 */

public class setTrade {
    private String telephone;
    private String sms_code;
    private String trade_password;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSms_code() {
        return sms_code;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }

    public String getTrade_password() {
        return trade_password;
    }

    public void setTrade_password(String trade_password) {
        this.trade_password = trade_password;
    }
}
