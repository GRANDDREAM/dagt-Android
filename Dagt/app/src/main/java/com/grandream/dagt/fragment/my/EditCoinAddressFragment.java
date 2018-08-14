package com.grandream.dagt.fragment.my;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.home.msg.MarKAllResult;
import com.grandream.dagt.bean.my.TakeCoinAddressData;
import com.grandream.dagt.databinding.EditCoinaddressLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

/**
 * Created by chenjing on 2018/5/2.
 */

public class EditCoinAddressFragment extends BaseFragment {
    private EditCoinaddressLayoutBinding binding;
    private TakeCoinAddressData.DataBean bean;
    private boolean ADD = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_coinaddress_layout, container, false);
        binding.setClick(this);
        savedInstanceState = getArguments();
        bean = (TakeCoinAddressData.DataBean) savedInstanceState.getSerializable("DATAINFO");
        ADD = savedInstanceState.getBoolean("ADD");
        if (!ADD) {
            binding.setBean(bean);
        } else {
            binding.addLin.setVisibility(View.GONE);
        }
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
        binding.navigation.setRightText("保存");
        binding.navigation.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ADD) {
                    ADD();
                } else {
                    Save();
                }
            }
        });
    }

    private void ADD() {
        bean = new TakeCoinAddressData.DataBean();
        bean.setUser_id(Utils.getUser_id(getActivity()) + "");
        bean.setCoin_address_name(binding.coinAddressName.getEditableText().toString());
        bean.setCoin_address(binding.coinAddress.getEditableText().toString());
        ApiService.AddAddress(getActivity(), new IHttpCallback<MarKAllResult>() {
            @Override
            public void onNext(HttpResult<MarKAllResult> t) {
                //0为更新失败，1为更新成功。
                if (t.getResponse_data().getSuccess() == 1) {
                    sendKeyBackEvent();
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(bean));
    }

    private void Save() {
        bean.setUser_id(Utils.getUser_id(getActivity()) + "");
        bean.setCoin_address_name(binding.coinAddressName.getEditableText().toString());
        bean.setCoin_address(binding.coinAddress.getEditableText().toString());
        ApiService.updateAddress(getActivity(), new IHttpCallback<MarKAllResult>() {
            @Override
            public void onNext(HttpResult<MarKAllResult> t) {
                CommonUtil.showToast(t.getResponse_text());
                //0为更新失败，1为更新成功。
                if (t.getResponse_data().getSuccess() == 1) {
                    inputGone();
                    sendKeyBackEvent();
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(bean));
    }

    public void delete(View view) {
        bean = new TakeCoinAddressData.DataBean();
        bean.setUser_id(Utils.getUser_id(getActivity()) + "");
        bean.setCoin_address_name(binding.coinAddressName.getEditableText().toString());
        bean.setCoin_address(binding.coinAddress.getEditableText().toString());
        ApiService.deleteAddress(getActivity(), new IHttpCallback<MarKAllResult>() {
            @Override
            public void onNext(HttpResult<MarKAllResult> t) {
                CommonUtil.showToast(t.getResponse_text());
                //0为更新失败，1为更新成功。
                if (t.getResponse_data().getSuccess() == 1) {
                    inputGone();
                    getActivity().finish();
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(bean));
    }

    public void inputGone() {

    }

}
