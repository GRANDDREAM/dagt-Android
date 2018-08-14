package com.grandream.dagt.requestbody;

/**
 * Created by chenjing on 2018/5/10.
 */

public class WalletCoinDetailBody {

    private String user_id;
    private String coin_alias_name;
    private String chain_trade_code;

    public WalletCoinDetailBody(String user_id, String coin_alias_name, String chain_trade_code) {
        this.user_id = user_id;
        this.coin_alias_name = coin_alias_name;
        this.chain_trade_code = chain_trade_code;
    }

    public WalletCoinDetailBody(String user_id, String coin_alias_name) {
        this.user_id = user_id;
        this.coin_alias_name = coin_alias_name;
    }
}
