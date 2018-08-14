package com.grandream.dagt.bean.wallet;

/**
 * Created by chenjing on 2018/5/11.
 */

public class RechargeBean {

    /**
     * coin_name : ETH
     * coin_address : 12345678900987654321
     * recharge_coin_tip_info : 温馨提示
     1、请勿向上述地址充值任何非ETH资产，否则资产将不可找回。
     2、您完成充值操作后，需要整个网络节点的确认才能到账
     */

    private String coin_name;
    private String coin_address;
    private String recharge_coin_tip_info;

    public String getCoin_name() {
        return coin_name;
    }

    public void setCoin_name(String coin_name) {
        this.coin_name = coin_name;
    }

    public String getCoin_address() {
        return coin_address;
    }

    public void setCoin_address(String coin_address) {
        this.coin_address = coin_address;
    }

    public String getRecharge_coin_tip_info() {
        return recharge_coin_tip_info;
    }

    public void setRecharge_coin_tip_info(String recharge_coin_tip_info) {
        this.recharge_coin_tip_info = recharge_coin_tip_info;
    }
}
