package com.grandream.dagt.fragment.wallet;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.wallet.CoinDetail;
import com.grandream.dagt.bean.wallet.TradBean;
import com.grandream.dagt.databinding.TradRecordDetailLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.WalletCoinDetailBody;
import com.grandream.dagt.utils.Utils;

/**
 * 交易历史记录详情
 * Created by chenjing on 2018/5/11.
 */

public class TradRecordDetailFragment extends BaseFragment {
    private TradRecordDetailLayoutBinding binding;
    private CoinDetail.TradeInfoBean tradeInfoBean;
    private String coin_alias_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.trad_record_detail_layout, container, false);
        savedInstanceState = getArguments();
        coin_alias_name = savedInstanceState.getString("COIN_ALIAS_NAME");
        tradeInfoBean = (CoinDetail.TradeInfoBean) savedInstanceState.getSerializable("TRADE_INFO");
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
        getData();
    }

    public void getData() {
        final WalletCoinDetailBody body = new WalletCoinDetailBody(Utils.getUser_id(getActivity()) + "", coin_alias_name, tradeInfoBean.getChain_trade_code());
        ApiService.rechargeCoinDetail(getActivity(), new IHttpCallback<TradBean>() {
            @Override
            public void onNext(HttpResult<TradBean> t) {
                binding.setBean(t.getResponse_data());
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }
}
