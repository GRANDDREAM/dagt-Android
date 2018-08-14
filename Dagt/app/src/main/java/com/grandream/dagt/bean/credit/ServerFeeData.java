package com.grandream.dagt.bean.credit;

import java.util.List;

/**
 * Created by chenjing on 2018/5/9.
 */

public class ServerFeeData {

    /**
     * data : {"user_wallet_address":"12345678900987654321","server_fee_name":"￥68000.0000元≈10271.90 USD","pay_type":[{"coin_icon":"","coin_name":"","coin_id":"","remainder_number":44,"pay_number":"0.21222933"},{"coin_icon":null,"coin_name":"ETH","coin_id":"1","pay_number":"1.24840787","remainder_number":10}]}
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
         * user_wallet_address : 12345678900987654321
         * server_fee_name : ￥68000.0000元≈10271.90 USD
         * pay_type : [{"coin_icon":"","coin_name":"","coin_id":"","remainder_number":44,"pay_number":"0.21222933"},{"coin_icon":null,"coin_name":"ETH","coin_id":"1","pay_number":"1.24840787","remainder_number":10}]
         */

        private String user_wallet_address;
        private String server_fee_name;
        private List<PayTypeBean> pay_type;

        public String getUser_wallet_address() {
            return user_wallet_address;
        }

        public void setUser_wallet_address(String user_wallet_address) {
            this.user_wallet_address = user_wallet_address;
        }

        public String getServer_fee_name() {
            return server_fee_name;
        }

        public void setServer_fee_name(String server_fee_name) {
            this.server_fee_name = server_fee_name;
        }

        public List<PayTypeBean> getPay_type() {
            return pay_type;
        }

        public void setPay_type(List<PayTypeBean> pay_type) {
            this.pay_type = pay_type;
        }

        public static class PayTypeBean {
            /**
             * coin_icon :
             * coin_name :
             * coin_id :
             * remainder_number : 44
             * pay_number : 0.21222933
             */

            private String coin_icon;
            private String coin_name;
            private String coin_id;
            private int remainder_number;
            private String pay_number;

            public String getCoin_icon() {
                return coin_icon;
            }

            public void setCoin_icon(String coin_icon) {
                this.coin_icon = coin_icon;
            }

            public String getCoin_name() {
                return coin_name;
            }

            public void setCoin_name(String coin_name) {
                this.coin_name = coin_name;
            }

            public String getCoin_id() {
                return coin_id;
            }

            public void setCoin_id(String coin_id) {
                this.coin_id = coin_id;
            }

            public int getRemainder_number() {
                return remainder_number;
            }

            public void setRemainder_number(int remainder_number) {
                this.remainder_number = remainder_number;
            }

            public String getPay_number() {
                return pay_number;
            }

            public void setPay_number(String pay_number) {
                this.pay_number = pay_number;
            }
        }
    }
}
