package com.grandream.dagt.bean.credit;

import java.io.Serializable;

/**
 * Created by chenjing on 2018/4/28.
 */

public class AgainReslut implements Serializable {

    /**
     * update : 1
     * next_step_info : {"tip_title":"贷款授信函创建成功","tip_content":"请尽快前往APP应用市场下载【中国好奇银行】APP完成贷款操作，以免授信函过期失效。","warn_tip":"质押风险提示\n1、系统将监测所有质押账户中数字资产的变化情况，计算数字资产发生变化的质押率。当质押率大于等于70%时第一次触发预警，我们将进行短信、APP消息提醒，请前往【我的】-【系统设置】-【平仓预警设置】操作。\n2、借款完成后请关注质押虚拟币的价值，价值大幅下跌时请及时追加质押品，建议质押率控制在75%以下。"}
     */
    private int del;

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    private int update;
    private NextStepInfoBean next_step_info;

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public NextStepInfoBean getNext_step_info() {
        return next_step_info;
    }

    public void setNext_step_info(NextStepInfoBean next_step_info) {
        this.next_step_info = next_step_info;
    }
}
