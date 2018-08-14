package com.grandream.dagt.bean.my;

import java.util.List;

/**
 * Created by chenjing on 2018/4/17.
 */

public class LenInstiutionBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * org_name : 中国好奇银行
         * org_alias_name : 好奇银行
         * org_icon : null
         * org_abstract : null
         * org_app_name : null
         * org_app_download_url : null
         * org_website : null
         */

        private String org_name;
        private String org_alias_name;
        private String org_icon;
        private String org_abstract;
        private String org_app_name;
        private String org_app_download_url;
        private String org_website;

        public String getOrg_name() {
            return org_name;
        }

        public void setOrg_name(String org_name) {
            this.org_name = org_name;
        }

        public String getOrg_alias_name() {
            return org_alias_name;
        }

        public void setOrg_alias_name(String org_alias_name) {
            this.org_alias_name = org_alias_name;
        }

        public String getOrg_icon() {
            return org_icon;
        }

        public void setOrg_icon(String org_icon) {
            this.org_icon = org_icon;
        }

        public String getOrg_abstract() {
            return org_abstract;
        }

        public void setOrg_abstract(String org_abstract) {
            this.org_abstract = org_abstract;
        }

        public String getOrg_app_name() {
            return org_app_name;
        }

        public void setOrg_app_name(String org_app_name) {
            this.org_app_name = org_app_name;
        }

        public String getOrg_app_download_url() {
            return org_app_download_url;
        }

        public void setOrg_app_download_url(String org_app_download_url) {
            this.org_app_download_url = org_app_download_url;
        }

        public String getOrg_website() {
            return org_website;
        }

        public void setOrg_website(String org_website) {
            this.org_website = org_website;
        }
    }
}
