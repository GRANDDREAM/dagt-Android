package com.grandream.dagt.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import android.widget.TextView;

import com.grandream.dagt.R;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;


/**
 * Created by Administrator on 2016/1/27.
 */
public class EditAlertDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title, txt_msg;
    private static EditText transaction_serial_number;
    private Button btn_neg;
    private Button btn_pos;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;


    public EditAlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public EditAlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.edit_dialog, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.ll_loginout);
        txt_title = (TextView) view.findViewById(R.id.title);
        txt_title.setVisibility(View.GONE);
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

    public EditAlertDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText("标题");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    /*	public LoginOutAlertDialog setMsg(String msg,String msg1,String msg2) {
                showMsg = true;
                if ("".equals(msg)) {
                      txt_msg.setText("内容");
                } else {
                      txt_msg.setText(msg);
                      txt_msg1.setText(msg1);
                      txt_msg2.setText(msg2);
                }
                return this;
          }
    */
//    public EditAlertDialog setMsg(String msg) {
//        showMsg = true;
//        if ("".equals(msg)) {
//            txt_msg.setText("内容");
//        } else {
//            txt_msg.setText(msg);
//        }
//        return this;
//    }

    public EditAlertDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public EditAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public EditAlertDialog setPositiveButton(String text,
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

    public EditAlertDialog setNegativeButton(String text,
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

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            //loginpassword.setVisibility(View.VISIBLE);
            txt_msg.setVisibility(View.VISIBLE);
        }

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

    public void show() {
        setLayout();
        dialog.show();
    }
}
