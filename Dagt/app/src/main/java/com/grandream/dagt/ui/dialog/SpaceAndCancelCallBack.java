package com.grandream.dagt.ui.dialog;

/**
 * Created by Administrator on 2016/2/2.
 */
public interface SpaceAndCancelCallBack {
    /**
     * 空白点击回调
     *
     * @param tag
     */
    public void onSpaceClick(String tag);

    /**
     * 取消事件回调
     *
     * @param tag
     */
    public void onCanceled(String tag);
}
