package com.grandream.dagt.requestbody.credit;

/**
 * Created by chenjing on 2018/5/8.
 */

public class CreditDetailBody {
    private String user_id;
    private String order_sequence_code;
    private String trade_password;
    private String coin_id;
    private String trade_code;

    public String getTrade_code() {
        return trade_code;
    }

    public void setTrade_code(String trade_code) {
        this.trade_code = trade_code;
    }

    public String getCoin_id() {
        return coin_id;
    }

    public void setCoin_id(String coin_id) {
        this.coin_id = coin_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_sequence_code() {
        return order_sequence_code;
    }

    public void setOrder_sequence_code(String order_sequence_code) {
        this.order_sequence_code = order_sequence_code;
    }

    public String getTrade_password() {
        return trade_password;
    }

    public void setTrade_password(String trade_password) {
        this.trade_password = trade_password;
    }

    public CreditDetailBody(String user_id, String order_sequence_code) {
        this.user_id = user_id;
        this.order_sequence_code = order_sequence_code;
    }
}
