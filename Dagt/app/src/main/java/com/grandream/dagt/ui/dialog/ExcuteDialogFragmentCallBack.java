package com.grandream.dagt.ui.dialog;

/**
 * Created by Administrator on 2016/2/2.
 */
public interface ExcuteDialogFragmentCallBack {
    /**
     * 确认点击
     *
     * @param tag
     */
    public void onPositiveBtnClick(String tag);

    /**
     * 取消点击
     *
     * @param tag
     */
    public void onNegtiveBtnClick(String tag);
}
