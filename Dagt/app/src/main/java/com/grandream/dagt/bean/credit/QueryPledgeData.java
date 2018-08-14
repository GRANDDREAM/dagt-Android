package com.grandream.dagt.bean.credit;

/**
 * Created by chenjing on 2018/5/9.
 */

public class QueryPledgeData {

    /**
     * data : {"user_wallet_address":"fuhfhf","total":"1.22801314"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_wallet_address : fuhfhf
         * total : 1.22801314
         */

        private String user_wallet_address;
        private String total;

        public String getUser_wallet_address() {
            return user_wallet_address;
        }

        public void setUser_wallet_address(String user_wallet_address) {
            this.user_wallet_address = user_wallet_address;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
