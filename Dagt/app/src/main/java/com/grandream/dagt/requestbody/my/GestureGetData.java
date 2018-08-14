package com.grandream.dagt.requestbody.my;

/**
 * Created by chenjing on 2018/5/28.
 */

public class GestureGetData {
    private String user_id;
    private String user_name;
    private int guest_password_switch;

    public GestureGetData(String user_id, String user_name, int guest_password_switch) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.guest_password_switch = guest_password_switch;
    }


}
