package com.grandream.dagt.fragment.my;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grandream.dagt.R;
import com.grandream.dagt.activity.MainActivity;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.login.CheckMember;
import com.grandream.dagt.bean.login.UserInfo;
import com.grandream.dagt.databinding.MyLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.user.LogingFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.my.Userlogo;
import com.grandream.dagt.utils.Utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的 设置
 * Created by chenjing on 2018/3/22.
 */

public class MyDetailFragment extends BaseFragment {
    private MyLayoutBinding binding;
    private String telephone;
    private String imageFilePath;
    private FileInputStream fis;
    private List<String> imgpath = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.my_layout, container, false);
        binding.setClick(this);
        telephone = Utils.getUser_name(getActivity());
        binding.tel.setText(Utils.replaceToXing(telephone, 4, telephone.length() - 4));
        getUserLogo();
        return binding.getRoot();
    }

    public void getUserLogo() {
        parameters.put("user_name", telephone);
        ApiService.getUserLogo(getContext(), new IHttpCallback<CheckMember>() {
            @Override
            public void onNext(HttpResult<CheckMember> t) {
                if (!TextUtils.isEmpty(
                        t.getResponse_data().getLogo())) {
                    Glide.with(getActivity()).load(t.getResponse_data().getLogo())
                            .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(binding.userImg);
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    public void Loging(View view) {
        GenericFragmentActivity.start(getActivity(), LogingFragment.class, new Bundle());
    }

    /**
     * 系統設置
     *
     * @param v
     */
    public void Sys_setting_Click(View v) {
        GenericFragmentActivity.start(getActivity(), SysSetFragment.class, new Bundle());
    }

    /**
     * 提笔地址
     *
     * @param v
     */
    public void Take_Coin_Address_Click(View v) {
        GenericFragmentActivity.start(getActivity(), TakeCoinAddressFragment.class, new Bundle());
    }

    /**
     * 常見問題
     *
     * @param v
     */
    public void Common_Problem_Click(View v) {
        GenericFragmentActivity.start(getActivity(), CommonProblemFragment.class, new Bundle());
    }

    /**
     * 贷款机构
     *
     * @param v
     */
    public void Len_Institutions_Click(View v) {
        GenericFragmentActivity.start(getActivity(), LendInstitutionFragment.class, new Bundle());
    }

    /**
     * 关于我们
     *
     * @param v
     */
    public void About_Us_Click(View v) {
        GenericFragmentActivity.start(getActivity(), AboutUSFragment.class, new Bundle());
    }

    public void chooseImg(View view) {
        requestPower();
        showCustomAlertDialog(getActivity());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (requestCode == IMAGE_CODE && resultCode == getActivity().RESULT_OK
                && data != null) {
            try {
                Uri uri = data.getData();
                imageFilePath = getPath(getActivity(), uri);
                //判断图片大小
                fis = new FileInputStream(imageFilePath);
//                if (fis.available() / 1000 <= 3072) {
//
//                }
                Glide.with(getActivity()).load(imageFilePath)
                        .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(binding.userImg);
                fis.close();
                userLogo();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == CAMERA_INTENT_REQUEST
                && resultCode == getActivity().RESULT_OK && data != null) {
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void userLogo() {
        Userlogo body = new Userlogo(Utils.getUser_id(getActivity()), Utils.bitmapToBase64(BitmapFactory.decodeFile(imageFilePath)), "jpg");
        ApiService.userLogo(getActivity(), new IHttpCallback<UserInfo>() {
            @Override
            public void onNext(HttpResult<UserInfo> t) {
                getUserLogo();
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!Utils.isLogin(getContext())) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeFragment(0);
            mainActivity.select(0);
        }
    }

    public void requestPower() {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限
            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,}, 1);
            }
        }
    }
}
