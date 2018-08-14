package com.grandream.dagt.bean.credit;

import java.io.Serializable;

/**
 * Created by chenjing on 2018/5/9.
 */

public class SerciceFeeReslut implements Serializable {

    /**
     * success : 1
     * next_step_info : {"tip_title":"支付处理中","tip_content":"请稍作等待\u2026\u2026"}
     */

    private int success;
    private int exists;

    public boolean getExists() {
        if (exists == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setExists(int exists) {
        this.exists = exists;
    }

    private NextStepInfoBean next_step_info;

    public boolean getSuccess() {
        if (success == 1) {
            return true;
        } else {
            return false;
        }
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
         * tip_title : 支付处理中
         * tip_content : 请稍作等待……
         */

        private String tip_title;
        private String tip_content;

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
    }
}
