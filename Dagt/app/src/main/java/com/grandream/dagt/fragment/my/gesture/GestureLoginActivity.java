package com.grandream.dagt.fragment.my.gesture;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.grandream.dagt.R;
import com.grandream.dagt.base.BaseFragmentActivity;
import com.grandream.dagt.bean.credit.SerciceFeeReslut;
import com.grandream.dagt.databinding.ActivityGestureLoginBinding;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.my.GestureGetData;
import com.grandream.dagt.requestbody.my.GesturePasswordSet;
import com.grandream.dagt.requestbody.user.DoLogin;
import com.grandream.dagt.ui.dialog.DialogClick;
import com.grandream.dagt.ui.dialog.SysWaringEditAlertDialog;
import com.grandream.dagt.ui.lockpattern.LockPatternView;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.LockPatternUtil;
import com.grandream.dagt.utils.Utils;

import java.util.List;

/**
 * Created by Sym on 2015/12/24.
 */
public class GestureLoginActivity extends BaseFragmentActivity {
    private ActivityGestureLoginBinding binding;
    private static final long DELAYTIME = 600l;
    boolean isOpen;
    boolean ONSTRART;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gesture_login);
        binding.messageTv.setText(Utils.replaceToXing(Utils.getUser_name(this), 4, Utils.getUser_name(this).length() - 4));
        binding.setClick(this);
        intent = getIntent();
        ONSTRART = intent.getBooleanExtra("ONSTRART", false);
        if (!ONSTRART) {
            isOpen = intent.getBooleanExtra("ISOPEN", false);
        }

        initView();
    }

    private void initView() {
        if (!ONSTRART) {
            binding.navigation.setLeftClick(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        } else {
            binding.navigation.hideLeft(true);
        }
        binding.lockPatternView.setOnPatternListener(patternListener);
    }


    private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {

        @Override
        public void onPatternStart() {
            binding.lockPatternView.removePostClearPatternRunnable();
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> pattern) {
            if (pattern != null) {
                String bytes = LockPatternUtil.patternToHash(pattern);
                try {
                    String password = bytes;
                    checkGesture(password);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    };

    private void checkGesture(String password) {
        GesturePasswordSet body = new GesturePasswordSet();
        body.setTelephone(Utils.getUser_name(this));
        String data = password + "" + body.getTelephone();
        body.setGesture_password(Utils.md5(data.trim()));
        ApiService.checkGesture(this, new IHttpCallback<SerciceFeeReslut>() {
            @Override
            public void onNext(HttpResult<SerciceFeeReslut> t) {
                if (t.getResponse_data().getSuccess()) {
                    //验证成功1代表关闭手势密码，2代表开启手势密码
                    if (!ONSTRART) {
                        if (isOpen) {
                            //关闭
                            sysSetSwitch(1);
                        } else {
                            //打开
                            sysSetSwitch(2);
                        }
                    } else {
                        CommonUtil.showToast(t.getResponse_text());
                        finish();
                    }
                } else {
                    binding.lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                    binding.lockPatternView.postClearPatternRunnable(DELAYTIME);
                    CommonUtil.showToast(t.getResponse_text());
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }


    public void forgetGestureBtn(View view) {
        final SysWaringEditAlertDialog editAlertDialog = new SysWaringEditAlertDialog(this);
        editAlertDialog.builder()
                .setTitle("请输入您的密码")
                .setCanceledOnTouchOutside(false)
                .setPositiveButton("确定",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }, new DialogClick() {
                            @Override
                            public void onDiaLogClick(String value) {
                                if (isSoftShowing()) {
                                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                                    imm.showSoftInput(view, InputMethodManager.SHOW_FORCED); //强制显示键盘
                                    imm.hideSoftInputFromWindow(editAlertDialog.getEditView().getWindowToken(), 0); //强制隐藏键盘 InputMethodManager imm = (InputMethodMan
                                }
                                checkLoginPassword(value);
                            }
                        })
                .setNegativeButton("取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isSoftShowing()) {
                                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                                    imm.showSoftInput(view, InputMethodManager.SHOW_FORCED); //强制显示键盘
                                    imm.hideSoftInputFromWindow(editAlertDialog.getEditView().getWindowToken(), 0); //强制隐藏键盘 InputMethodManager imm = (InputMethodMan
                                }
                                return;
                            }
                        }).show();
    }

    private void checkLoginPassword(String value) {
        DoLogin body = new DoLogin();
        body.setTelephone(Utils.getUser_name(this));
        body.setPassword(value);
        ApiService.checkLoginPassword(this, new IHttpCallback<SerciceFeeReslut>() {
            @Override
            public void onNext(HttpResult<SerciceFeeReslut> t) {
                if (t.getResponse_data().getSuccess()) {
                    //验证成功
                    Intent intent = new Intent(GestureLoginActivity.this, CreateGestureActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (isSoftShowing()) {
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    CommonUtil.showToast(t.getResponse_text());
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    /**
     * 手势密码参数为1和2。1代表关闭手势密码，2代表开启手势密码
     *
     * @param guest_password_switch
     */
    public void sysSetSwitch(int guest_password_switch) {
        GestureGetData body = new GestureGetData(Utils.getUser_id(this), Utils.getUser_name(this), guest_password_switch);
        ApiService.sysSetSwitch(this, new IHttpCallback<SerciceFeeReslut>() {
            @Override
            public void onNext(HttpResult<SerciceFeeReslut> t) {
                if (t.getResponse_data().getSuccess()) {
                    finish();
                }
                CommonUtil.showToast(t.getResponse_text());

            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (ONSTRART) {
            // TODO Auto-generated method stub
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    AlertDialog.Builder build = new AlertDialog.Builder(this);
                    build.setTitle("注意")
                            .setMessage("确定要退出吗？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    quit();

                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub

                                }
                            })
                            .show();
                    break;

                default:
                    break;
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
        return false;
    }

}
