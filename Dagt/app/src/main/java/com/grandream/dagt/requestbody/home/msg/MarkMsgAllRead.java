package com.grandream.dagt.requestbody.home.msg;

import com.grandream.dagt.bean.home.msg.MsgList;

import java.util.List;

/**
 * Created by chenjing on 2018/4/16.
 */

public class MarkMsgAllRead {
    private String user_id;
    private List<MsgList.MsgListData> user_msg_id;

    public MarkMsgAllRead(String user_id, List<MsgList.MsgListData> user_msg_id) {
        this.user_id = user_id;
        this.user_msg_id = user_msg_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<MsgList.MsgListData> getUser_msg_id() {
        return user_msg_id;
    }

    public void setUser_msg_id(List<MsgList.MsgListData> user_msg_id) {
        this.user_msg_id = user_msg_id;
    }
}
