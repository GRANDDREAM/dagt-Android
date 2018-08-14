package com.grandream.dagt.requestbody.credit;

/**
 * Created by chenjing on 2018/4/27.
 */

public class TransactionSerialNumBean {
    private String user_id;
    private String user_wallet_address;
    private String order_sequence_code;
    private String chain_trade_code;
    private String order_id;
    private String trade_type;

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_wallet_address() {
        return user_wallet_address;
    }

    public void setUser_wallet_address(String user_wallet_address) {
        this.user_wallet_address = user_wallet_address;
    }

    public String getOrder_sequence_code() {
        return order_sequence_code;
    }

    public void setOrder_sequence_code(String order_sequence_code) {
        this.order_sequence_code = order_sequence_code;
    }

    public String getChain_trade_code() {
        return chain_trade_code;
    }

    public void setChain_trade_code(String chain_trade_code) {
        this.chain_trade_code = chain_trade_code;
    }
}
