package com.grandream.dagt.bean.credit;

import java.util.List;

/**
 * Created by chenjing on 2018/4/27.
 */

public class OperatinData {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 172
         * user_tid : 5
         * order_sequence : A_20180428155706187187
         * trade_code : T_20180428155711419067
         * chain_trade_code : 0xa39e7a2260864206724ff2bbacd68c25aca4c7575aae1e5601ccvv68dbd86cd303
         * total : 10.00000000
         * trade_status_id : 10036
         * trade_type_id : 10038
         * memo : null
         * trade_time : 0000-00-00 00:00:00
         * create_time : 2018-04-28 15:57:11
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
    }
}
