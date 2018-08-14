package com.grandream.dagt.fragment.user;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.login.CountryCode;
import com.grandream.dagt.bean.login.RegisterInfo;
import com.grandream.dagt.bean.login.RegisterSmsCode;
import com.grandream.dagt.bean.login.RegisterVerifyCode;
import com.grandream.dagt.databinding.RegisterLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.user.DoRegister;
import com.grandream.dagt.utils.Utils;

/**
 * 注册账号
 * Created by chenjing on 2018/3/28.
 */

public class RegisterFragment extends BaseFragment {
    private static final String FRAGMENTERROR = "fragment error";
    RegisterLayoutBinding binding;
    private String user_name;
    private boolean runningDownTimer;     //判断是否在倒计时


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.register_layout, container, false);
        savedInstanceState = getArguments();
        user_name = savedInstanceState.getString("user_name");
        binding.setClick(this);
        getCountryCode();
        getVerifyCode();
        return binding.getRoot();
    }

    public void sendBack(View view) {
        if (getActivity().getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
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

    public void NextClick(View view) {
        if (CheckValue()) {
            doRegister();
        }
    }

    private boolean CheckValue() {
        if (!binding.button.isChecked()) {
            Utils.ShowDialog("请勾选协议", FRAGMENTERROR, RegisterFragment.this, getActivity());
            return false;
        }
        return true;
    }

    private void doRegister() {
        DoRegister doRegister = new DoRegister();
        doRegister.setUser_name(user_name);
        doRegister.setPassword(binding.password.getEditableText().toString());
        doRegister.setSms_code(binding.SMSCode.getEditableText().toString());
        doRegister.setVerify_code(binding.ImageCode.getEditableText().toString());
        ApiService.doRegister(getActivity(), new IHttpCallback<RegisterInfo>() {

            @Override
            public void onNext(HttpResult<RegisterInfo> t) {
                Bundle bundle = new Bundle();
                bundle.putString("user_name", t.getResponse_data().getUser_name());
                GenericFragmentActivity.start(getActivity(), SecondLoginFragment.class, bundle);
                getActivity().finish();
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(doRegister));
    }

    /**
     * 获取短信验证码
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
        parameters.put("verify_code", binding.ImageCode.getEditableText().toString());
        ApiService.getSMSCode(getActivity(), new IHttpCallback<RegisterSmsCode>() {
            @Override
            public void onNext(HttpResult<RegisterSmsCode> t) {
                downTimer.start();  // 倒计时开始
            }

            @Override
            public void onError(int code, String message) {
                binding.getSms.setText("重新发送");
                runningDownTimer = false;
            }
        }, parameters);
    }

    /**
     * 获取图形验证码
     */
    public void getVerifyCode() {
        parameters.clear();
        parameters.put("telephone", user_name);
        ApiService.getVerifyCode(getActivity(), new IHttpCallback<RegisterVerifyCode>() {

            @Override
            public void onNext(HttpResult<RegisterVerifyCode> t) {
                binding.verifyCode.setImageBitmap(Utils.stringToBitmap(t.getResponse_data().getVerify_code()));
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    /**
     * 再次请求图形验证码
     *
     * @param v
     */
    public void getImageCode(View v) {
        getVerifyCode();
    }
}
