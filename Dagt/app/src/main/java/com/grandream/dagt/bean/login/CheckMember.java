package com.grandream.dagt.bean.login;

/**
 * 检查手机号是否是注册手机号
 * Created by chenjing on 2018/4/12.
 */

public class CheckMember {
    /**
     * 是否存在，0为不存在，1为存在
     */
    private int exists;
    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getExists() {
        return exists;
    }

    public void setExists(int exists) {
        this.exists = exists;
    }
}
