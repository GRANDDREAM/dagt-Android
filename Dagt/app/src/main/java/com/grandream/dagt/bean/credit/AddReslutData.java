package com.grandream.dagt.bean.credit;

import java.io.Serializable;

/**
 * Created by chenjing on 2018/5/9.
 */

public class AddReslutData implements Serializable {

    /**
     * success : 0
     * next_step_info : {"tip_title":"","tip_content":"","submit_text":""}
     */

    private int success;
    private NextStepInfoBean next_step_info;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public NextStepInfoBean getNext_step_info() {
        return next_step_info;
    }

    public void setNext_step_info(NextStepInfoBean next_step_info) {
        this.next_step_info = next_step_info;
    }

    public static class NextStepInfoBean implements Serializable {
        /**
         * tip_title :
         * tip_content :
         * submit_text :
         */

        private String tip_title;
        private String tip_content;
        private String submit_text;

        public String getTip_title() {
            return tip_title;
        }

        public void setTip_title(String tip_title) {
            this.tip_title = tip_title;
        }

        public String getTip_content() {
            return tip_content;
        }

        public void setTip_content(String tip_content) {
            this.tip_content = tip_content;
        }

        public String getSubmit_text() {
            return submit_text;
        }

        public void setSubmit_text(String submit_text) {
            this.submit_text = submit_text;
        }
    }
}
