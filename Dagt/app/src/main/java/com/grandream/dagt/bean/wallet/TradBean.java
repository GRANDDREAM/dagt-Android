package com.grandream.dagt.bean.wallet;

/**
 * Created by chenjing on 2018/5/11.
 */

public class TradBean {

    /**
     * trade_type : 提币
     * trade_number : 38
     * trade_coin_alias_name : ETH
     * trade_address : 000000---------900000
     * trade_status : 已完成
     * trade_fee : 4
     * chain_trade_code : 5af4fff19e160
     * trade_time : 2018年05月11日 10:29:07
     */

    private String trade_type;
    private String trade_number;
    private String trade_coin_alias_name;
    private String trade_address;
    private String trade_status;
    private String trade_fee;
    private String chain_trade_code;
    private String trade_time;

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_number() {
        return trade_number;
    }

    public void setTrade_number(String trade_number) {
        this.trade_number = trade_number;
    }

    public String getTrade_coin_alias_name() {
        return trade_coin_alias_name;
    }

    public void setTrade_coin_alias_name(String trade_coin_alias_name) {
        this.trade_coin_alias_name = trade_coin_alias_name;
    }

    public String getTrade_address() {
        return trade_address;
    }

    public void setTrade_address(String trade_address) {
        this.trade_address = trade_address;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getTrade_fee() {
        return trade_fee;
    }

    public void setTrade_fee(String trade_fee) {
        this.trade_fee = trade_fee;
    }

    public String getChain_trade_code() {
        return chain_trade_code;
    }

    public void setChain_trade_code(String chain_trade_code) {
        this.chain_trade_code = chain_trade_code;
    }

    public String getTrade_time() {
        return trade_time;
    }

    public void setTrade_time(String trade_time) {
        this.trade_time = trade_time;
    }
}
