package com.grandream.dagt.bean.home.msg;

/**
 * Created by chenjing on 2018/4/16.
 */

public class MsgDeatilBean {

    /**
     * data : {"id":"2","msg_title":"haoqi ceshi 2","msg_content":"haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2","create_time":"2018-04-04 12:45:27"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * msg_title : haoqi ceshi 2
         * msg_content : haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2haoqi ceshi 2
         * create_time : 2018-04-04 12:45:27
         */

        private String id;
        private String msg_title;
        private String msg_content;
        private String create_time;

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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
