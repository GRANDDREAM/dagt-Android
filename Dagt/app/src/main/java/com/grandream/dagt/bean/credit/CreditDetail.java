package com.grandream.dagt.bean.credit;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenjing on 2018/5/8.
 */

public class CreditDetail implements Serializable {

    /**
     * data : {"id":"71","order_sequence_code":"A_20180426145503265343","user_wallet_type":"perso","user_wallet_address":"vvh","loan_coin_id":"1","loan_organization_id":"1","loan_organization_years_ratio":"10.00","loan_coin_real_total":"0.00000000","can_amount_limit":"51544.0000","need_amount_limit":"1000.0000","real_loan_amount":null,"order_apply_status":"10010","credit_apply_server_fee":"32000.0000","credit_apply_server_fee_unit":"USD","server_fee_pay_status":"10027","loan_status":"10022","create_time":"2018-04-26 14:55:03","loan_coin_name":"ETH数量0.00000000个","loan_organization_name":"中国好奇银行","server_fee_name":"￥32000.0000元≈4848.48 USD","pledged_rate":"100%","closing_line_price":"0.00","format_create_time":"2018年04月26日","order_apply_status_name":"授信中","server_fee_pay":2,"order_loan_status":1,"shx_trade_info":[{"title":"","total":0,"trade_code":"","trade_time":"","trade_status_name":""}],"bc_trade_info":[{"title":"","total":0,"trade_code":"","trade_time":"","trade_status_name":""}]}
     * button : 1
     * left_text :
     * middle_text : 继续授信
     * right_text :
     * credit_confirm_tip_data : {"select_agreement_tips":"阅读并同意签署《授信协议》","tip_info":"温馨提示\n,1：用户可实时通过智能合约钱包地址在区块链上查询质押品情况。\n2：请勿向上述地址充值任何非ETH资产，否则资产将不可找回。\n3：您完成操作后，需要整个网络节点的确认才能到帐。\n4：提币到合约地址可能会发生合约执行失败，将导致转账失败。资产将退回至用户原钱包地址。处理时间可能较长，请您谅解。","submit_text":"转币完成，创建授信函"}
     */

    private DataBean data;
    private int button;
    private String left_text;
    private String middle_text;
    private String right_text;
    private int rule_id;
    private CreditConfirmTipDataBean credit_confirm_tip_data;

    public int getRule_id() {
        return rule_id;
    }

    public void setRule_id(int rule_id) {
        this.rule_id = rule_id;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }

    public String getLeft_text() {
        return left_text;
    }

    public void setLeft_text(String left_text) {
        this.left_text = left_text;
    }

    public String getMiddle_text() {
        return middle_text;
    }

    public void setMiddle_text(String middle_text) {
        this.middle_text = middle_text;
    }

    public String getRight_text() {
        return right_text;
    }

    public void setRight_text(String right_text) {
        this.right_text = right_text;
    }

    public CreditConfirmTipDataBean getCredit_confirm_tip_data() {
        return credit_confirm_tip_data;
    }

    public void setCredit_confirm_tip_data(CreditConfirmTipDataBean credit_confirm_tip_data) {
        this.credit_confirm_tip_data = credit_confirm_tip_data;
    }

    public static class DataBean {
        /**
         * id : 71
         * order_sequence_code : A_20180426145503265343
         * user_wallet_type : perso
         * user_wallet_address : vvh
         * loan_coin_id : 1
         * loan_organization_id : 1
         * loan_organization_years_ratio : 10.00
         * loan_coin_real_total : 0.00000000
         * can_amount_limit : 51544.0000
         * need_amount_limit : 1000.0000
         * real_loan_amount : null
         * order_apply_status : 10010
         * credit_apply_server_fee : 32000.0000
         * credit_apply_server_fee_unit : USD
         * server_fee_pay_status : 10027
         * loan_status : 10022
         * create_time : 2018-04-26 14:55:03
         * loan_coin_name : ETH数量0.00000000个
         * loan_organization_name : 中国好奇银行
         * server_fee_name : ￥32000.0000元≈4848.48 USD
         * pledged_rate : 100%
         * closing_line_price : 0.00
         * format_create_time : 2018年04月26日
         * order_apply_status_name : 授信中
         * server_fee_pay : 2
         * order_loan_status : 1
         * shx_trade_info : [{"title":"","total":0,"trade_code":"","trade_time":"","trade_status_name":""}]
         * bc_trade_info : [{"title":"","total":0,"trade_code":"","trade_time":"","trade_status_name":""}]
         */

        private String id;
        private String order_sequence_code;
        private String user_wallet_type;
        private String user_wallet_address;
        private String loan_coin_id;
        private String loan_organization_id;
        private String loan_organization_years_ratio;
        private String loan_coin_real_total;
        private String can_amount_limit;
        private String need_amount_limit;
        private Object real_loan_amount;
        private String order_apply_status;
        private String credit_apply_server_fee;
        private String credit_apply_server_fee_unit;
        private String server_fee_pay_status;
        private String loan_status;
        private String create_time;
        private String loan_coin_name;
        private String loan_organization_name;
        private String server_fee_name;
        private String pledged_rate;
        private String closing_line_price;
        private String format_create_time;
        private String order_apply_status_name;
        private int server_fee_pay;
        private int order_loan_status;
        private List<ShxTradeInfoBean> trade_info;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_sequence_code() {
            return order_sequence_code;
        }

        public void setOrder_sequence_code(String order_sequence_code) {
            this.order_sequence_code = order_sequence_code;
        }

        public String getUser_wallet_type() {
            return user_wallet_type;
        }

        public void setUser_wallet_type(String user_wallet_type) {
            this.user_wallet_type = user_wallet_type;
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

        public String getLoan_organization_id() {
            return loan_organization_id;
        }

        public void setLoan_organization_id(String loan_organization_id) {
            this.loan_organization_id = loan_organization_id;
        }

        public String getLoan_organization_years_ratio() {
            return loan_organization_years_ratio;
        }

        public void setLoan_organization_years_ratio(String loan_organization_years_ratio) {
            this.loan_organization_years_ratio = loan_organization_years_ratio;
        }

        public String getLoan_coin_real_total() {
            return loan_coin_real_total;
        }

        public void setLoan_coin_real_total(String loan_coin_real_total) {
            this.loan_coin_real_total = loan_coin_real_total;
        }

        public String getCan_amount_limit() {
            return can_amount_limit;
        }

        public void setCan_amount_limit(String can_amount_limit) {
            this.can_amount_limit = can_amount_limit;
        }

        public String getNeed_amount_limit() {
            return need_amount_limit;
        }

        public void setNeed_amount_limit(String need_amount_limit) {
            this.need_amount_limit = need_amount_limit;
        }

        public Object getReal_loan_amount() {
            return real_loan_amount;
        }

        public void setReal_loan_amount(Object real_loan_amount) {
            this.real_loan_amount = real_loan_amount;
        }

        public String getOrder_apply_status() {
            return order_apply_status;
        }

        public void setOrder_apply_status(String order_apply_status) {
            this.order_apply_status = order_apply_status;
        }

        public String getCredit_apply_server_fee() {
            return credit_apply_server_fee;
        }

        public void setCredit_apply_server_fee(String credit_apply_server_fee) {
            this.credit_apply_server_fee = credit_apply_server_fee;
        }

        public String getCredit_apply_server_fee_unit() {
            return credit_apply_server_fee_unit;
        }

        public void setCredit_apply_server_fee_unit(String credit_apply_server_fee_unit) {
            this.credit_apply_server_fee_unit = credit_apply_server_fee_unit;
        }

        public String getServer_fee_pay_status() {
            return server_fee_pay_status;
        }

        public void setServer_fee_pay_status(String server_fee_pay_status) {
            this.server_fee_pay_status = server_fee_pay_status;
        }

        public String getLoan_status() {
            return loan_status;
        }

        public void setLoan_status(String loan_status) {
            this.loan_status = loan_status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getLoan_coin_name() {
            return loan_coin_name;
        }

        public void setLoan_coin_name(String loan_coin_name) {
            this.loan_coin_name = loan_coin_name;
        }

        public String getLoan_organization_name() {
            return loan_organization_name;
        }

        public void setLoan_organization_name(String loan_organization_name) {
            this.loan_organization_name = loan_organization_name;
        }

        public String getServer_fee_name() {
            return server_fee_name;
        }

        public void setServer_fee_name(String server_fee_name) {
            this.server_fee_name = server_fee_name;
        }

        public String getPledged_rate() {
            return pledged_rate;
        }

        public void setPledged_rate(String pledged_rate) {
            this.pledged_rate = pledged_rate;
        }

        public String getClosing_line_price() {
            return closing_line_price;
        }

        public void setClosing_line_price(String closing_line_price) {
            this.closing_line_price = closing_line_price;
        }

        public String getFormat_create_time() {
            return format_create_time;
        }

        public void setFormat_create_time(String format_create_time) {
            this.format_create_time = format_create_time;
        }

        public String getOrder_apply_status_name() {
            return order_apply_status_name;
        }

        public void setOrder_apply_status_name(String order_apply_status_name) {
            this.order_apply_status_name = order_apply_status_name;
        }

        public int getServer_fee_pay() {
            return server_fee_pay;
        }

        public void setServer_fee_pay(int server_fee_pay) {
            this.server_fee_pay = server_fee_pay;
        }

        public int getOrder_loan_status() {
            return order_loan_status;
        }

        public void setOrder_loan_status(int order_loan_status) {
            this.order_loan_status = order_loan_status;
        }

        public List<ShxTradeInfoBean> getTrade_info() {
            return trade_info;
        }

        public void setTrade_info(List<ShxTradeInfoBean> trade_info) {
            this.trade_info = trade_info;
        }

        public static class ShxTradeInfoBean {
            /**
             * title :
             * total : 0
             * trade_code :
             * trade_time :
             * trade_status_name :
             */
            private String chain_trade_code;
            private String title;
            private String total;
            private String trade_code;
            private String trade_time;
            private String trade_status_name;

            public String getChain_trade_code() {
                return chain_trade_code;
            }

            public void setChain_trade_code(String chain_trade_code) {
                this.chain_trade_code = chain_trade_code;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getTrade_code() {
                return trade_code;
            }

            public void setTrade_code(String trade_code) {
                this.trade_code = trade_code;
            }

            public String getTrade_time() {
                return trade_time;
            }

            public void setTrade_time(String trade_time) {
                this.trade_time = trade_time;
            }

            public String getTrade_status_name() {
                return trade_status_name;
            }

            public void setTrade_status_name(String trade_status_name) {
                this.trade_status_name = trade_status_name;
            }
        }

        public static class BcTradeInfoBean {
            /**
             * title :
             * total : 0
             * trade_code :
             * trade_time :
             * trade_status_name :
             */

            private String title;
            private String total;
            private String trade_code;
            private String trade_time;
            private String trade_status_name;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getTrade_code() {
                return trade_code;
            }

            public void setTrade_code(String trade_code) {
                this.trade_code = trade_code;
            }

            public String getTrade_time() {
                return trade_time;
            }

            public void setTrade_time(String trade_time) {
                this.trade_time = trade_time;
            }

            public String getTrade_status_name() {
                return trade_status_name;
            }

            public void setTrade_status_name(String trade_status_name) {
                this.trade_status_name = trade_status_name;
            }
        }
    }

    public static class CreditConfirmTipDataBean {
        /**
         * order_id : 467
         * wallet_contract_address : 0xd3811261320a36d7b49d540aa31d9b2f32afb9f4
         * loan_coin_total : 95.20000000
         * loan_coin_total_text : 请将95.20000000个ETH数量0.00000000个转账到智能合约钱包地址进行质押操作
         * select_agreement_tips : 阅读并同意签署《授信协议》
         * tip_info : 温馨提示
         * ,1：用户可实时通过智能合约钱包地址在区块链上查询质押品情况。
         * 2：请勿向上述地址充值任何非ETH资产，否则资产将不可找回。
         * 3：您完成操作后，需要整个网络节点的确认才能到帐。
         * 4：提币到合约地址可能会发生合约执行失败，将导致转账失败。资产将退回至用户原钱包地址。处理时间可能较长，请您谅解。
         * submit_text : 转币完成，创建授信函
         */

        private String order_id;
        private String wallet_contract_address;
        private String loan_coin_total;
        private String loan_coin_total_text;
        private String select_agreement_tips;
        private String tip_info;
        private String submit_text;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
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