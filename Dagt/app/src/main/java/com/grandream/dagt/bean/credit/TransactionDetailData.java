package com.grandream.dagt.bean.credit;

import java.util.List;

/**
 * Created by chenjing on 2018/6/22.
 */

public class TransactionDetailData {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 338
         * user_tid : 37
         * order_sequence : O_20180620142945930423
         * trade_code : T_20180620143859267504
         * chain_trade_code : 0x17b6f4c06b98c5d9224f5ae8c9a28a59792fe27da54437aa4e41b35dfe9cf0c8
         * total : 1.00000000
         * trade_status_id : 10037
         * trade_type_id : 10038
         * memo : null
         * trade_time : 2018-06-20 14:38:59
         * create_time : 2018-06-20 14:38:59
         * coin_name : Ethereum
         * trade_status_name : 交易失败
         * trade_type_name : 授信申请
         */

        private String id;
        private String user_tid;
        private String order_sequence;
        private String trade_code;
        private String chain_trade_code;
        private String total;
        private String trade_status_id;
        private String trade_type_id;
        private Object memo;
        private String trade_time;
        private String create_time;
        private String coin_name;
        private String trade_status_name;
        private String trade_type_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_tid() {
            return user_tid;
        }

        public void setUser_tid(String user_tid) {
            this.user_tid = user_tid;
        }

        public String getOrder_sequence() {
            return order_sequence;
        }

        public void setOrder_sequence(String order_sequence) {
            this.order_sequence = order_sequence;
        }

        public String getTrade_code() {
            return trade_code;
        }

        public void setTrade_code(String trade_code) {
            this.trade_code = trade_code;
        }

        public String getChain_trade_code() {
            return chain_trade_code;
        }

        public void setChain_trade_code(String chain_trade_code) {
            this.chain_trade_code = chain_trade_code;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getTrade_status_id() {
            return trade_status_id;
        }

        public void setTrade_status_id(String trade_status_id) {
            this.trade_status_id = trade_status_id;
        }

        public String getTrade_type_id() {
            return trade_type_id;
        }

        public void setTrade_type_id(String trade_type_id) {
            this.trade_type_id = trade_type_id;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public String getTrade_time() {
            return trade_time;
        }

        public void setTrade_time(String trade_time) {
            this.trade_time = trade_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCoin_name() {
            return coin_name;
        }

        public void setCoin_name(String coin_name) {
            this.coin_name = coin_name;
        }

        public String getTrade_status_name() {
            return trade_status_name;
        }

        public void setTrade_status_name(String trade_status_name) {
            this.trade_status_name = trade_status_name;
        }

        public String getTrade_type_name() {
            return trade_type_name;
        }

        public void setTrade_type_name(String trade_type_name) {
            this.trade_type_name = trade_type_name;
        }
    }
}
