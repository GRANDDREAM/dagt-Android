package com.grandream.dagt.fragment.wallet;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.zxing.WriterException;
import com.grandream.dagt.R;
import com.grandream.dagt.bean.login.CheckMember;
import com.grandream.dagt.bean.login.CountryCode;
import com.grandream.dagt.bean.wallet.WalletIndext;
import com.grandream.dagt.databinding.BackupkeystoreLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.ui.dialog.DialogClick;
import com.grandream.dagt.ui.dialog.PasswordAlertGetMsgDialog;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;
import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * 备份私钥
 * Created by chenjing on 2018/5/7.
 */

public class BackupKeystoreFragment extends BaseFragment {
    private BackupkeystoreLayoutBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.backupkeystore_layout, container, false);
        binding.setClick(this);
        initView();
        getPoprtText();
        return binding.getRoot();
    }

    private void initView() {
        binding.userRl.setVisibility(View.VISIBLE);
        binding.keyRl.setVisibility(View.GONE);
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        getUserLogo();
        getCountryCode();
    }

    private void getCountryCode() {
        ApiService.getCountryCode(getContext(), new IHttpCallback<CountryCode>() {

            @Override
            public void onNext(HttpResult<CountryCode> t) {
                binding.tel.setText(t.getResponse_data().getCountry_code() + Utils.getUser_name(getContext()));
                binding.userTel.setText(t.getResponse_data().getCountry_code() + Utils.getUser_name(getContext()));
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    public void getUserLogo() {
        parameters.put("user_name", Utils.getUser_name(getContext()));
        ApiService.getUserLogo(getContext(), new IHttpCallback<CheckMember>() {
            @Override
            public void onNext(HttpResult<CheckMember> t) {
                if (!TextUtils.isEmpty(
                        t.getResponse_data().getLogo())) {
                    Glide.with(getActivity()).load(t.getResponse_data().getLogo())
                            .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(binding.userImg);
                    Glide.with(getActivity()).load(t.getResponse_data().getLogo())
                            .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(binding.userImg1);
                }

            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    public void getKeyStore(View v) {
        PasswordAlertGetMsgDialog dialog = new PasswordAlertGetMsgDialog(getActivity());
        dialog.builder()
                .setCanceledOnTouchOutside(false)
                .setPositiveButton("获取私钥",
                        new DialogClick() {
                            @Override
                            public void onDiaLogClick(String value) {
                                if (TextUtils.isEmpty(value)) {
                                    CommonUtil.showToast("获取失败");
                                    return;
                                } else {
                                    Bitmap bitmap = null;
                                    try {
                                        binding.userRl.setVisibility(View.GONE);
                                        binding.keyRl.setVisibility(View.VISIBLE);
                                        bitmap = Utils.create2DCode(value);
                                        binding.qrcode.setImageBitmap(bitmap);
                                        binding.key.setText(value);
                                    } catch (WriterException e) {
                                        e.printStackTrace();
                                    }
                                }
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

    public void getPoprtText() {
        ApiService.backupKey(getContext(), new IHttpCallback<WalletIndext>() {
            @Override
            public void onNext(HttpResult<WalletIndext> t) {
                binding.setBean(t.getResponse_data());
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }
}
