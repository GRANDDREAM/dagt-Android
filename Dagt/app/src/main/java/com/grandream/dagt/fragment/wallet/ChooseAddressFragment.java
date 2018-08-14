package com.grandream.dagt.fragment.wallet;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.wallet.DrawDetailBean;
import com.grandream.dagt.databinding.ChooseAddressLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.wallet.ChooseAddressAdapter;
import com.grandream.dagt.utils.Utils;

/**
 * 选择提币地址
 * Created by chenjing on 2018/5/14.
 */

public class ChooseAddressFragment extends BaseFragment {
    private static final String ERRORFRAGMENT = "CHOOSEADDRESSFRAGMENT ERROR";
    private ChooseAddressLayoutBinding binding;
    private DrawDetailBean bean;
    ChooseAddressAdapter addressAdapter;
    private String wallt_address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.choose_address_layout, container, false);
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
        binding.navigation.setRightText("确定");
        binding.navigation.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(wallt_address)) {
                    Utils.ShowDialog("请选择一个地址", ERRORFRAGMENT, ChooseAddressFragment.this, getActivity());
                    return;
                } else {
                    chooseClickListener.sendData(wallt_address);
                    dismissSelf();
                }
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.adress.setLayoutManager(manager);
        addressAdapter = new ChooseAddressAdapter(getActivity(), bean.getUser_draw_coin_address());
        binding.adress.setAdapter(addressAdapter);
        addressAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                wallt_address = bean.getUser_draw_coin_address().get(position).getCoin_address();
            }
        });
    }


    private ChooseClickListener chooseClickListener;

    public void setChooseClickListener(ChooseClickListener chooseClickListener, DrawDetailBean bean) {
        this.bean = bean;
        this.chooseClickListener = chooseClickListener;
    }

    public interface ChooseClickListener {
        public void sendData(String wall_address);
    }
}
