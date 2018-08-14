package com.grandream.dagt.bean.credit;

import java.io.Serializable;

/**
 * Created by chenjing on 2018/4/25.
 */

public class CreditInfoReslut implements Serializable {


        /**
         * order_id : 452
         * data : {"order_sequence_code":"O_20180608171814401612","wallet_contract_address":"0xd3811261320a36d7b49d540aa31d9b2f32afb9f4","loan_coin_total":"2.91120815","loan_coin_total_text":"请将2.91120815个ETH转账到智能合约钱包地址进行质押操作","user_wallet_address":"0xceeb8ddc21c33d434a362db6033dbf321f30927d","loan_coin_id":"1","select_agreement_tips":"阅读并同意签署《授信协议》","tip_info":"温馨提示\n,1：用户可实时通过智能合约钱包地址在区块链上查询质押品情况。\n2：请勿向上述地址充值任何非ETH资产，否则资产将不可找回。\n3：您完成操作后，需要整个网络节点的确认才能到帐。\n4：提币到合约地址可能会发生合约执行失败，将导致转账失败。资产将退回至用户原钱包地址。处理时间可能较长，请您谅解。","submit_text":"转币完成，创建授信函"}
         */

        private String order_id;
        private DataBean data;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean implements Serializable {
            /**
             * order_sequence_code : O_20180608171814401612
             * wallet_contract_address : 0xd3811261320a36d7b49d540aa31d9b2f32afb9f4
             * loan_coin_total : 2.91120815
             * loan_coin_total_text : 请将2.91120815个ETH转账到智能合约钱包地址进行质押操作
             * user_wallet_address : 0xceeb8ddc21c33d434a362db6033dbf321f30927d
             * loan_coin_id : 1
             * select_agreement_tips : 阅读并同意签署《授信协议》
             * tip_info : 温馨提示
             ,1：用户可实时通过智能合约钱包地址在区块链上查询质押品情况。
             2：请勿向上述地址充值任何非ETH资产，否则资产将不可找回。
             3：您完成操作后，需要整个网络节点的确认才能到帐。
             4：提币到合约地址可能会发生合约执行失败，将导致转账失败。资产将退回至用户原钱包地址。处理时间可能较长，请您谅解。
             * submit_text : 转币完成，创建授信函
             */

            private String order_sequence_code;
            private String wallet_contract_address;
            private String loan_coin_total;
            private String loan_coin_total_text;
            private String user_wallet_address;
            private String loan_coin_id;
            private String select_agreement_tips;
            private String tip_info;
            private String submit_text;

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

            public String getLoan_coin_total() {
                return loan_coin_total;
            }

            public void setLoan_coin_total(String loan_coin_total) {
                this.loan_coin_total = loan_coin_total;
            }

            public String getLoan_coin_total_text() {
                return loan_coin_total_text;
            }

            public void setLoan_coin_total_text(String loan_coin_total_text) {
                this.loan_coin_total_text = loan_coin_total_text;
            }

            public String getUser_wallet_address() {
                return user_wallet_address;
            }

            public void setUser_wallet_address(String user_wallet_address) {
                this.user_wallet_address = user_wallet_address;
            }

            public String getLoan_coin_id() {
                return loan_coin_id;
            }

            public void setLoan_coin_id(String loan_coin_id) {
                this.loan_coin_id = loan_coin_id;
            }

            public String getSelect_agreement_tips() {
                return select_agreement_tips;
            }

            public void setSelect_agreement_tips(String select_agreement_tips) {
                this.select_agreement_tips = select_agreement_tips;
            }

            public String getTip_info() {
                return tip_info;
            }

            public void setTip_info(String tip_info) {
                this.tip_info = tip_info;
            }

            public String getSubmit_text() {
                return submit_text;
            }

            public void setSubmit_text(String submit_text) {
                this.submit_text = submit_text;
            }
        }
}
