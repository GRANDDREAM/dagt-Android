package com.grandream.dagt.bean.login;

import com.grandream.dagt.http.model.HttpResult;

/**
 * 国家手机区号
 * Created by chenjing on 2018/4/11.
 */

public class CountryCode {

    private String country_code;

    public String getCountry_code() {
        return "(" + country_code + ")";
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
}
