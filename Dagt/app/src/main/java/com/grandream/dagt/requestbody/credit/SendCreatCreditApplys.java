package com.grandream.dagt.requestbody.credit;

/**
 * Created by chenjing on 2018/4/24.
 */

public class SendCreatCreditApplys {
    private String user_id;
    private String user_wallet_type;
    private String user_wallet_address;
    private String loan_coin_id;
    private String loan_organization_id;
    private String loan_organization_years_ratio;
    private String can_amount_limit;
    private String need_amount_limit;
    private String credit_apply_server_fee;
    private String credit_apply_server_fee_unit;

    public SendCreatCreditApplys() {
    }

    public SendCreatCreditApplys(String user_id, String user_wallet_type, String user_wallet_address, String loan_coin_id, String loan_organization_id, String loan_organization_years_ratio, String can_amount_limit, String need_amount_limit, String credit_apply_server_fee, String credit_apply_server_fee_unit) {
        this.user_id = user_id;
        this.user_wallet_type = user_wallet_type;
        this.user_wallet_address = user_wallet_address;
        this.loan_coin_id = loan_coin_id;
        this.loan_organization_id = loan_organization_id;
        this.loan_organization_years_ratio = loan_organization_years_ratio;
        this.can_amount_limit = can_amount_limit;
        this.need_amount_limit = need_amount_limit;
        this.credit_apply_server_fee = credit_apply_server_fee;
        this.credit_apply_server_fee_unit = credit_apply_server_fee_unit;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
}
