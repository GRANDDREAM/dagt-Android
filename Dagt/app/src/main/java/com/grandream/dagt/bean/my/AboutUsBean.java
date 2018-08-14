package com.grandream.dagt.bean.my;

import java.util.List;

/**
 * Created by chenjing on 2018/4/17.
 */

public class AboutUsBean {


    /**
     * id : 1
     * logo : null
     * address : 中国最大地区
     * version : 0.0.1
     * website : www.dagt.io
     * bd_email : dd@11.com
     */

    private String id;
    private Object logo;
    private String address;
    private String version;
    private String website;
    private String bd_email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getLogo() {
        return logo;
    }

    public void setLogo(Object logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBd_email() {
        return bd_email;
    }

    public void setBd_email(String bd_email) {
        this.bd_email = bd_email;
    }
}
