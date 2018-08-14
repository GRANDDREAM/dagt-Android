package com.grandream.dagt.fragment.my;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.SerciceFeeReslut;
import com.grandream.dagt.bean.my.SysWaringBean;
import com.grandream.dagt.databinding.UnwindPreloadedLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.my.WrningBody;
import com.grandream.dagt.ui.dialog.DialogClick;
import com.grandream.dagt.ui.dialog.EditAlertDialog;
import com.grandream.dagt.ui.dialog.SysWaringEditAlertDialog;
import com.grandream.dagt.utils.Utils;

import cn.jpush.android.api.JPushInterface;

/**
 * 平常预警
 * Created by chenjing on 2018/5/24.
 */

public class UnwindPreloadedFragment extends BaseFragment {
    UnwindPreloadedLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.unwind_preloaded_layout, container, false);
        binding.setClick(this);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        binding.navigation.setRightText("预警说明");
        binding.navigation.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
//                bundle.putString("RULE_ID", rule_id);
                GenericFragmentActivity.start(getActivity(), CommonProblemFragment.class, bundle);
            }
        });
        binding.settingCheckbox1.setChecked(JPushInterface.getConnectionState(getContext()));
        getData();
    }

    public void getData() {
        parameters.put("user_id", Utils.getUser_id(getActivity()));
        ApiService.earlyWarningsIndex(getActivity(), new IHttpCallback<SysWaringBean>() {
            @Override
            public void onNext(HttpResult<SysWaringBean> t) {
                binding.setBean(t.getResponse_data());
                if (TextUtils.isEmpty(t.getResponse_data().getEarly_warning_email())) {
                    binding.add.setText("添加");
                } else {
                    binding.add.setText("修改");
                }
                if (TextUtils.isEmpty(t.getResponse_data().getEarly_warning_telephone())) {
                    binding.edit.setText("添加");
                } else {
                    binding.edit.setText("修改");
                }

            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    public void setting_checkbox_Click(View v) {
        if (binding.settingCheckbox1.isChecked()) {
            JPushInterface.resumePush(getActivity());
        } else {
            JPushInterface.stopPush(getActivity());
        }
    }

    public void early_warning_telephoneClick(View v) {
        SysWaringEditAlertDialog editAlertDialog = new SysWaringEditAlertDialog(getActivity());
        editAlertDialog.builder()
                .setCanceledOnTouchOutside(false)
                .setPositiveButton("保存",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }, new DialogClick() {
                            @Override
                            public void onDiaLogClick(String value) {
                                earlyWarningsSet(value, 1);
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

    private void earlyWarningsSet(String value, final int type) {
        WrningBody body = new WrningBody();
        body.setUser_id(Utils.getUser_id(getActivity()));
        body.setEarly_warn_type(type);
        if (type == 1) {
            body.setEarly_warn_telephone(value);
        } else {
            body.setEarly_warn_email(value);
        }
        ApiService.earlyWarningsSet(getActivity(), new IHttpCallback<SerciceFeeReslut>() {
            @Override
            public void onNext(HttpResult<SerciceFeeReslut> t) {
                if (t.getResponse_data().getSuccess()) {
                    getData();
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    public void early_warning_emailClick(View v) {
        SysWaringEditAlertDialog editAlertDialog = new SysWaringEditAlertDialog(getActivity());
        editAlertDialog.builder()
                .setCanceledOnTouchOutside(false)
                .setPositiveButton("保存",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }, new DialogClick() {
                            @Override
                            public void onDiaLogClick(String value) {
                                earlyWarningsSet(value, 2);
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
}
