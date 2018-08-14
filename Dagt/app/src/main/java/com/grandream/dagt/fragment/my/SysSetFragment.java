package com.grandream.dagt.fragment.my;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.Manifest;
import com.grandream.dagt.R;
import com.grandream.dagt.activity.MainActivity;
import com.grandream.dagt.base.BaseFragmentActivity;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.SerciceFeeReslut;
import com.grandream.dagt.bean.my.ResetLogPwdBean;
import com.grandream.dagt.common.AppContext;
import com.grandream.dagt.databinding.SysSetLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.my.gesture.CreateGestureActivity;
import com.grandream.dagt.fragment.my.gesture.GestureLoginActivity;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.user.DoLogin;
import com.grandream.dagt.requestbody.user.UserID;
import com.grandream.dagt.ui.dialog.DialogClick;
import com.grandream.dagt.ui.dialog.LoginOutAlertDialog;
import com.grandream.dagt.ui.dialog.SysWaringEditAlertDialog;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

/**
 * 系统设置
 * Created by chenjing on 2018/3/22.
 */

public class SysSetFragment extends BaseFragment {
    private SysSetLayoutBinding binding;
    private boolean isOpen;
    private boolean isSetGesture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.sys_set_layout, container, false);
        binding.setClick(this);
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        getGestureData();
        isSetGesture();
        return binding.getRoot();
    }

    /**
     * 登录密码设置
     *
     * @param view
     */
    public void PassWordSet_Click(View view) {
        GenericFragmentActivity.start(getActivity(), LoginPasswordSetFragment.class, new Bundle());

    }


    /**
     * 交易密碼設置
     *
     * @param view
     */
    public void PayPassWordSet_Click(View view) {
        GenericFragmentActivity.start(getActivity(), TradePwdFragment.class, new Bundle());
    }


    /**
     * 手势密码设置
     *
     * @param view
     */
    public void setting_checkbox_Click(View view) {
        if (isOpen) {
            //关闭手势密码
            Intent intent = new Intent(getActivity(), GestureLoginActivity.class);
            intent.putExtra("ISOPEN", isOpen);
            intent.putExtra("ONSTRART", false);
            startActivity(intent);
        } else {
            if (isSetGesture) {
                //直接验证手势密码
                Intent intent = new Intent(getActivity(), GestureLoginActivity.class);
                intent.putExtra("ISOPEN", isOpen);
                intent.putExtra("ONSTRART", false);
                startActivity(intent);
            } else {
                //设置手势密码
                Intent intent = new Intent(getActivity(), CreateGestureActivity.class);
                startActivity(intent);
            }
        }
    }

    /**
     * 平仓预禁
     *
     * @param view
     */
    public void UnwindPreloaded_Click(View view) {
        GenericFragmentActivity.start(getActivity(), UnwindPreloadedFragment.class, new Bundle());
    }


    /**
     * 退出当期账号
     *
     * @param view
     */
    public void logingOut_Click(View view) {
        LoginOutAlertDialog dialog = new LoginOutAlertDialog(
                getActivity());
        dialog.builder()
                .setCanceledOnTouchOutside(false)
                .setMsg("您将退出此次登录，是否确定")
                .setPositiveButton("确认退出",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SendLogOut();
                            }
                        })
                .setNegativeButton("点错了",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                return;
                            }
                        }).show();
    }

    public void SendLogOut() {
        UserID userID = new UserID(Utils.getUser_id(getActivity()));
        ApiService.LogOut(getActivity(), new IHttpCallback<ResetLogPwdBean>() {
            @Override
            public void onNext(HttpResult<ResetLogPwdBean> t) {
                if (t.getResponse_data().getLogout() == 1) {
                    if (Utils.ClearUserInfo(getActivity())) {
                        CommonUtil.showToast("退出登录成功");
                        Utils.getNewToken(getContext());
                        getActivity().finish();
                    } else {
                        CommonUtil.showToast("清理数据失败");
                    }

                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(userID));
    }

    public void getGestureData() {
        parameters.put("user_id", Utils.getUser_id(getActivity()));
        parameters.put("user_name", Utils.getUser_name(getActivity()));
        ApiService.SysIndex(getActivity(), new IHttpCallback<ResetLogPwdBean>() {
            @Override
            public void onNext(HttpResult<ResetLogPwdBean> t) {
                //0为未打开，1为已打开。
                if (t.getResponse_data().getSwitch_on() == 1) {
                    isOpen = true;
                    binding.settingCheckbox1.setChecked(true);
                } else {
                    isOpen = false;
                    binding.settingCheckbox1.setChecked(false);
                }
                Utils.saveGesutreStatus(getActivity(), isOpen);
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }


    public void isSetGesture() {
        UserID userID = new UserID(Utils.getUser_id(getActivity()));
        ApiService.isSetGesture(getActivity(), new IHttpCallback<SerciceFeeReslut>() {
            @Override
            public void onNext(HttpResult<SerciceFeeReslut> t) {
//                0为未设置，1为已设置
                isSetGesture = t.getResponse_data().getExists();
                if (isSetGesture) {
                    binding.GesturesPwdModifyClickLin.setVisibility(View.VISIBLE);
                    binding.GesturesPwdModifyRl.setVisibility(View.VISIBLE);
                } else {
                    binding.GesturesPwdModifyClickLin.setVisibility(View.GONE);
                    binding.GesturesPwdModifyRl.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(userID));
    }


    public void GesturesPwd_Modify_Click(View view) {
        //弹窗验证登录密码，验证成功后，显示手势密码框，重新添加手势
        final SysWaringEditAlertDialog editAlertDialog = new SysWaringEditAlertDialog(getActivity());
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
                                checkLoginPassword(value);
                            }
                        })
                .setNegativeButton("取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                return;
                            }
                        }).show();
    }

    private void checkLoginPassword(String value) {
        DoLogin body = new DoLogin();
        body.setTelephone(Utils.getUser_name(getActivity()));
        body.setPassword(value);
        ApiService.checkLoginPassword(getActivity(), new IHttpCallback<SerciceFeeReslut>() {
            @Override
            public void onNext(HttpResult<SerciceFeeReslut> t) {
                if (t.getResponse_data().getSuccess()) {
                    //验证成功
                    Intent intent = new Intent(getActivity(), CreateGestureActivity.class);
                    startActivity(intent);

                } else {
                    CommonUtil.showToast(t.getResponse_text());
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    @Override
    public void onResume() {
        super.onResume();
        getGestureData();
    }


}
