package com.grandream.dagt.fragment.wallet;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.wallet.CoinDetail;
import com.grandream.dagt.databinding.CoinDetailLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.wallet.WalletCoinDetailAdapter;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.WalletCoinDetailBody;
import com.grandream.dagt.utils.Utils;

/**
 * 持币详情
 * Created by chenjing on 2018/5/10.
 */

public class CoinDetailFragment extends BaseFragment {
    private CoinDetailLayoutBinding binding;
    private String coin_alias_name;
    private WalletCoinDetailAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.coin_detail_layout, container, false);
        savedInstanceState = getArguments();
        coin_alias_name = savedInstanceState.getString("COIN_ALIAS_NAME");
        initView();
        binding.setClick(this);
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
        binding.recordRcy.setLayoutManager(manager);
        getData();
    }

    public void Dissaving(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("COIN_ALIAS_NAME", coin_alias_name);
        GenericFragmentActivity.start(getActivity(), DrawCoinFragment.class, bundle);

    }

    public void Recharge(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("COIN_ALIAS_NAME", coin_alias_name);
        GenericFragmentActivity.start(getActivity(), WalletRechargeFragment.class, bundle);
    }

    public void getData() {
        final WalletCoinDetailBody body = new WalletCoinDetailBody(Utils.getUser_id(getActivity()) + "", coin_alias_name);
        ApiService.ownerCoinDetail(getActivity(), new IHttpCallback<CoinDetail>() {
            @Override
            public void onNext(final HttpResult<CoinDetail> t) {
                binding.setBean(t.getResponse_data());
                if (t.getResponse_data().getTrade_info().size() != 0) {
                    adapter = new WalletCoinDetailAdapter(getActivity(), t.getResponse_data().getTrade_info());
                    binding.recordRcy.setAdapter(adapter);
                    adapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Bundle bundle = new Bundle();
                            bundle.putString("COIN_ALIAS_NAME", coin_alias_name);
                            bundle.putSerializable("TRADE_INFO", t.getResponse_data().getTrade_info().get(position));
                            GenericFragmentActivity.start(getActivity(), TradRecordDetailFragment.class, bundle);
                        }
                    });
                }

            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }
}
