package com.grandream.dagt.ui.dialog.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grandream.dagt.R;


public abstract class DestDialogWithTwoBtn extends DestDialog {

	private static final String DEFAULT_POSITIVE_TEXT = "确定";

	private static final String DEFAULT_NEGATIVE_TEXT = "取消";

	public DestDialogWithTwoBtn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 创建DestDialog调此方法
	 * 
	 * @param content
	 *            对话框内容
	 * @param positiveText
	 *            确定按钮的文字内容
	 * @param negativeText
	 *            取消按钮的文字内容
	 * @param positiveListener
	 *            点确定时的事件回调(默认行为点完按钮对话框会自动消失,即使不设置该值)
	 * @param negativeListener
	 *            点取消时的事件回调(默认行为点完按钮对话框会自动消失,即使不设置该值)
	 * @return
	 */
	@SuppressLint("ValidFragment")
	public static DestDialogWithTwoBtn newInstance(String content, String positiveText,
                                                   String negativeText, final OnClickListener positiveListener,
                                                   final OnClickListener negativeListener) {
		DestDialogWithTwoBtn destDialog = new DestDialogWithTwoBtn() {
			@Override
			public OnClickListener getPositiveListener() {
				return new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						dismiss();
						if (positiveListener != null) {
							positiveListener.onClick(v);
						}
					}
				};
			}

			@Override
			public OnClickListener getNegativeListener() {
				return new OnClickListener() {
					@Override
					public void onClick(View v) {
						
							dismiss();
						if (negativeListener != null) {
							negativeListener.onClick(v);
						}
					}
				};
			}
		};

		Bundle args = new Bundle();
		args.putString("content", content);
		args.putString("positiveText", positiveText);
		args.putString("negativeText", negativeText);
		destDialog.setArguments(args);
		return destDialog;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_excute_layout, container,
				false);
		
		TextView mContentTv = (TextView) view.findViewById(R.id.content_text);
		mContentTv.setText("");
		mContentTv.setGravity(Gravity.CENTER);
		
	

		TextView mRightBtn = (TextView) view.findViewById(R.id.right_btn);
		mRightBtn.setBackgroundResource(R.drawable.btn_dialog_selector);
		TextView mLeftBtn = (TextView) view.findViewById(R.id.lef_btn);
		mLeftBtn.setBackgroundResource(R.drawable.btn_dialog_selector);

		String forRightText = DEFAULT_NEGATIVE_TEXT;
		OnClickListener forRightListener = getNegativeListener();
		String forLeftText = DEFAULT_POSITIVE_TEXT;
		OnClickListener forLeftListener = getPositiveListener();
		if (android.os.Build.VERSION.SDK_INT >= 14) {
			forRightText = DEFAULT_POSITIVE_TEXT;
			forRightListener = getPositiveListener();
			forLeftText = DEFAULT_NEGATIVE_TEXT;
			forLeftListener = getNegativeListener();
		}

		mRightBtn.setText(forRightText);
		mRightBtn.setOnClickListener(forRightListener);
		mLeftBtn.setText(forLeftText);
		mLeftBtn.setOnClickListener(forLeftListener);

		return view;
	}

	protected abstract OnClickListener getPositiveListener();

	protected abstract OnClickListener getNegativeListener();

}
