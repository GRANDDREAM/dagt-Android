package com.grandream.dagt.fragment.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.login.CheckMember;
import com.grandream.dagt.bean.login.CountryCode;
import com.grandream.dagt.databinding.LoginLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.user.CheckUser;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 * Created by chenjing on 2018/3/27.
 */

public class LogingFragment extends BaseFragment {
    LoginLayoutBinding binding;
    private Map<String, Object> parameters = new HashMap<String, Object>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_layout, container, false);
        binding.setClick(this);
        getCountryCode();
        return binding.getRoot();
    }

    public void Area_Num_Seletor(View view) {

    }


    private void getCountryCode() {
        ApiService.getCountryCode(getContext(), new IHttpCallback<CountryCode>() {

            @Override
            public void onNext(HttpResult<CountryCode> t) {
                binding.areaNum.setText(t.getResponse_data().getCountry_code());

            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    /**
     * 下一步按钮点击
     *
     * @param view
     */
    public void NextClick(View view) {
        if (CheckValue()) {
            CheckMember();
        }
    }

    private boolean CheckValue() {
        if (TextUtils.isEmpty(binding.phone.getEditableText().toString())) {
            CommonUtil.showToast("请输入用户名");
            return false;
        }
        return true;
    }

    private void CheckMember() {
        final CheckUser checkUser = new CheckUser();
        checkUser.setUser_name(binding.phone.getEditableText().toString());
        ApiService.CheckMember(getActivity(), new IHttpCallback<CheckMember>() {
            @Override
            public void onNext(HttpResult<CheckMember> t) {
                Bundle bundle = new Bundle();
                bundle.putString("user_name", checkUser.getUser_name());
                //是否存在，0为不存在，1为存在。
                if (t.getResponse_data().getExists() == 0) {
                    //去注册
                    GenericFragmentActivity.start(getActivity(), RegisterFragment.class, bundle);
                } else {
                    //去登录页面
                    GenericFragmentActivity.start(getActivity(), SecondLoginFragment.class, bundle);
                }
                getActivity().finish();
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(checkUser));
    }
}
