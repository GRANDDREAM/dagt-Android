package com.grandream.dagt.fragment.my;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.OperatinData;
import com.grandream.dagt.bean.credit.Qrcode;
import com.grandream.dagt.bean.my.TakeCoinAddressData;
import com.grandream.dagt.databinding.TakeCoinAddressLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.my.MakeCoinAddressAdapter;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.utils.Utils;

/**
 * Created by chenjing on 2018/3/26.
 */

public class TakeCoinAddressFragment extends BaseFragment {
    private TakeCoinAddressLayoutBinding binding;
    protected MakeCoinAddressAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.take_coin_address_layout, container, false);
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
        binding.setClick(this);
    }

    public void getData() {
        parameters.put("user_id", Utils.getUser_id(getActivity()));
        ApiService.AddressData(getActivity(), new IHttpCallback<TakeCoinAddressData>() {
            @Override
            public void onNext(HttpResult<TakeCoinAddressData> t) {
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                binding.cashCoinAddress.setLayoutManager(manager);
                adapter = new MakeCoinAddressAdapter(getActivity(), t.getResponse_data().getData());
                binding.cashCoinAddress.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    public void add(View v) {
        Bundle bunle = new Bundle();
        bunle.putBoolean("ADD", true);
        GenericFragmentActivity.start(getActivity(), EditCoinAddressFragment.class, bunle);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
