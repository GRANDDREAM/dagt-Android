package com.grandream.dagt.fragment.my;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.home.msg.MsgList;
import com.grandream.dagt.bean.my.LenInstiutionBean;
import com.grandream.dagt.databinding.LendinstitutionLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.my.LendAdapter;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;

/**
 * 贷款机构
 * Created by chenjing on 2018/4/17.
 */

public class LendInstitutionFragment extends BaseFragment {
    LendinstitutionLayoutBinding binding;
    LendAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.lendinstitution_layout, container, false);
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
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
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.orgDetial.setLayoutManager(manager);
        getProData();
    }


    public void getProData() {
        ApiService.GetOrg(getActivity(), new IHttpCallback<LenInstiutionBean>() {
            @Override
            public void onNext(HttpResult<LenInstiutionBean> t) {
                adapter = new LendAdapter(getActivity(), t.getResponse_data().getData());
                binding.orgDetial.setAdapter(adapter);
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }
}
