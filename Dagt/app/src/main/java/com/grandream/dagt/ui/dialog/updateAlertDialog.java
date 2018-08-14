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
import android.widget.TextView;

import com.grandream.dagt.R;


public class updateAlertDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title, txt_msg, txt_msg1, txt_msg2;
    private static EditText loginpassword;
    private Button btn_neg;
    private Button btn_pos;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public updateAlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public updateAlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.update_dialog, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.ll_update);
        txt_title = (TextView) view.findViewById(R.id.update_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.update_msg);
        txt_msg.setVisibility(View.GONE);
        txt_msg1 = (TextView) view.findViewById(R.id.update_msg1);
        txt_msg1.setVisibility(View.GONE);
        txt_msg2 = (TextView) view.findViewById(R.id.update_msg2);
        txt_msg2.setVisibility(View.GONE);
        btn_neg = (Button) view.findViewById(R.id.btn_update);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_update_pos);
        btn_pos.setVisibility(View.GONE);
        /*
         * img_line = (ImageView) view.findViewById(R.id.img_line);
		 * img_line.setVisibility(View.GONE);
		 */

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

        return this;
    }

    public static String getLoginPassword() {
        String password = loginpassword.getText().toString();
        return password;
    }

    public updateAlertDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText("标题");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    // 设置txt_msg的大小
    public updateAlertDialog setTextSize(float size) {
        txt_msg.setTextSize(size);
        //txt_msg.setGravity(Gravity.CENTER_HORIZONTAL);
        return this;
    }

    // 设置txt_msg的大小
    public updateAlertDialog setTextSize1(float size) {
        txt_msg1.setTextSize(size);
        //txt_msg1.setGravity(Gravity.CENTER_HORIZONTAL);
        return this;
    }

    // 设置txt_msg的大小
    public updateAlertDialog setTextSize2(float size) {
        txt_msg2.setTextSize(size);
        //txt_msg2.setGravity(Gravity.CENTER_HORIZONTAL);
        return this;
    }

    public updateAlertDialog setMsg(String msg, String msg1, String msg2) {
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

    public updateAlertDialog setMaintenanceMsg(String msg, String msg1, String msg2) {
        showMsg = true;
        txt_msg.setText(msg);
        txt_msg1.setText(msg1);
        txt_msg2.setText(msg2);
        return this;
    }

    public updateAlertDialog setMsg(String msg, float size, int gravity, int top, int left) {
        showMsg = true;
        if ("".equals(msg)) {
            txt_msg.setText("");
        } else {
            txt_msg.setText(msg);
            txt_msg.setTextSize(size);
            txt_msg.setGravity(gravity);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            params.rightMargin = 30;
            params.topMargin = top;
            params.leftMargin = left;
            txt_msg.setLayoutParams(params);
        }
        return this;
    }

    public updateAlertDialog setMsg1(String msg, float size, int gravity, int left) {
        showMsg = true;
        if ("".equals(msg)) {
            txt_msg1.setText("");
        } else {
            txt_msg1.setText(msg);
            txt_msg1.setTextSize(size);
            txt_msg1.setGravity(gravity);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            params.rightMargin = 30;
            params.leftMargin = left;
            txt_msg1.setLayoutParams(params);
        }
        return this;
    }

    public updateAlertDialog setMsg2(String msg, float size, int gravity, int left) {
        showMsg = true;
        if ("".equals(msg)) {
            txt_msg2.setText("");
        } else {
            txt_msg2.setText(msg);
            txt_msg2.setTextSize(size);
            txt_msg2.setGravity(gravity);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            params.rightMargin = 30;
            params.leftMargin = left;
            txt_msg2.setLayoutParams(params);
        }
        return this;
    }

    public updateAlertDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public updateAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public updateAlertDialog setPositiveButton(String text,
                                               final OnClickListener listener) {
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
                dialog.dismiss();
            }
        });
        return this;
    }

    public updateAlertDialog setNegativeButton(String text,
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
        if (!showTitle && !showMsg) {
            txt_title.setText("提示");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            // loginpassword.setVisibility(View.VISIBLE);
            txt_msg.setVisibility(View.VISIBLE);
            txt_msg1.setVisibility(View.VISIBLE);
            txt_msg2.setVisibility(View.VISIBLE);
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
