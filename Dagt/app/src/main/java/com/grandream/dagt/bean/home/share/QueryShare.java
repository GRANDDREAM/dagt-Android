package com.grandream.dagt.bean.home.share;

import java.util.List;

/**
 * Created by chenjing on 2018/7/4.
 */

public class QueryShare {

    /**
     * recommend_count : 1
     * recommend_sum : 20
     * data : [{"id":"1","recommended_user_tid":"58","recommend_reward_info":"20","user_name":"17199887722","avatar":null}]
     */

    private String recommend_count;
    private String recommend_sum;
    private List<DataBean> data;

    public String getRecommend_count() {
        return recommend_count;
    }

    public void setRecommend_count(String recommend_count) {
        this.recommend_count = recommend_count;
    }

    public String getRecommend_sum() {
        return recommend_sum;
    }

    public void setRecommend_sum(String recommend_sum) {
        this.recommend_sum = recommend_sum;
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
         * recommended_user_tid : 58
         * recommend_reward_info : 20
         * user_name : 17199887722
         * avatar : null
         */

        private String id;
        private String recommended_user_tid;
        private String recommend_reward_info;
        private String user_name;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRecommended_user_tid() {
            return recommended_user_tid;
        }

        public void setRecommended_user_tid(String recommended_user_tid) {
            this.recommended_user_tid = recommended_user_tid;
        }

        public String getRecommend_reward_info() {
            return recommend_reward_info;
        }

        public void setRecommend_reward_info(String recommend_reward_info) {
            this.recommend_reward_info = recommend_reward_info;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
