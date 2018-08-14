package com.grandream.dagt.fragment.credit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.CoinData;
import com.grandream.dagt.bean.credit.CreditInfo;
import com.grandream.dagt.databinding.ApplyForCreditLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.credit.CoinRecyCleryAdapter;
import com.grandream.dagt.fragment.my.CommonProblemFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.credit.SendCreditInfo;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

/**
 * Created by chenjing on 2018/4/17.
 */

public class ApplyForCreditFragment extends BaseFragment {
    private ApplyForCreditLayoutBinding binding;
    private CoinRecyCleryAdapter adapter;
    private String address;
    private CoinData coinData;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.apply_for_credit_layout, container, false);
        binding.setClick(this);
        initView();
        getData();
        return binding.getRoot();
    }


    private void initView() {
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        binding.navigation.setRightText("授信规则");
        binding.navigation.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("RULE_ID", coinData.getRule_id());
                GenericFragmentActivity.start(getActivity(), CommonProblemFragment.class, bundle);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.coinRecy.setLayoutManager(manager);
        binding.userDagtWalletAddressCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.userDagtWalletAddressCb.setChecked(true);
                    binding.userOtherWalletAddressCb.setChecked(false);
                } else {
                    binding.userDagtWalletAddressCb.setChecked(false);
                }
            }
        });
        binding.userOtherWalletAddressCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.userDagtWalletAddressCb.setChecked(false);
                    binding.userOtherWalletAddressCb.setChecked(true);
                } else {
                    binding.userOtherWalletAddressCb.setChecked(false);
                }
            }
        });

    }


    //获取数据
    public void getData() {
        parameters.put("user_id", Utils.getUser_id(getActivity()));
        ApiService.CapitalEvaluation(getActivity(), new IHttpCallback<CoinData>() {
            @Override
            public void onNext(HttpResult<CoinData> t) {
                coinData = t.getResponse_data();
                binding.setBean(t.getResponse_data());
                id = coinData.getCoin_data().get(0).getId();
                adapter = new CoinRecyCleryAdapter(getActivity(), t.getResponse_data().getCoin_data());
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        id = coinData.getCoin_data().get(position).getId();
                    }
                });
                binding.coinRecy.setAdapter(adapter);
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }


    /**
     * 下一步
     *
     * @param view
     */
    public void NextClick(View view) {
        if (isChckValue()) {
            SendData();
        }
    }

    /**
     * 发送请求
     */
    private void SendData() {
        SendCreditInfo send = new SendCreditInfo();
        send.setUser_id(Utils.getUser_id(getActivity()));
        send.setCoin_id(id);
        send.setWallet_address(address);
        if (binding.userOtherWalletAddressCb.isChecked()) {
            send.setWallet_type("person");
        } else {
            send.setWallet_type("dagt");
        }
        ApiService.CreditInfo(getActivity(), new IHttpCallback<CreditInfo>() {
            @Override
            public void onNext(HttpResult<CreditInfo> t) {
                CreditInfo creditInfo = t.getResponse_data();
                Bundle bundle = new Bundle();
                bundle.putSerializable("CREDITINFO", creditInfo);
                bundle.putInt("RULE_ID", coinData.getRule_id());
                GenericFragmentActivity.start(getActivity(), CreditInformationFragment.class, bundle);
                getActivity().finish();
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(send));
    }


    public boolean isChckValue() {
        if (TextUtils.isEmpty(id)) {
            CommonUtil.showToast("请选择您要抵押的币");
            return false;
        }
        if (!binding.userDagtWalletAddressCb.isChecked() && !binding.userOtherWalletAddressCb.isChecked()) {
            CommonUtil.showToast("请选择一个钱包地址");
            return false;
        }
        if (binding.userDagtWalletAddressCb.isChecked()) {
            address = binding.userDagtWalletAddress.getText().toString();
        }
        if (binding.userOtherWalletAddressCb.isChecked()) {
            address = binding.userOtherWalletAddress.getEditableText().toString();
        }
        if (TextUtils.isEmpty(address)) {
            CommonUtil.showToast("请输入钱包地址");
            return false;
        }
        return true;
    }
}
