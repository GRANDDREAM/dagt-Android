package com.grandream.dagt.bean.my;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenjing on 2018/5/2.
 */

public class TakeCoinAddressData implements Serializable {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * user_tid : 3
         * coin_address_name : Dad
         * coin_address : jasdasdasdasdsdffssdf
         * status : 1
         * create_time : 2018-04-18 14:16:39
         */

        private String id;
        private String user_id;
        private String coin_address_name;
        private String coin_address;
        private String status;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCoin_address_name() {
            return coin_address_name;
        }

        public void setCoin_address_name(String coin_address_name) {
            this.coin_address_name = coin_address_name;
        }

        public String getCoin_address() {
            return coin_address;
        }

        public void setCoin_address(String coin_address) {
            this.coin_address = coin_address;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
