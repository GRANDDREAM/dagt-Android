package com.grandream.dagt.bean.credit;

import java.util.List;

/**
 * Created by chenjing on 2018/5/7.
 */

public class CreditListData {

    /**
     * data : {"total_credit_loan_amount":"69549.7500","success_total_credit_loan_amount":"0.0000","format_loan_coin_info":[{"id":"1","digit_coin_name":"Ethereum","digit_coin_alias_name":"ETH","digit_coin_icon":null,"status":"1","digit_coin_name_ch":"以太坊","digit_coin_query_price_symbol":"etcusdt","create_time":"2018-04-24 16:48:56","current_coin_value":"质押ETH数量1.22801314个≈76246.0830元"},{"id":"2","digit_coin_name":"Bitcoin","digit_coin_alias_name":"BTC","digit_coin_icon":null,"status":"1","digit_coin_name_ch":"比特币","digit_coin_query_price_symbol":"btcusdt","create_time":"2018-04-24 16:49:02","current_coin_value":"质押BTC数量6.99461070个≈399930.4362元"}],"format_order_info":[{"order_sequence_code":"A_20180428155706187187","loan_coin_id":"1","loan_coin_real_total":"1.22801314","loan_organization_id":"1","need_amount_limit":"34000.0000","real_loan_amount":"7660.8000","order_apply_status":"10011","credit_apply_server_fee":"68000.0000","credit_apply_server_fee_unit":"USD","loan_status":"10022","application_time":"2018年04月28日","show_warning":1,"show_warning_text":"已达预警线","show_warning_submit_text":"追加质押品"},{"order_sequence_code":"A_20180428163420214562","loan_coin_id":"2","loan_coin_real_total":"6.99461070","loan_organization_id":"1","need_amount_limit":"61000.0000","real_loan_amount":"1888.9500","order_apply_status":"10011","credit_apply_server_fee":"122000.0000","credit_apply_server_fee_unit":"USD","loan_status":"10022","application_time":"2018年04月28日","show_warning":1,"show_warning_text":"已达预警线","show_warning_submit_text":"追加质押品"}]}
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
         * total_credit_loan_amount : 69549.7500
         * success_total_credit_loan_amount : 0.0000
         * format_loan_coin_info : [{"id":"1","digit_coin_name":"Ethereum","digit_coin_alias_name":"ETH","digit_coin_icon":null,"status":"1","digit_coin_name_ch":"以太坊","digit_coin_query_price_symbol":"etcusdt","create_time":"2018-04-24 16:48:56","current_coin_value":"质押ETH数量1.22801314个≈76246.0830元"},{"id":"2","digit_coin_name":"Bitcoin","digit_coin_alias_name":"BTC","digit_coin_icon":null,"status":"1","digit_coin_name_ch":"比特币","digit_coin_query_price_symbol":"btcusdt","create_time":"2018-04-24 16:49:02","current_coin_value":"质押BTC数量6.99461070个≈399930.4362元"}]
         * format_order_info : [{"order_sequence_code":"A_20180428155706187187","loan_coin_id":"1","loan_coin_real_total":"1.22801314","loan_organization_id":"1","need_amount_limit":"34000.0000","real_loan_amount":"7660.8000","order_apply_status":"10011","credit_apply_server_fee":"68000.0000","credit_apply_server_fee_unit":"USD","loan_status":"10022","application_time":"2018年04月28日","show_warning":1,"show_warning_text":"已达预警线","show_warning_submit_text":"追加质押品"},{"order_sequence_code":"A_20180428163420214562","loan_coin_id":"2","loan_coin_real_total":"6.99461070","loan_organization_id":"1","need_amount_limit":"61000.0000","real_loan_amount":"1888.9500","order_apply_status":"10011","credit_apply_server_fee":"122000.0000","credit_apply_server_fee_unit":"USD","loan_status":"10022","application_time":"2018年04月28日","show_warning":1,"show_warning_text":"已达预警线","show_warning_submit_text":"追加质押品"}]
         */

        private String total_credit_loan_amount;
        private String success_total_credit_loan_amount;
        private int unprocess_order_info;
        private List<FormatLoanCoinInfoBean> format_loan_coin_info;
        private List<FormatOrderInfoBean> format_order_info;

        public int getUnprocess_order_info() {
            return unprocess_order_info;
        }

        public void setUnprocess_order_info(int unprocess_order_info) {
            this.unprocess_order_info = unprocess_order_info;
        }

        public String getTotal_credit_loan_amount() {
            return total_credit_loan_amount;
        }

        public void setTotal_credit_loan_amount(String total_credit_loan_amount) {
            this.total_credit_loan_amount = total_credit_loan_amount;
        }

        public String getSuccess_total_credit_loan_amount() {
            return success_total_credit_loan_amount;
        }

        public void setSuccess_total_credit_loan_amount(String success_total_credit_loan_amount) {
            this.success_total_credit_loan_amount = success_total_credit_loan_amount;
        }

        public List<FormatLoanCoinInfoBean> getFormat_loan_coin_info() {
            return format_loan_coin_info;
        }

        public void setFormat_loan_coin_info(List<FormatLoanCoinInfoBean> format_loan_coin_info) {
            this.format_loan_coin_info = format_loan_coin_info;
        }

        public List<FormatOrderInfoBean> getFormat_order_info() {
            return format_order_info;
        }

        public void setFormat_order_info(List<FormatOrderInfoBean> format_order_info) {
            this.format_order_info = format_order_info;
        }

        public static class FormatLoanCoinInfoBean {
            /**
             * id : 1
             * digit_coin_name : Ethereum
             * digit_coin_alias_name : ETH
             * digit_coin_icon : null
             * status : 1
             * digit_coin_name_ch : 以太坊
             * digit_coin_query_price_symbol : etcusdt
             * create_time : 2018-04-24 16:48:56
             * current_coin_value : 质押ETH数量1.22801314个≈76246.0830元
             */

            private String id;
            private String digit_coin_name;
            private String digit_coin_alias_name;
            private String digit_coin_icon;
            private String status;
            private String digit_coin_name_ch;
            private String digit_coin_query_price_symbol;
            private String create_time;
            private String current_coin_value;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDigit_coin_name() {
                return digit_coin_name;
            }

            public void setDigit_coin_name(String digit_coin_name) {
                this.digit_coin_name = digit_coin_name;
            }

            public String getDigit_coin_alias_name() {
                return digit_coin_alias_name;
            }

            public void setDigit_coin_alias_name(String digit_coin_alias_name) {
                this.digit_coin_alias_name = digit_coin_alias_name;
            }

            public String getDigit_coin_icon() {
                return digit_coin_icon;
            }

            public void setDigit_coin_icon(String digit_coin_icon) {
                this.digit_coin_icon = digit_coin_icon;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDigit_coin_name_ch() {
                return digit_coin_name_ch;
            }

            public void setDigit_coin_name_ch(String digit_coin_name_ch) {
                this.digit_coin_name_ch = digit_coin_name_ch;
            }

            public String getDigit_coin_query_price_symbol() {
                return digit_coin_query_price_symbol;
            }

            public void setDigit_coin_query_price_symbol(String digit_coin_query_price_symbol) {
                this.digit_coin_query_price_symbol = digit_coin_query_price_symbol;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getCurrent_coin_value() {
                return current_coin_value;
            }

            public void setCurrent_coin_value(String current_coin_value) {
                this.current_coin_value = current_coin_value;
            }
        }

        public static class FormatOrderInfoBean {
            /**
             * order_sequence_code : A_20180428155706187187
             * loan_coin_id : 1
             * loan_coin_real_total : 1.22801314
             * loan_organization_id : 1
             * need_amount_limit : 34000.0000
             * real_loan_amount : 7660.8000
             * order_apply_status : 10011
             * credit_apply_server_fee : 68000.0000
             * credit_apply_server_fee_unit : USD
             * loan_status : 10022
             * application_time : 2018年04月28日
             * show_warning : 1
             * show_warning_text : 已达预警线
             * show_warning_submit_text : 追加质押品
             */

            private String order_sequence_code;
            private String loan_coin_id;
            private String loan_coin_real_total;
            private String loan_organization_id;
            private String need_amount_limit;
            private String real_loan_amount;
            private String order_apply_status;
            private String credit_apply_server_fee;
            private String credit_apply_server_fee_unit;
            private String loan_status;
            private String application_time;
            private int show_warning;
            private String show_warning_text;
            private String show_warning_submit_text;

            public String getOrder_sequence_code() {
                return order_sequence_code;
            }

            public void setOrder_sequence_code(String order_sequence_code) {
                this.order_sequence_code = order_sequence_code;
            }

            public String getLoan_coin_id() {
                return loan_coin_id;
            }

            public void setLoan_coin_id(String loan_coin_id) {
                this.loan_coin_id = loan_coin_id;
            }

            public String getLoan_coin_real_total() {
                return loan_coin_real_total;
            }

            public void setLoan_coin_real_total(String loan_coin_real_total) {
                this.loan_coin_real_total = loan_coin_real_total;
            }

            public String getLoan_organization_id() {
                return loan_organization_id;
            }

            public void setLoan_organization_id(String loan_organization_id) {
                this.loan_organization_id = loan_organization_id;
            }

            public String getNeed_amount_limit() {
                return need_amount_limit;
            }

            public void setNeed_amount_limit(String need_amount_limit) {
                this.need_amount_limit = need_amount_limit;
            }

            public String getReal_loan_amount() {
                return real_loan_amount;
            }

            public void setReal_loan_amount(String real_loan_amount) {
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

            public String getLoan_status() {
                return loan_status;
            }

            public void setLoan_status(String loan_status) {
                this.loan_status = loan_status;
            }

            public String getApplication_time() {
                return application_time;
            }

            public void setApplication_time(String application_time) {
                this.application_time = application_time;
            }

            public int getShow_warning() {
                return show_warning;
            }

            public void setShow_warning(int show_warning) {
                this.show_warning = show_warning;
            }

            public String getShow_warning_text() {
                return show_warning_text;
            }

            public void setShow_warning_text(String show_warning_text) {
                this.show_warning_text = show_warning_text;
            }

            public String getShow_warning_submit_text() {
                return show_warning_submit_text;
            }

            public void setShow_warning_submit_text(String show_warning_submit_text) {
                this.show_warning_submit_text = show_warning_submit_text;
            }
        }
    }
}
