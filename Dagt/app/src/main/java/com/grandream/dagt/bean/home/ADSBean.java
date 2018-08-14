package com.grandream.dagt.bean.home;

import java.util.List;

/**
 * Created by chenjing on 2018/5/23.
 */

public class ADSBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * type : banner
         * content : http://testapi.51kjq.com/uploads/user_logo/1526534829400.jpg
         * url : 34324234
         * create_time : 2018-04-20 16:50:35
         */

        private String id;
        private String type;
        private String content;
        private String url;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
