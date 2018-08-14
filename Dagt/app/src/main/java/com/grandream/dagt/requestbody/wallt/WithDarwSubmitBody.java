package com.grandream.dagt.requestbody.wallt;

/**
 * Created by chenjing on 2018/5/14.
 */

public class WithDarwSubmitBody {
    private String user_id;
    private String telephone;
    private String coin_alias_name;
    private String sms_code;
    private String withdraw_coin_address;
    private String withdraw_coin_number;
    private String withdraw_coin_fee;
    private String trade_password;

    public WithDarwSubmitBody(String user_id, String telephone, String coin_alias_name, String sms_code, String withdraw_coin_address, String withdraw_coin_number, String withdraw_coin_fee, String trade_password) {
        this.user_id = user_id;
        this.telephone = telephone;
        this.coin_alias_name = coin_alias_name;
        this.sms_code = sms_code;
        this.withdraw_coin_address = withdraw_coin_address;
        this.withdraw_coin_number = withdraw_coin_number;
        this.withdraw_coin_fee = withdraw_coin_fee;
        this.trade_password = trade_password;
    }
}
