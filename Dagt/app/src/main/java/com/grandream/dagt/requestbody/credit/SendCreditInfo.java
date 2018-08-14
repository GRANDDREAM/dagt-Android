package com.grandream.dagt.requestbody.credit;

/**
 * 钱包类型，用户个人钱包(person)和用户在dagt的钱包(dagt)
 * Created by chenjing on 2018/4/23.
 */

public class SendCreditInfo {
    private String user_id;
    private String wallet_type;//钱包类型，用户个人钱包(person)和用户在dagt的钱包(dagt)
    private String wallet_address;
    private String coin_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWallet_type() {
        return wallet_type;
    }

    /**
     * 钱包类型，用户个人钱包(person)和用户在dagt的钱包(dagt)
     *
     * @param wallet_type
     */
    public void setWallet_type(String wallet_type) {
        this.wallet_type = wallet_type;
    }

    public String getWallet_address() {
        return wallet_address;
    }

    public void setWallet_address(String wallet_address) {
        this.wallet_address = wallet_address;
    }

    public String getCoin_id() {
        return coin_id;
    }

    public void setCoin_id(String coin_id) {
        this.coin_id = coin_id;
    }
}
