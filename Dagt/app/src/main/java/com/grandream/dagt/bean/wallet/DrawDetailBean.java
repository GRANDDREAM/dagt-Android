package com.grandream.dagt.bean.wallet;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenjing on 2018/5/14.
 */

public class DrawDetailBean implements Serializable{

    /**
     * user_draw_coin_address : [{"id":"9","user_id":"5","coin_address_name":"bbb","coin_address":"bbbbbbbbbbbbbbbbbbbbbb","create_time":"2018-05-02 10:44:55"},{"id":"10","user_id":"5","coin_address_name":"sjjs","coin_address":"zhjzjzkszs","create_time":"2018-05-02 17:01:58"},{"id":"12","user_id":"5","coin_address_name":"sjjs","coin_address":"zhjzjzkszsgg","create_time":"2018-05-02 17:17:39"}]
     * remainder_number : 33
     * draw_coin_name : ETH
     * draw_coin_number_tip_info : 可提数量  33  ETH.
     * draw_coin_fee : 0.01
     * draw_coin_fee_step : 0.005
     * draw_coin_tip_info : 温馨提示
     * 1、请不要直接提币到ICO的众筹地址，这会导致您无法收取众筹到的数字资产。
     * 2、提币到合约地址可能会发生合约执行失败，将导致转账失败，资产将退回至用户原钱包地址。处理时间可能较长，请您谅解。
     */

    private String remainder_number;
    private String draw_coin_name;
    private String draw_coin_number_tip_info;
    private String draw_coin_fee;
    private String draw_coin_fee_step;
    private String draw_coin_tip_info;
    private List<UserDrawCoinAddressBean> user_draw_coin_address;

    public String getRemainder_number() {
        return remainder_number;
    }

    public void setRemainder_number(String remainder_number) {
        this.remainder_number = remainder_number;
    }

    public String getDraw_coin_name() {
        return draw_coin_name;
    }

    public void setDraw_coin_name(String draw_coin_name) {
        this.draw_coin_name = draw_coin_name;
    }

    public String getDraw_coin_number_tip_info() {
        return draw_coin_number_tip_info;
    }

    public void setDraw_coin_number_tip_info(String draw_coin_number_tip_info) {
        this.draw_coin_number_tip_info = draw_coin_number_tip_info;
    }

    public String getDraw_coin_fee() {
        return draw_coin_fee;
    }

    public void setDraw_coin_fee(String draw_coin_fee) {
        this.draw_coin_fee = draw_coin_fee;
    }

    public String getDraw_coin_fee_step() {
        return draw_coin_fee_step;
    }

    public void setDraw_coin_fee_step(String draw_coin_fee_step) {
        this.draw_coin_fee_step = draw_coin_fee_step;
    }

    public String getDraw_coin_tip_info() {
        return draw_coin_tip_info;
    }

    public void setDraw_coin_tip_info(String draw_coin_tip_info) {
        this.draw_coin_tip_info = draw_coin_tip_info;
    }

    public List<UserDrawCoinAddressBean> getUser_draw_coin_address() {
        return user_draw_coin_address;
    }

    public void setUser_draw_coin_address(List<UserDrawCoinAddressBean> user_draw_coin_address) {
        this.user_draw_coin_address = user_draw_coin_address;
    }

    public static class UserDrawCoinAddressBean implements Serializable {
        /**
         * id : 9
         * user_id : 5
         * coin_address_name : bbb
         * coin_address : bbbbbbbbbbbbbbbbbbbbbb
         * create_time : 2018-05-02 10:44:55
         */

        private String id;
        private String user_id;
        private String coin_address_name;
        private String coin_address;
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
