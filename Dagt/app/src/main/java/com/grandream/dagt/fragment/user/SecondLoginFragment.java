package com.grandream.dagt.fragment.user;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.login.CheckMember;
import com.grandream.dagt.bean.login.CountryCode;
import com.grandream.dagt.bean.login.UserInfo;
import com.grandream.dagt.databinding.SecondLoginLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.user.DoLogin;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 第二次登录
 * Created by chenjing on 2018/3/27.
 */

public class SecondLoginFragment extends BaseFragment {
    private SecondLoginLayoutBinding binding;
    private String user_name;
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.second_login_layout, container, false);
        savedInstanceState = getArguments();
        user_name = savedInstanceState.getString("user_name");
        getCountryCode();
        getUserLogo();
        binding.setClick(this);
        return binding.getRoot();
    }

    public void sendBack(View view) {
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

    public void getUserLogo() {
        parameters.put("user_name", user_name);
        ApiService.getUserLogo(getContext(), new IHttpCallback<CheckMember>() {
            @Override
            public void onNext(HttpResult<CheckMember> t) {
                url = t.getResponse_data().getLogo();
                Glide.with(getActivity()).load(t.getResponse_data().getLogo())
                        .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(binding.userImg);
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }


    public void doLogin() {
        DoLogin doLogin = new DoLogin();
        doLogin.setUser_name(user_name);
        doLogin.setPassword(binding.passowrd.getEditableText().toString());

        ApiService.DoLogin(getActivity(), new IHttpCallback<UserInfo>() {
            @Override
            public void onNext(HttpResult<UserInfo> t) {
                UserInfo userInfo = t.getResponse_data();
                Utils.saveUserStatus(getActivity(), userInfo.getId(), userInfo.getUser_name(), userInfo.getCountry(), userInfo.getAvatar());
                setTagAndAlias();
                CommonUtil.showToast("登录成功");
                getActivity().finish();
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(doLogin));
    }

    /**
     * 设置标签与别名
     */
    private void setTagAndAlias() {
        /**
         *这里设置了别名，在这里获取的用户登录的信息
         *并且此时已经获取了用户的userId,然后就可以用用户的userId来设置别名了
         **/
        //false状态为未设置标签与别名成功
        //if (UserUtils.getTagAlias(getHoldingActivity()) == false) {
        Set<String> tags = new HashSet<String>();
        //这里可以设置你要推送的人，一般是用户uid 不为空在设置进去 可同时添加多个
        if (!TextUtils.isEmpty(Utils.getUser_id(getActivity()) + "")) {
            tags.add(Utils.getUser_id(getActivity()) + "");//设置tag
        }
        //上下文、别名【Sting行】、标签【Set型】、回调
        JPushInterface.setAliasAndTags(getActivity(), Utils.getUser_id(getActivity()) + "", tags,
                mAliasCallback);
        // }
    }


    /**
     * /**
     * TagAliasCallback类是JPush开发包jar中的类，用于
     * 设置别名和标签的回调接口，成功与否都会回调该方法
     * 同时给定回调的代码。如果code=0,说明别名设置成功。
     * /**
     * 6001   无效的设置，tag/alias 不应参数都为 null
     * 6002   设置超时    建议重试
     * 6003   alias 字符串不合法    有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。
     * 6004   alias超长。最多 40个字节    中文 UTF-8 是 3 个字节
     * 6005   某一个 tag 字符串不合法  有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。
     * 6006   某一个 tag 超长。一个 tag 最多 40个字节  中文 UTF-8 是 3 个字节
     * 6007   tags 数量超出限制。最多 100个 这是一台设备的限制。一个应用全局的标签数量无限制。
     * 6008   tag/alias 超出总长度限制。总长度最多 1K 字节
     * 6011   10s内设置tag或alias大于3次 短时间内操作过于频繁
     **/
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    //这里可以往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    //UserUtils.saveTagAlias(getHoldingActivity(), true);
                    logs = "Set tag and alias success极光推送别名设置成功";
                    Log.e("TAG", logs);
                    break;
                case 6002:
                    //极低的可能设置失败 我设置过几百回 出现3次失败 不放心的话可以失败后继续调用上面那个方面 重连3次即可 记得return 不要进入死循环了...
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.极光推送别名设置失败，60秒后重试";
                    Log.e("TAG", logs);
                    break;
                default:
                    logs = "极光推送设置失败，Failed with errorCode = " + code;
                    Log.e("TAG", logs);
                    break;
            }
        }
    };

    /**
     * 切换账号
     *
     * @param view
     */
    public void SwitchAccount(View view) {
        GenericFragmentActivity.start(getActivity(), LogingFragment.class, new Bundle());
        getActivity().finish();
    }

    /**
     * 登录按钮
     *
     * @param view
     */
    public void NextClick(View view) {
        if (CheckValue()) {
            doLogin();
        }

    }


    /**
     * 忘记密码
     *
     * @param view
     */
    public void ForgotPassword(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("user_name", user_name);
        GenericFragmentActivity.start(getActivity(), ForgotPasswordFragment.class, bundle);
        getActivity().finish();
    }


    private boolean CheckValue() {
        if (TextUtils.isEmpty(binding.passowrd.getEditableText().toString())) {
            CommonUtil.showToast("请输入密码");
            return false;
        }
        return true;
    }

}
