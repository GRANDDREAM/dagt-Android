package com.grandream.dagt.bean.credit;

import java.util.List;

/**
 * Created by chenjing on 2018/4/23.
 */

public class CoinData {

    /**
     * coin_data : [{"id":"1","digit_coin_name":"Ethereum","digit_coin_alias_name":"ETH","digit_coin_icon":"http://testapi.51kjq.com/uploads/user_logo/1525832328651.jpg","status":"1","digit_coin_name_ch":"以太坊","digit_coin_query_price_symbol":"etcusdt","oracle_api_coin_token":"1","create_time":"2018-05-23 15:27:26"}]
     * user_other_wallet_address : 0xdac0e6113da0f4136eceab4363ee95aa1d54796b
     * user_dagt_wallet_address : 0x60caee25781cd40b55a8e9fe837e0f61e9f964c8
     * rule_id : 2
     */

    private String user_other_wallet_address;
    private String user_dagt_wallet_address;
    private int rule_id;
    private List<CoinDataBean> coin_data;

    public String getUser_other_wallet_address() {
        return user_other_wallet_address;
    }

    public void setUser_other_wallet_address(String user_other_wallet_address) {
        this.user_other_wallet_address = user_other_wallet_address;
    }

    public String getUser_dagt_wallet_address() {
        return user_dagt_wallet_address;
    }

    public void setUser_dagt_wallet_address(String user_dagt_wallet_address) {
        this.user_dagt_wallet_address = user_dagt_wallet_address;
    }

    public int getRule_id() {
        return rule_id;
    }

    public void setRule_id(int rule_id) {
        this.rule_id = rule_id;
    }

    public List<CoinDataBean> getCoin_data() {
        return coin_data;
    }

    public void setCoin_data(List<CoinDataBean> coin_data) {
        this.coin_data = coin_data;
    }

    public static class CoinDataBean {
        /**
         * id : 1
         * digit_coin_name : Ethereum
         * digit_coin_alias_name : ETH
         * digit_coin_icon : http://testapi.51kjq.com/uploads/user_logo/1525832328651.jpg
         * status : 1
         * digit_coin_name_ch : 以太坊
         * digit_coin_query_price_symbol : etcusdt
         * oracle_api_coin_token : 1
         * create_time : 2018-05-23 15:27:26
         */

        private String id;
        private String digit_coin_name;
        private String digit_coin_alias_name;
        private String digit_coin_icon;
        private String status;
        private String digit_coin_name_ch;
        private String digit_coin_query_price_symbol;
        private String oracle_api_coin_token;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDigit_coin_name() {
            return digit_coin_name;
        }

        public void setDigit_coin_name(String digit_coin_name) {
            this.digit_coin_name = digit_coin_name;
        }

        public String getDigit_coin_alias_name() {
            return digit_coin_alias_name;
        }

        public void setDigit_coin_alias_name(String digit_coin_alias_name) {
            this.digit_coin_alias_name = digit_coin_alias_name;
        }

        public String getDigit_coin_icon() {
            return digit_coin_icon;
        }

        public void setDigit_coin_icon(String digit_coin_icon) {
            this.digit_coin_icon = digit_coin_icon;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDigit_coin_name_ch() {
            return digit_coin_name_ch;
        }

        public void setDigit_coin_name_ch(String digit_coin_name_ch) {
            this.digit_coin_name_ch = digit_coin_name_ch;
        }

        public String getDigit_coin_query_price_symbol() {
            return digit_coin_query_price_symbol;
        }

        public void setDigit_coin_query_price_symbol(String digit_coin_query_price_symbol) {
            this.digit_coin_query_price_symbol = digit_coin_query_price_symbol;
        }

        public String getOracle_api_coin_token() {
            return oracle_api_coin_token;
        }

        public void setOracle_api_coin_token(String oracle_api_coin_token) {
            this.oracle_api_coin_token = oracle_api_coin_token;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
