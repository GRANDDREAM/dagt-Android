package com.grandream.dagt.bean.credit;

/**
 * Created by chenjing on 2018/4/28.
 */

public class LockContractReslut {
    /**
     * success : 3
     * data : {"user_real_damage_amount":0,"current_loan_coin_total":0}
     * warnning : 0
     * shwo_detail_submit_text : 查看详情
     * left_submit_text
     * right_submit_text
     */

    private int success;
    private DataBean data;
    private int warnning;
    private String shwo_detail_submit_text;
    private String left_submit_text,
            right_submit_text;
    private NextStepInfoBean next_step_info;

    public NextStepInfoBean getNext_step_info() {
        return next_step_info;
    }

    public void setNext_step_info(NextStepInfoBean next_step_info) {
        this.next_step_info = next_step_info;
    }

    public String getLeft_submit_text() {
        return left_submit_text;
    }

    public void setLeft_submit_text(String left_submit_text) {
        this.left_submit_text = left_submit_text;
    }

    public String getRight_submit_text() {
        return right_submit_text;
    }

    public void setRight_submit_text(String right_submit_text) {
        this.right_submit_text = right_submit_text;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getWarnning() {
        return warnning;
    }

    public void setWarnning(int warnning) {
        this.warnning = warnning;
    }

    public String getShwo_detail_submit_text() {
        return shwo_detail_submit_text;
    }

    public void setShwo_detail_submit_text(String shwo_detail_submit_text) {
        this.shwo_detail_submit_text = shwo_detail_submit_text;
    }

    public static class DataBean {
        /**
         * user_real_damage_amount : 0
         * current_loan_coin_total : 0
         */

        private String user_real_damage_amount;
        private int current_loan_coin_total;

        public String getUser_real_damage_amount() {
            return user_real_damage_amount;
        }

        public void setUser_real_damage_amount(String user_real_damage_amount) {
            this.user_real_damage_amount = user_real_damage_amount;
        }

        public int getCurrent_loan_coin_total() {
            return current_loan_coin_total;
        }

        public void setCurrent_loan_coin_total(int current_loan_coin_total) {
            this.current_loan_coin_total = current_loan_coin_total;
        }
    }

//    /**
//     * success : 0
//     * data : {"user_real_damage_amount":0,"current_loan_coin_total":0}
//     * warnning : 1
//     */
//
//    private int success;
//    private DataBean data;
//    private int warnning;
//
//    public int getSuccess() {
//        return success;
//    }
//
//    public void setSuccess(int success) {
//        this.success = success;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public int getWarnning() {
//        return warnning;
//    }
//
//    public void setWarnning(int warnning) {
//        this.warnning = warnning;
//    }
//
//    public static class DataBean {
//        /**
//         * user_real_damage_amount : 0
//         * current_loan_coin_total : 0
//         */
//
//        private int user_real_damage_amount;
//        private int current_loan_coin_total;
//
//        public int getUser_real_damage_amount() {
//            return user_real_damage_amount;
//        }
//
//        public void setUser_real_damage_amount(int user_real_damage_amount) {
//            this.user_real_damage_amount = user_real_damage_amount;
//        }
//
//        public int getCurrent_loan_coin_total() {
//            return current_loan_coin_total;
//        }
//
//        public void setCurrent_loan_coin_total(int current_loan_coin_total) {
//            this.current_loan_coin_total = current_loan_coin_total;
//        }
//    }


}
