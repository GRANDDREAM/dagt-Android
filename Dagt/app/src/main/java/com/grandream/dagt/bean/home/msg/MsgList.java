package com.grandream.dagt.bean.home.msg;

import java.util.List;

/**
 * Created by chenjing on 2018/4/16.
 */

public class MsgList {

    /**
     * total : 2
     * data : [{"read":"0","user_id":"0","type":"10000","create_time":"2018-04-04 12:43:51","id":"2","msg_title":"haoqi ceshi 2","msg_content":"haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2"},{"read":"0","user_id":"0","type":"10000","create_time":"2018-04-04 12:42:09","id":"1","msg_title":"haoqi ceshi 1","msg_content":"haoqi ceshi 1haoqi ceshi 1haoqi ceshi 1haoqi ceshi 1haoqi ceshi 1haoqi ceshi 1haoqi ceshi 1"}]
     */

    private int total;
    private List<MsgListData> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MsgListData> getData() {
        return data;
    }

    public void setData(List<MsgListData> data) {
        this.data = data;
    }

    public static class MsgListData {

        /**
         * read : 1
         * user_tid : 5
         * msg_tid : 2
         * msg_type_tid : 10000
         * create_time : 2018-04-16 12:54:47
         * id : 2
         * msg_title : haoqi ceshi 2
         * msg_content : haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2
         */

        private String read;
        private String user_tid;
        private String msg_tid;
        private String msg_type_tid;
        private String create_time;
        private String id;
        private String msg_title;
        private String msg_content;

        public String getRead() {
            return read;
        }

        public void setRead(String read) {
            this.read = read;
        }

        public String getUser_tid() {
            return user_tid;
        }

        public void setUser_tid(String user_tid) {
            this.user_tid = user_tid;
        }

        public String getMsg_tid() {
            return msg_tid;
        }

        public void setMsg_tid(String msg_tid) {
            this.msg_tid = msg_tid;
        }

        public String getMsg_type_tid() {
            return msg_type_tid;
        }

        public void setMsg_type_tid(String msg_type_tid) {
            this.msg_type_tid = msg_type_tid;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMsg_title() {
            return msg_title;
        }

        public void setMsg_title(String msg_title) {
            this.msg_title = msg_title;
        }

        public String getMsg_content() {
            return msg_content;
        }

        public void setMsg_content(String msg_content) {
            this.msg_content = msg_content;
        }
    }
}
