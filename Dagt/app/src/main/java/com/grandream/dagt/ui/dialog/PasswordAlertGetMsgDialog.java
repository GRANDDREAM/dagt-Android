package com.grandream.dagt.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
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
import com.grandream.dagt.bean.credit.CreditDetail;
import com.grandream.dagt.bean.wallet.PrivateKeyStore;
import com.grandream.dagt.bean.wallet.WalletIndext;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.user.ReSetPwd;
import com.grandream.dagt.utils.Utils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2016/1/27.
 */
public class PasswordAlertGetMsgDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView getmsg;
    private static EditText ed_msg, ed_password;
    private Button btn_neg;
    private Button btn_pos;
    private Display display;
    private boolean runningDownTimer;//判断是否在倒计时
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    public Map<String, Object> parameters = new HashMap<String, Object>();


    public PasswordAlertGetMsgDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public PasswordAlertGetMsgDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.password_getmsg_dialog, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.ll_loginout);
        btn_neg = (Button) view.findViewById(R.id.btn_cancel);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_confirm);
        btn_pos.setVisibility(View.GONE);
        ed_msg = (EditText) view.findViewById(R.id.ed_msg);
        ed_password = (EditText) view.findViewById(R.id.ed_password);
        getmsg = (TextView) view.findViewById(R.id.get_msg);

        getmsg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (runningDownTimer) {
                    return;
                }
                getSMSCode();
            }
        });
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

        return this;
    }

    /**
     * 倒计时
     */
    private CountDownTimer downTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            runningDownTimer = true;
            getmsg.setText((l / 1000) + "秒后重发");
        }

        @Override
        public void onFinish() {
            runningDownTimer = false;
            getmsg.setText("重新发送");
        }

    };

    public static String getEdMsgData() {
        String msg = ed_msg.getEditableText().toString();
        return msg;
    }

    public static String getPasswordData() {
        String password = ed_password.getEditableText().toString();
        return password;
    }


    public PasswordAlertGetMsgDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public PasswordAlertGetMsgDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public PasswordAlertGetMsgDialog setPositiveButton(String text,
                                                       final DialogClick dialogClick) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getKeyStore(dialogClick);
            }
        });
        return this;
    }

    public PasswordAlertGetMsgDialog setNegativeButton(String text,
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

    public void show() {
        setLayout();
        dialog.show();
    }

    public void getSMSCode() {
        parameters.put("user_id", Utils.getUser_id(context));
        parameters.put("telephone", Utils.getUser_name(context));
        ApiService.backupKeySmsCode(context, new IHttpCallback<WalletIndext>() {
            @Override
            public void onNext(HttpResult<WalletIndext> t) {
                downTimer.start();  // 倒计时开始
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    public void getKeyStore(final DialogClick dialogClick) {
        ReSetPwd body = new ReSetPwd();
        body.setUser_id(Utils.getUser_id(context) + "");
        body.setTelephone(Utils.getUser_name(context));
        body.setLogin_password(ed_password.getEditableText().toString());
        body.setSms_code(ed_msg.getEditableText().toString());
        ApiService.verifyBackup(context, new IHttpCallback<PrivateKeyStore>() {
            @Override
            public void onNext(HttpResult<PrivateKeyStore> t) {
                dialogClick.onDiaLogClick(t.getResponse_data().getPrivate_key());
                dialog.dismiss();
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }
}
