package com.grandream.dagt.bean.home.share;

/**
 * Created by chenjing on 2018/6/27.
 */

public class ShareData {

    /**
     * share_url : http://tmanage.51kjq.com/register_ch?share_code=QshkTfFM7pLB7HYkvWAJczgxZTY2OGYwMDBmMzkzZjdlZjdjNTZmZDg5ZjFjN2NkMTNkYzk1YTVmZjczNzg4YWUwZjRjMTU3NzJlZTQwYzHzyEePC+lvh2cAc5zU5nPxMeMoHT7O2Otse4R0fyJeAKMPAJSd+fCIV5RuVXMJaek=
     * share_title : 推荐好友1
     * share_content : 测试测试测his吃
     * share_icon : null
     * reward_count : 20
     * total_sum_recommend : 20
     * reward_coin_info : DAGT
     */

    private String share_url;
    private String share_title;
    private String share_content;
    private Object share_icon;
    private String reward_count;
    private String total_sum_recommend;
    private String reward_coin_info;

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getShare_title() {
        return share_title;
    }

    public void setShare_title(String share_title) {
        this.share_title = share_title;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }

    public Object getShare_icon() {
        return share_icon;
    }

    public void setShare_icon(Object share_icon) {
        this.share_icon = share_icon;
    }

    public String getReward_count() {
        return reward_count;
    }

    public void setReward_count(String reward_count) {
        this.reward_count = reward_count;
    }

    public String getTotal_sum_recommend() {
        return total_sum_recommend;
    }

    public void setTotal_sum_recommend(String total_sum_recommend) {
        this.total_sum_recommend = total_sum_recommend;
    }

    public String getReward_coin_info() {
        return reward_coin_info;
    }

    public void setReward_coin_info(String reward_coin_info) {
        this.reward_coin_info = reward_coin_info;
    }
}
