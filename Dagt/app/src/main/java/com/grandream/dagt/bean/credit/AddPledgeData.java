package com.grandream.dagt.bean.credit;

/**
 * Created by chenjing on 2018/5/8.
 */

public class AddPledgeData {

    /**
     * success : 1
     * data : {"current_pledge_rate":"0.03%","order_sequence_code":"A_20180428155706187187","wallet_contract_address":"abcdefghijklmnopqrstuvwxyz","append_loan_coin_total":"11.99370558","append_loan_coin_name":"ETH","user_wallet_address":"fuhfhf","base_pledge_rate":"34%","append_pledge_goods_tips_info":"当前质押率：0.03%，建议追加11.99370558个ETH，质押率可达到34%","select_agreement_tips":"阅读并同意签署《授信协议》","append_pledge_tip_info":"质押风险提示\n,1、请尽快完成追加质押品操作以防止系统自动平仓。\n2、请确保追加的质押币种与上次质押币种相同。\n3、请勿向上述地址充值任何非【以太坊】资产，否则资产将不可找回。\n4、\t您完成充值操作后，需要整个网络节点的确认才能到账。\n5、\t提币到合约地址可能会发生合约执行失败，将导致转账失败，资产将退回至用户原钱包地址。处理时间可能较长，请您谅解。","append_pledge_submit_text":"知道了"}
     */

    private int success;
    private DataBean data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * current_pledge_rate : 0.03%
         * order_sequence_code : A_20180428155706187187
         * wallet_contract_address : abcdefghijklmnopqrstuvwxyz
         * append_loan_coin_total : 11.99370558
         * append_loan_coin_name : ETH
         * user_wallet_address : fuhfhf
         * base_pledge_rate : 34%
         * append_pledge_goods_tips_info : 当前质押率：0.03%，建议追加11.99370558个ETH，质押率可达到34%
         * select_agreement_tips : 阅读并同意签署《授信协议》
         * append_pledge_tip_info : 质押风险提示
         ,1、请尽快完成追加质押品操作以防止系统自动平仓。
         2、请确保追加的质押币种与上次质押币种相同。
         3、请勿向上述地址充值任何非【以太坊】资产，否则资产将不可找回。
         4、	您完成充值操作后，需要整个网络节点的确认才能到账。
         5、	提币到合约地址可能会发生合约执行失败，将导致转账失败，资产将退回至用户原钱包地址。处理时间可能较长，请您谅解。
         * append_pledge_submit_text : 知道了
         */

        private String current_pledge_rate;
        private String order_sequence_code;
        private String wallet_contract_address;
        private String append_loan_coin_total;
        private String append_loan_coin_name;
        private String user_wallet_address;
        private String base_pledge_rate;
        private String append_pledge_goods_tips_info;
        private String select_agreement_tips;
        private String append_pledge_tip_info;
        private String append_pledge_submit_text;

        public String getCurrent_pledge_rate() {
            return current_pledge_rate;
        }

        public void setCurrent_pledge_rate(String current_pledge_rate) {
            this.current_pledge_rate = current_pledge_rate;
        }

        public String getOrder_sequence_code() {
            return order_sequence_code;
        }

        public void setOrder_sequence_code(String order_sequence_code) {
            this.order_sequence_code = order_sequence_code;
        }

        public String getWallet_contract_address() {
            return wallet_contract_address;
        }

        public void setWallet_contract_address(String wallet_contract_address) {
            this.wallet_contract_address = wallet_contract_address;
        }

        public String getAppend_loan_coin_total() {
            return append_loan_coin_total;
        }

        public void setAppend_loan_coin_total(String append_loan_coin_total) {
            this.append_loan_coin_total = append_loan_coin_total;
        }

        public String getAppend_loan_coin_name() {
            return append_loan_coin_name;
        }

        public void setAppend_loan_coin_name(String append_loan_coin_name) {
            this.append_loan_coin_name = append_loan_coin_name;
        }

        public String getUser_wallet_address() {
            return user_wallet_address;
        }

        public void setUser_wallet_address(String user_wallet_address) {
            this.user_wallet_address = user_wallet_address;
        }

        public String getBase_pledge_rate() {
            return base_pledge_rate;
        }

        public void setBase_pledge_rate(String base_pledge_rate) {
            this.base_pledge_rate = base_pledge_rate;
        }

        public String getAppend_pledge_goods_tips_info() {
            return append_pledge_goods_tips_info;
        }

        public void setAppend_pledge_goods_tips_info(String append_pledge_goods_tips_info) {
            this.append_pledge_goods_tips_info = append_pledge_goods_tips_info;
        }

        public String getSelect_agreement_tips() {
            return select_agreement_tips;
        }

        public void setSelect_agreement_tips(String select_agreement_tips) {
            this.select_agreement_tips = select_agreement_tips;
        }

        public String getAppend_pledge_tip_info() {
            return append_pledge_tip_info;
        }

        public void setAppend_pledge_tip_info(String append_pledge_tip_info) {
            this.append_pledge_tip_info = append_pledge_tip_info;
        }

        public String getAppend_pledge_submit_text() {
            return append_pledge_submit_text;
        }

        public void setAppend_pledge_submit_text(String append_pledge_submit_text) {
            this.append_pledge_submit_text = append_pledge_submit_text;
        }
    }
}
