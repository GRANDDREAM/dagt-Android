package com.grandream.dagt.ui.dialog.dialog;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.grandream.dagt.R;
import com.grandream.dagt.base.BaseFragmentActivity;
import com.grandream.dagt.utils.ActivityChecker;


public class DialogHelper {
	private static final String TAG = "DestDialog";

	@Deprecated
	public static final String NO_NET_DIALOG = "noNetDialog";

	public static void showInfo(final FragmentActivity activity, String content) {
		showInfo(activity, content, null, null);
	}

	/**
	 * 提示框信息 "知道了"
	 * 
	 * @param activity
	 *            启动dialog的activity
	 * @param content
	 *            提示信息
	 * @param btnText
	 *            按钮文字替代"知道了"
	 * @param listener
	 *            点“知道了”按钮回调
	 */
	public static void showInfo(final FragmentActivity activity, String content, String btnText, final OnClickListener listener, boolean cancelable) {
		if(ActivityChecker.needBreak(activity)) return;
		Resources res = activity.getResources();
		DestDialogWithOneBtn destDialog = DestDialogWithOneBtn.newInstance(content, TextUtils.isEmpty(btnText) ? res.getString(R.string.yes_i_konw) : btnText, listener);
		destDialog.setCancelable(cancelable);
		destDialog.fixedShow(activity, "DestDialogWithOneBtn");
	}

	public static void showInfo(final FragmentActivity activity, String content, String btnText, final OnClickListener listener) {
		showInfo(activity, content, btnText, listener, true);
	}

	/**
	 * 无法通过回退键取消的Dialog
	 * 
	 * @param activity
	 *            启动dialog的activity
	 * @param content
	 *            提示信息
	 * @param positiveText
	 *            确定按钮内容
	 * @param negativeText
	 *            取消按钮内容
	 * @param positiveListener
	 *            确定按钮回调
	 * @param negativeListener
	 *            取消按钮回调
	 */
	public static void showDialog(FragmentActivity activity, String content, String positiveText, String negativeText, OnClickListener positiveListener, OnClickListener negativeListener) {
		showDialog(activity, content, positiveText, negativeText, positiveListener, negativeListener, false);
	}

	/**
	 * @param activity
	 *            启动dialog的activity
	 * @param content
	 *            提示信息
	 * @param positiveText
	 *            确定按钮内容
	 * @param negativeText
	 *            取消按钮内容
	 * @param positiveListener
	 *            确定按钮回调
	 * @param negativeListener
	 *            取消按钮回调
	 * @param cancelable
	 *            对话能否被取消
	 */
	public static void showDialog(FragmentActivity activity, String content, String positiveText, String negativeText, OnClickListener positiveListener, OnClickListener negativeListener, boolean cancelable) {
		DestDialogWithTwoBtn destDialog = DestDialogWithTwoBtn.newInstance(content, positiveText, negativeText, positiveListener, negativeListener);
		destDialog.setCancelable(cancelable);
		destDialog.fixedShow(activity, "DestDialogWithTwoBtn");
	}

	/**
	 * 无网络提示框 "知道了" | "拨打电话"
	 * 
	 * @param activity
	 *            启动dialog的activity
	 */
	public static void showNoNetwork(final FragmentActivity activity) {
		showNoNetwork(activity, null);
	}

	/**
	 * 无网络提示框 "知道了" | "拨打电话"
	 * 
	 * @param activity
	 *            启动dialog的activity
	 * @param listener
	 *            "知道了"按钮的回调事件
	 */
	public static void showNoNetwork(final FragmentActivity activity, final OnClickListener listener) {
		Resources res = activity.getResources();
		showDialog(activity, res.getString(R.string.commom_error_net_unconnect), "拨打电话", res.getString(R.string.yes_i_konw), new OnClickListener() {
			public void onClick(View v) {

				if (activity instanceof BaseFragmentActivity) {
					if (listener != null) {
						listener.onClick(v);
					}

				} else {
					Log.w(TAG, activity.toString() + " is not a descendent of QedBaseActivityV2" + " to invoke QedCallManager.goCall ");
				}
			}
		}, listener, false);
	}


}
