package com.grandream.dagt.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grandream.dagt.R;


/**
 * Created by Administrator on 2016/1/27.
 */
public class SysWaringEditAlertDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private static EditText transaction_serial_number;
    private Button btn_neg;
    private Button btn_pos;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private RelativeLayout title_rl;
    private TextView title;

    public SysWaringEditAlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public SysWaringEditAlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.sys_waring_edit_dialog, null);

        // 获取自定义Dialog布局中的控件
        title = (TextView) view.findViewById(R.id.title);
        title_rl = (RelativeLayout) view.findViewById(R.id.title_rl);
        lLayout_bg = (LinearLayout) view.findViewById(R.id.ll_loginout);
        btn_neg = (Button) view.findViewById(R.id.btn_cancel);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_confirm);
        btn_pos.setVisibility(View.GONE);
        transaction_serial_number = (EditText) view.findViewById(R.id.ed_no);
        /*img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);*/

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

        return this;
    }


    public static String getTransactionSerialNumberText() {
        String transaction_serial_number_text = transaction_serial_number.getEditableText().toString();
        return transaction_serial_number_text;
    }


    public SysWaringEditAlertDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public SysWaringEditAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public SysWaringEditAlertDialog setPositiveButton(String text,
                                                      final OnClickListener listener, final DialogClick dialogClick) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialogClick.onDiaLogClick(transaction_serial_number.getEditableText().toString());
                dialog.dismiss();
            }
        });
        return this;
    }

    public SysWaringEditAlertDialog setTitle(String value) {
        title_rl.setVisibility(View.VISIBLE);
        title.setText(value);
        return this;
    }

    public SysWaringEditAlertDialog setNegativeButton(String text,
                                                      final OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    private void setLayout() {


        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("确定");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_neg.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
        }
    }

    public View getEditView() {
        return transaction_serial_number;
    }

    public void show() {
        setLayout();
        dialog.show();
    }
}
