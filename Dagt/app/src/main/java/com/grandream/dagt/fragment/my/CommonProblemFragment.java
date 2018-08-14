package com.grandream.dagt.fragment.my;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.my.CommProBean;
import com.grandream.dagt.bean.wallet.WalletIndext;
import com.grandream.dagt.databinding.CommonproblemLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.my.CommProBlemAdapter;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;

/**
 * Created by chenjing on 2018/5/22.
 */

public class CommonProblemFragment extends BaseFragment {
    private CommonproblemLayoutBinding binding;
    private CommProBlemAdapter adapter;
    private int rule_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.commonproblem_layout, container, false);
        rule_id = getArguments().getInt("RULE_ID");
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
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.problem.setLayoutManager(manager);
        getData();
    }

    public void getData() {
        parameters.put("page_size", 1);
        parameters.put("page_number", 1000000);
        parameters.put("rule_id", rule_id);
        ApiService.faqsIndex(getActivity(), new IHttpCallback<CommProBean>() {
            @Override
            public void onNext(HttpResult<CommProBean> t) {
                adapter = new CommProBlemAdapter(getActivity(), t.getResponse_data().getData());
                binding.problem.setAdapter(adapter);
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }
}
