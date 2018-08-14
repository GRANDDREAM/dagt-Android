package com.grandream.dagt.bean.wallet;

import java.util.List;

/**
 * Created by chenjing on 2018/5/7.
 */

public class WalletIndext {

    /**
     * id : 5
     * user_name : 137****3522
     * country : zh-CN
     * avatar : null
     * {"tip":"温馨提示\n1、私钥是在区块链上用来保护您的账户安全（类似银行账户密码），请不要泄露给任何第三方；\n2、请将私钥抄写下来或拍照保存至安全的地方，不建议截屏保存（可能会被第三方应用截获）；"}
     * wallet_dagt_address : eHBSMHlqb1kzK1ZFM3VlZzl0cXJleWkrRWJFaE9peHUxa3p6cTk2ZXRyQT0=
     * owner_coin_info : [{"coin_name":"DAGT","coin_icon":"","remainder_number":38},{"coin_name":"ETH","coin_icon":null,"remainder_number":31}]
     */
    private String tip;
    private String id;
    private String user_name;
    private String country;
    private String avatar;
    private String wallet_dagt_address;
    private List<OwnerCoinInfoBean> owner_coin_info;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWallet_dagt_address() {
        return wallet_dagt_address;
    }

    public void setWallet_dagt_address(String wallet_dagt_address) {
        this.wallet_dagt_address = wallet_dagt_address;
    }

    public List<OwnerCoinInfoBean> getOwner_coin_info() {
        return owner_coin_info;
    }

    public void setOwner_coin_info(List<OwnerCoinInfoBean> owner_coin_info) {
        this.owner_coin_info = owner_coin_info;
    }

    public static class OwnerCoinInfoBean {
        /**
         * coin_name : DAGT
         * coin_icon :
         * remainder_number : 38
         */

        private String coin_name;
        private String coin_alias_name;
        private String coin_icon;
        private String remainder_number;

        public String getCoin_alias_name() {
            return coin_alias_name;
        }

        public void setCoin_alias_name(String coin_alias_name) {
            this.coin_alias_name = coin_alias_name;
        }

        public String getCoin_name() {
            return coin_name;
        }

        public void setCoin_name(String coin_name) {
            this.coin_name = coin_name;
        }

        public String getCoin_icon() {
            return coin_icon;
        }

        public void setCoin_icon(String coin_icon) {
            this.coin_icon = coin_icon;
        }

        public String getRemainder_number() {
            return remainder_number;
        }

        public void setRemainder_number(String remainder_number) {
            this.remainder_number = remainder_number;
        }
    }
}
