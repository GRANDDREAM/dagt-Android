package com.grandream.dagt.bean.my;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chenjing on 2018/5/23.
 */

public class CommProBean {

    /**
     * count : 1
     * data : [{"id":"1","name":"测试问题1","abstract":"测试测试测试","content":"这是一个测试问题1","create_time":"2018-04-16 10:43:18"},{"id":"2","name":"测试问题2","abstract":"测试问题2测试问题2","content":"测试问题2测试问题2测试问题2测试问题2测试问题2测试问题2测试问题2","create_time":"2018-04-16 10:43:35"}]
     */

    private String count;
    private List<DataBean> data;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 测试问题1
         * abstract : 测试测试测试
         * content : 这是一个测试问题1
         * create_time : 2018-04-16 10:43:18
         */

        private String id;
        private String name;
        @SerializedName("abstract")
        private String abstractX;
        private String content;
        private String create_time;
        private int show;

        public boolean getShow() {
            return show == 1;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
