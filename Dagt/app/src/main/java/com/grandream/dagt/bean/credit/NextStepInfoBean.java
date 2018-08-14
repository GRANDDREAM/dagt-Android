package com.grandream.dagt.bean.credit;

import java.io.Serializable;

/**
 * Created by chenjing on 2018/4/28.
 */

public class NextStepInfoBean implements Serializable {

    /**
     * tip_title : 贷款授信函创建成功
     * tip_content : 请尽快前往APP应用市场下载【中国好奇银行】APP完成贷款操作，以免授信函过期失效。
     * warn_tip : 质押风险提示
     1、系统将监测所有质押账户中数字资产的变化情况，计算数字资产发生变化的质押率。当质押率大于等于70%时第一次触发预警，我们将进行短信、APP消息提醒，请前往【我的】-【系统设置】-【平仓预警设置】操作。
     2、借款完成后请关注质押虚拟币的价值，价值大幅下跌时请及时追加质押品，建议质押率控制在75%以下。
     */

    private String tip_title;
    private String tip_content;
    private String warn_tip;

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

    public String getWarn_tip() {
        return warn_tip;
    }

    public void setWarn_tip(String warn_tip) {
        this.warn_tip = warn_tip;
    }
}
