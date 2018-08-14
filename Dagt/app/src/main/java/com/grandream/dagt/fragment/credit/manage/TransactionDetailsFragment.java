package com.grandream.dagt.fragment.credit.manage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.credit.TransactionDetailData;
import com.grandream.dagt.databinding.TransactionDetailsLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.credit.CreditDetailBody;
import com.grandream.dagt.utils.Utils;

/**
 * 交易流水详情
 * Created by chenjing on 2018/6/22.
 */

public class TransactionDetailsFragment extends BaseFragment {
    TransactionDetailsLayoutBinding binding;
    private String order_sequence_code, trade_code;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.transaction_details_layout, container, false);
        savedInstanceState = getArguments();
        order_sequence_code = savedInstanceState.getString("ORDER_SEQUENCE_CODE");
        trade_code = savedInstanceState.getString("TRADE_CODE");
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendKeyBackEvent();
            }
        });
        queryPledgeGoods();
        return binding.getRoot();
    }

    private void queryPledgeGoods() {
        CreditDetailBody body = new CreditDetailBody(Utils.getUser_id(getActivity()) + "", order_sequence_code);
        body.setTrade_code(trade_code);
        ApiService.tradeDetail(getContext(), new IHttpCallback<TransactionDetailData>() {
            @Override
            public void onNext(HttpResult<TransactionDetailData> t) {
                binding.setBean(t.getResponse_data().getData().get(0));
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }
}
