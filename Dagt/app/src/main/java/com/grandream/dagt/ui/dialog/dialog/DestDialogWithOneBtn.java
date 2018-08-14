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


public abstract class DestDialogWithOneBtn extends DestDialog {

    private static final String DEFAULT_BTN_TEXT = "知道了";

    /**
     * @param content  对话框内容
     * @param btnText  单按钮的文字内容
     * @param listener 点单按钮时的事件回调(默认行为点完按钮对话框会自动消失,即使不设置该值)
     * @return
     */
    @SuppressLint("ValidFragment")
    public static DestDialogWithOneBtn newInstance(String content, String btnText,
                                                   final OnClickListener listener) {
        DestDialogWithOneBtn destDialog = new DestDialogWithOneBtn() {
            @SuppressLint("ValidFragment")
            @Override
            public OnClickListener getBtnListener() {
                return new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        if (listener != null) {
                            listener.onClick(v);
                        }
                    }
                };
            }
        };

        Bundle args = new Bundle();
        args.putString("content", content);
        args.putString("btnText", btnText);
        destDialog.setArguments(args);
        return destDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_error_layout, container, false);

        TextView mContentTv = (TextView) view.findViewById(R.id.context_text);
        mContentTv.setText("");
        mContentTv.setGravity(Gravity.CENTER);

        TextView btn = (TextView) view.findViewById(R.id.single_btn);
        btn.setText(DEFAULT_BTN_TEXT);
        btn.setOnClickListener(getBtnListener());

        return view;
    }

    protected abstract OnClickListener getBtnListener();

}
