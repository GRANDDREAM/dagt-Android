package com.grandream.dagt.bean.wallet;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenjing on 2018/5/10.
 */

public class CoinDetail implements Serializable {

    /**
     * coin_icon : null
     * remainder_number : 38
     * trade_info : [{"trade_type":"充币","chain_trade_code":"5af418de606d2","trade_number":10,"trade_status_name":"已完成","trade_time":"18:03:10 2018/05/10"},{"trade_type":"充币","chain_trade_code":"5af418de606f8","trade_number":8,"trade_status_name":"已完成","trade_time":"18:03:10 2018/05/10"},{"trade_type":"提币","chain_trade_code":"5af418de6072f","trade_number":2,"trade_status_name":"已完成","trade_time":"18:03:10 2018/05/10"}]
     */

    private Object coin_icon;
    private String coin_alias_name;
    private String remainder_number;
    private List<TradeInfoBean> trade_info;


    public String getCoin_alias_name() {
        return coin_alias_name;
    }

    public void setCoin_alias_name(String coin_alias_name) {
        this.coin_alias_name = coin_alias_name;
    }

    public Object getCoin_icon() {
        return coin_icon;
    }

    public void setCoin_icon(Object coin_icon) {
        this.coin_icon = coin_icon;
    }

    public String getRemainder_number() {
        return remainder_number;
    }

    public void setRemainder_number(String remainder_number) {
        this.remainder_number = remainder_number;
    }

    public List<TradeInfoBean> getTrade_info() {
        return trade_info;
    }

    public void setTrade_info(List<TradeInfoBean> trade_info) {
        this.trade_info = trade_info;
    }

    public static class TradeInfoBean implements Serializable {
        /**
         * trade_type : 充币
         * chain_trade_code : 5af418de606d2
         * trade_number : 10
         * trade_status_name : 已完成
         * trade_time : 18:03:10 2018/05/10
         */

        private String trade_type;
        private String chain_trade_code;
        private String trade_number;
        private String trade_status_name;
        private String trade_time;

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getChain_trade_code() {
            return chain_trade_code;
        }

        public void setChain_trade_code(String chain_trade_code) {
            this.chain_trade_code = chain_trade_code;
        }

        public String getTrade_number() {
            return trade_number;
        }

        public void setTrade_number(String trade_number) {
            this.trade_number = trade_number;
        }

        public String getTrade_status_name() {
            return trade_status_name;
        }

        public void setTrade_status_name(String trade_status_name) {
            this.trade_status_name = trade_status_name;
        }

        public String getTrade_time() {
            return trade_time;
        }

        public void setTrade_time(String trade_time) {
            this.trade_time = trade_time;
        }
    }
}
