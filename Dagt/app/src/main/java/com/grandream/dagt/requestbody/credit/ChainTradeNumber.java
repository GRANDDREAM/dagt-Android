package com.grandream.dagt.requestbody.credit;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chenjing on 2018/4/27.
 */

public class ChainTradeNumber {
    private String user_id;
    private String order_id;
    private String user_wallet_address;
    private String order_sequence_code;
    private String user_real_damage_amount;
    private HashMap<String, String> chain_trade_number;
    private String current_loan_coin_total;

    public String getCurrent_loan_coin_total() {
        return current_loan_coin_total;
    }

    public void setCurrent_loan_coin_total(String current_loan_coin_total) {
        this.current_loan_coin_total = current_loan_coin_total;
    }

    public HashMap<String, String> getChain_trade_number() {
        return chain_trade_number;
    }

    public void setChain_trade_number(HashMap<String, String> chain_trade_number) {
        this.chain_trade_number = chain_trade_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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

    public String getUser_real_damage_amount() {
        return user_real_damage_amount;
    }

    public void setUser_real_damage_amount(String user_real_damage_amount) {
        this.user_real_damage_amount = user_real_damage_amount;
    }


    public static class ChainTradeNumberBean {
        /**
         * trade_code_value : chain_trade_code_value
         */

        private String trade_code_value;

        public String getTrade_code_value() {
            return trade_code_value;
        }

        public void setTrade_code_value(String trade_code_value) {
            this.trade_code_value = trade_code_value;
        }
    }
}
