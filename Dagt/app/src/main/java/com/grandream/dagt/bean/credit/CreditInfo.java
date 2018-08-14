package com.grandream.dagt.bean.credit;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenjing on 2018/4/23.
 */

public class CreditInfo implements Serializable {
    /**
     * data : {"address":"0xd3811261320a36d7b49d540aa31d9b2f32afb9f4","token":"1","wallet_type":"person","wallet_address":"0xd3811261320a36d7b49d540aa31d9b2f32afb9f4","loan_coin_id":"1","user_total_money":"42526","step_amount_limit":1000,"min_amount_limit":0,"can_amount_limit":1701,"need_amount_limit":"1000","loan_organization":[{"id":"1","org_name":"中国好奇银行","company_name":"好奇银行","org_icon":null,"org_years_ratio":"10.00","format_org_years_ratio":"10.00%","org_code":"01002","org_abstract":"","org_app_name":"","org_app_download_url":"","org_website":"","loan_limit_min":"1.00","loan_limit_max":"100000.00","loan_limit_unit":"元","pledge_rate":"a:2:{i:1;s:1:\"4\";i:2;s:1:\"5\";}","status":"1","create_time":"2018-04-14 17:06:05","can_amount_limit":1701,"need_amount_limit":"1000"},{"id":"2","org_name":"中国好奇2银行","company_name":"2银行","org_icon":null,"org_years_ratio":"11.00","format_org_years_ratio":"11.00%","org_code":"02001","org_abstract":null,"org_app_name":null,"org_app_download_url":null,"org_website":null,"loan_limit_min":"100000.00","loan_limit_max":"150000.00","loan_limit_unit":"元","pledge_rate":"a:2:{i:1;s:1:\"4\";i:2;s:1:\"5\";}","status":"1","create_time":"2018-04-14 17:06:37","can_amount_limit":1701,"need_amount_limit":"1000"},{"id":"4","org_name":"企额贷","company_name":"上海相城金融","org_icon":"","org_years_ratio":"11.00","format_org_years_ratio":"11.00%","org_code":"SH1804261688","org_abstract":"上海想成是DDDDDDDDDDDDDDf11","org_app_name":"企鹅贷APP","org_app_download_url":"http://www.baidu.com","org_website":"http://www.google.com","loan_limit_min":"100000.00","loan_limit_max":"150000.00","loan_limit_unit":"元","pledge_rate":"a:2:{i:1;s:1:\"4\";i:2;s:1:\"5\";}","status":"1","create_time":"2018-04-26 16:40:52","can_amount_limit":1701,"need_amount_limit":"1000"}],"credit_apply_server_base_fee":"25.41","credit_apply_server_fee":"25000","credit_apply_server_fee_unit":"USD"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * address : 0xd3811261320a36d7b49d540aa31d9b2f32afb9f4
         * token : 1
         * wallet_type : person
         * wallet_address : 0xd3811261320a36d7b49d540aa31d9b2f32afb9f4
         * loan_coin_id : 1
         * user_total_money : 42526
         * step_amount_limit : 1000
         * min_amount_limit : 0
         * can_amount_limit : 1701
         * need_amount_limit : 1000
         * loan_organization : [{"id":"1","org_name":"中国好奇银行","company_name":"好奇银行","org_icon":null,"org_years_ratio":"10.00","format_org_years_ratio":"10.00%","org_code":"01002","org_abstract":"","org_app_name":"","org_app_download_url":"","org_website":"","loan_limit_min":"1.00","loan_limit_max":"100000.00","loan_limit_unit":"元","pledge_rate":"a:2:{i:1;s:1:\"4\";i:2;s:1:\"5\";}","status":"1","create_time":"2018-04-14 17:06:05","can_amount_limit":1701,"need_amount_limit":"1000"},{"id":"2","org_name":"中国好奇2银行","company_name":"2银行","org_icon":null,"org_years_ratio":"11.00","format_org_years_ratio":"11.00%","org_code":"02001","org_abstract":null,"org_app_name":null,"org_app_download_url":null,"org_website":null,"loan_limit_min":"100000.00","loan_limit_max":"150000.00","loan_limit_unit":"元","pledge_rate":"a:2:{i:1;s:1:\"4\";i:2;s:1:\"5\";}","status":"1","create_time":"2018-04-14 17:06:37","can_amount_limit":1701,"need_amount_limit":"1000"},{"id":"4","org_name":"企额贷","company_name":"上海相城金融","org_icon":"","org_years_ratio":"11.00","format_org_years_ratio":"11.00%","org_code":"SH1804261688","org_abstract":"上海想成是DDDDDDDDDDDDDDf11","org_app_name":"企鹅贷APP","org_app_download_url":"http://www.baidu.com","org_website":"http://www.google.com","loan_limit_min":"100000.00","loan_limit_max":"150000.00","loan_limit_unit":"元","pledge_rate":"a:2:{i:1;s:1:\"4\";i:2;s:1:\"5\";}","status":"1","create_time":"2018-04-26 16:40:52","can_amount_limit":1701,"need_amount_limit":"1000"}]
         * credit_apply_server_base_fee : 25.41
         * credit_apply_server_fee : 25000
         * credit_apply_server_fee_unit : USD
         */

        private String address;
        private String token;
        private String wallet_type;
        private String wallet_address;
        private String loan_coin_id;
        private String user_total_money;
        private String step_amount_limit;
        private String min_amount_limit;
        private String can_amount_limit;
        private String need_amount_limit;
        private String credit_apply_server_base_fee;
        private String credit_apply_server_fee;
        private String credit_apply_server_fee_unit;
        private List<LoanOrganizationBean> loan_organization;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getWallet_type() {
            return wallet_type;
        }

        public void setWallet_type(String wallet_type) {
            this.wallet_type = wallet_type;
        }

        public String getWallet_address() {
            return wallet_address;
        }

        public void setWallet_address(String wallet_address) {
            this.wallet_address = wallet_address;
        }

        public String getLoan_coin_id() {
            return loan_coin_id;
        }

        public void setLoan_coin_id(String loan_coin_id) {
            this.loan_coin_id = loan_coin_id;
        }

        public String getUser_total_money() {
            return user_total_money;
        }

        public void setUser_total_money(String user_total_money) {
            this.user_total_money = user_total_money;
        }

        public String getStep_amount_limit() {
            return step_amount_limit;
        }

        public void setStep_amount_limit(String step_amount_limit) {
            this.step_amount_limit = step_amount_limit;
        }

        public String getMin_amount_limit() {
            return min_amount_limit;
        }

        public void setMin_amount_limit(String min_amount_limit) {
            this.min_amount_limit = min_amount_limit;
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

        public String getCredit_apply_server_base_fee() {
            return credit_apply_server_base_fee;
        }

        public void setCredit_apply_server_base_fee(String credit_apply_server_base_fee) {
            this.credit_apply_server_base_fee = credit_apply_server_base_fee;
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

        public List<LoanOrganizationBean> getLoan_organization() {
            return loan_organization;
        }

        public void setLoan_organization(List<LoanOrganizationBean> loan_organization) {
            this.loan_organization = loan_organization;
        }

        public static class LoanOrganizationBean implements Serializable {
            /**
             * id : 1
             * org_name : 中国好奇银行
             * company_name : 好奇银行
             * org_icon : null
             * org_years_ratio : 10.00
             * format_org_years_ratio : 10.00%
             * org_code : 01002
             * org_abstract :
             * org_app_name :
             * org_app_download_url :
             * org_website :
             * loan_limit_min : 1.00
             * loan_limit_max : 100000.00
             * loan_limit_unit : 元
             * pledge_rate : a:2:{i:1;s:1:"4";i:2;s:1:"5";}
             * status : 1
             * create_time : 2018-04-14 17:06:05
             * can_amount_limit : 1701
             * need_amount_limit : 1000
             */

            private String id;
            private String org_name;
            private String company_name;
            private String org_icon;
            private String org_years_ratio;
            private String format_org_years_ratio;
            private String org_code;
            private String org_abstract;
            private String org_app_name;
            private String org_app_download_url;
            private String org_website;
            private String loan_limit_min;
            private String loan_limit_max;
            private String loan_limit_unit;
            private String pledge_rate;
            private String status;
            private String create_time;
            private String can_amount_limit;
            private String need_amount_limit;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrg_name() {
                return org_name;
            }

            public void setOrg_name(String org_name) {
                this.org_name = org_name;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getOrg_icon() {
                return org_icon;
            }

            public void setOrg_icon(String org_icon) {
                this.org_icon = org_icon;
            }

            public String getOrg_years_ratio() {
                return org_years_ratio;
            }

            public void setOrg_years_ratio(String org_years_ratio) {
                this.org_years_ratio = org_years_ratio;
            }

            public String getFormat_org_years_ratio() {
                return format_org_years_ratio;
            }

            public void setFormat_org_years_ratio(String format_org_years_ratio) {
                this.format_org_years_ratio = format_org_years_ratio;
            }

            public String getOrg_code() {
                return org_code;
            }

            public void setOrg_code(String org_code) {
                this.org_code = org_code;
            }

            public String getOrg_abstract() {
                return org_abstract;
            }

            public void setOrg_abstract(String org_abstract) {
                this.org_abstract = org_abstract;
            }

            public String getOrg_app_name() {
                return org_app_name;
            }

            public void setOrg_app_name(String org_app_name) {
                this.org_app_name = org_app_name;
            }

            public String getOrg_app_download_url() {
                return org_app_download_url;
            }

            public void setOrg_app_download_url(String org_app_download_url) {
                this.org_app_download_url = org_app_download_url;
            }

            public String getOrg_website() {
                return org_website;
            }

            public void setOrg_website(String org_website) {
                this.org_website = org_website;
            }

            public String getLoan_limit_min() {
                return loan_limit_min;
            }

            public void setLoan_limit_min(String loan_limit_min) {
                this.loan_limit_min = loan_limit_min;
            }

            public String getLoan_limit_max() {
                return loan_limit_max;
            }

            public void setLoan_limit_max(String loan_limit_max) {
                this.loan_limit_max = loan_limit_max;
            }

            public String getLoan_limit_unit() {
                return loan_limit_unit;
            }

            public void setLoan_limit_unit(String loan_limit_unit) {
                this.loan_limit_unit = loan_limit_unit;
            }

            public String getPledge_rate() {
                return pledge_rate;
            }

            public void setPledge_rate(String pledge_rate) {
                this.pledge_rate = pledge_rate;
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
        }
    }
}
