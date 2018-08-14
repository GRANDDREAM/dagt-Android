package com.grandream.dagt.requestbody.my;

/**
 * Created by chenjing on 2018/5/24.
 */

public class WrningBody {
    private String user_id;
    private int early_warn_type;
    private String early_warn_telephone;
    private String early_warn_email;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getEarly_warn_type() {
        return early_warn_type;
    }

    public void setEarly_warn_type(int early_warn_type) {
        this.early_warn_type = early_warn_type;
    }

    public String getEarly_warn_telephone() {
        return early_warn_telephone;
    }

    public void setEarly_warn_telephone(String early_warn_telephone) {
        this.early_warn_telephone = early_warn_telephone;
    }

    public String getEarly_warn_email() {
        return early_warn_email;
    }

    public void setEarly_warn_email(String early_warn_email) {
        this.early_warn_email = early_warn_email;
    }
}
