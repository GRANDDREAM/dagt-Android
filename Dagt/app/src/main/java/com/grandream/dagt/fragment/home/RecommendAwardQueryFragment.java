package com.grandream.dagt.fragment.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.home.HomeCoinTrendBean;
import com.grandream.dagt.bean.home.share.QueryShare;
import com.grandream.dagt.databinding.RecommendawardqueryLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.share.RecommendAdapter;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.utils.Utils;

/**
 * Created by chenjing on 2018/7/4.
 */

public class RecommendAwardQueryFragment extends BaseFragment {
    RecommendawardqueryLayoutBinding binding;
    RecommendAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.recommendawardquery_layout, container, false);
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
        binding.firendCry.setLayoutManager(manager);
        parameters.put("user_id", Utils.getUser_id(getContext()));
        parameters.put("page", 1);
        parameters.put("size", 10000);
        ApiService.QueryRecommendReward(getContext(), new IHttpCallback<QueryShare>() {
            @Override
            public void onNext(HttpResult<QueryShare> t) {
                binding.setBean(t.getResponse_data());
                adapter = new RecommendAdapter(getContext(), t.getResponse_data().getData());
                binding.firendCry.setAdapter(adapter);
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }
}
