package com.grandream.dagt.requestbody.home;

/**
 * Created by chenjing on 2018/4/16.
 */

public class GetMsgDetail {
    private String user_id;
    private String msg_id;
    private String msg_type_id;
    private String read_status;

    public GetMsgDetail(String user_id, String msg_id, String msg_type_id, String read_status) {
        this.user_id = user_id;
        this.msg_id = msg_id;
        this.msg_type_id = msg_type_id;
        this.read_status = read_status;
    }


    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_type_id() {
        return msg_type_id;
    }

    public void setMsg_type_id(String msg_type_id) {
        this.msg_type_id = msg_type_id;
    }

    public String getRead_status() {
        return read_status;
    }

    public void setRead_status(String read_status) {
        this.read_status = read_status;
    }
}
