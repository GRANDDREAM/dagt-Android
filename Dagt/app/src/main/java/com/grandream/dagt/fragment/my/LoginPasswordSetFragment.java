package com.grandream.dagt.fragment.my;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.my.ResetLogPwdBean;
import com.grandream.dagt.databinding.LoginPasswordSetLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.user.ResetLogPwd;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

/**
 * 登录密码设置
 * Created by chenjing on 2018/3/28.
 */

public class LoginPasswordSetFragment extends BaseFragment {
    private LoginPasswordSetLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_password_set_layout, container, false);
        binding.setClick(this);
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        return binding.getRoot();
    }

    /**
     * 保存
     *
     * @param view
     */
    public void SaveClick(View view) {
        if (CheckValue()) {
            doReset();
        }
    }

    private boolean CheckValue() {
        if (TextUtils.isEmpty(binding.oldPwdEd.getEditableText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.reset_input_pwd));
            return false;
        }
        if (TextUtils.isEmpty(binding.newPwdEd.getEditableText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.reset_input_newpwd));
            return false;
        }
        if (!binding.newPwdEd.getEditableText().toString().equals(binding.pwdEd.getEditableText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.error_inconsistent));
            return false;
        }

        return true;
    }

    private void doReset() {
        ResetLogPwd resetLogPwd = new ResetLogPwd(Utils.getUser_id(getActivity()), binding.oldPwdEd.getEditableText().toString(), binding.newPwdEd.getEditableText().toString(), binding.pwdEd.getEditableText().toString());
        ApiService.ResetLoginPwd(getActivity(), new IHttpCallback<ResetLogPwdBean>() {
            @Override
            public void onNext(HttpResult<ResetLogPwdBean> t) {
                if (t.getResponse_data().getReset() == 0) {
                    CommonUtil.showToast("设置失败");
                } else {
                    CommonUtil.showToast("设置成功");
                    getActivity().finish();
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(resetLogPwd));
    }

}
