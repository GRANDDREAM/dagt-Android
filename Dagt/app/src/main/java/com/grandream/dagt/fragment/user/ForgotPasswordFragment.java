package com.grandream.dagt.fragment.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.login.CountryCode;
import com.grandream.dagt.bean.login.RegisterSmsCode;
import com.grandream.dagt.bean.login.ResetResult;
import com.grandream.dagt.databinding.ForGPLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.user.ReSetPwd;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

/**
 * 忘记密码
 * Created by chenjing on 2018/4/13.
 */

public class ForgotPasswordFragment extends BaseFragment {
    private ForGPLayoutBinding binding;
    private boolean runningDownTimer;
    private String user_name, country;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.for_g_p_layout, container, false);
        savedInstanceState = getArguments();
        user_name = savedInstanceState.getString("user_name");
        getCountryCode();
        binding.setClick(this);
        return binding.getRoot();
    }

    public void SenBack(View view) {
        sendKeyBackEvent();
    }

    private void getCountryCode() {
        ApiService.getCountryCode(getContext(), new IHttpCallback<CountryCode>() {

            @Override
            public void onNext(HttpResult<CountryCode> t) {
                binding.phone.setText(t.getResponse_data().getCountry_code() + user_name);
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    /**
     * 获取验证码
     *
     * @param view
     */
    public void getSMSCode(View view) {
        //如果60秒倒计时没有结束
        if (runningDownTimer) {
            return;
        }

        binding.getSms.setText("正在发送");
        getSMSCode();
    }

    /**
     * 倒计时
     */
    private CountDownTimer downTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            runningDownTimer = true;
            binding.getSms.setText((l / 1000) + "秒后重发");
        }

        @Override
        public void onFinish() {
            runningDownTimer = false;
            binding.getSms.setText("重新发送");
        }

    };

    private void getSMSCode() {
        parameters.clear();
        parameters.put("telephone", user_name);
        ApiService.forGetSMSCode(getActivity(), new IHttpCallback<RegisterSmsCode>() {
            @Override
            public void onNext(HttpResult<RegisterSmsCode> t) {
                downTimer.start();  // 倒计时开始
            }

            @Override
            public void onError(int code, String message) {
                runningDownTimer = false;
            }
        }, parameters);
    }


    /**
     * 確定
     *
     * @param view
     */
    public void NextClick(View view) {
        if (CheckValue()) {
            doCommit();
        }
    }

    private boolean CheckValue() {
        if (TextUtils.isEmpty(binding.SMSCode.getEditableText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.register_input2));
            return false;
        }
        if (binding.password.getEditableText().toString().length() < 8) {
            CommonUtil.showToast(getResources().getString(R.string.register_input3));
            return false;
        }
        if (TextUtils.isEmpty(binding.password.getEditableText().toString())) {
            CommonUtil.showToast(this.getResources().getString(R.string.input_new_pwd));
            return false;
        }

        return true;
    }

    private void doCommit() {
        ReSetPwd reSetPwd = new ReSetPwd();
        reSetPwd.setSms_code(binding.SMSCode.getEditableText().toString());
        reSetPwd.setPassword(binding.password.getEditableText().toString());
        reSetPwd.setTelephone(user_name);
        ApiService.ReSetPwd(getActivity(), new IHttpCallback<ResetResult>() {
            @Override
            public void onNext(HttpResult<ResetResult> t) {
                //设置成功与否，0为失败，1为成功。
                if (t.getResponse_data().getReset() == 0) {
                    CommonUtil.showToast("重置密码失败");
                } else {
                    CommonUtil.showToast("重置密码成功");
                    Bundle bundle = new Bundle();
                    bundle.putString("user_name", user_name);
                    GenericFragmentActivity.start(getActivity(), SecondLoginFragment.class, bundle);
                    getActivity().finish();
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(reSetPwd));
    }

}
