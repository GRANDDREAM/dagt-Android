package com.grandream.dagt.fragment.my;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.my.AboutUsBean;
import com.grandream.dagt.bean.my.LenInstiutionBean;
import com.grandream.dagt.databinding.AboutUsLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;

/**
 * Created by chenjing on 2018/3/28.
 */

public class AboutUSFragment extends BaseFragment {
    private AboutUsLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.about_us_layout, container, false);
        binding.setClick(this);
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        getData();
        return binding.getRoot();
    }

    /**
     * 去公司官网
     *
     * @param view
     */
    public void toCompantyWebsite(View view) {

    }

    public void getData() {
        ApiService.AboutUs(getActivity(), new IHttpCallback<AboutUsBean>() {
            @Override
            public void onNext(HttpResult<AboutUsBean> t) {
                binding.setBean(t.getResponse_data());
            }

            @Override
            public void onError(int code, String message) {

            }
        },parameters);
    }
}
