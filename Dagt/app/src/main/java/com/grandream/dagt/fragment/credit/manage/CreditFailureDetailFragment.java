package com.grandream.dagt.fragment.credit.manage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.CreditDetail;
import com.grandream.dagt.bean.credit.CreditInfoReslut;
import com.grandream.dagt.bean.credit.CreditListData;
import com.grandream.dagt.databinding.CreditFailureDetailLayoutBinding;
import com.grandream.dagt.databinding.CreditFailureLayoutBinding;
import com.grandream.dagt.databinding.CreditProcessDetailLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.credit.CollateralDataAdapter;
import com.grandream.dagt.fragment.adapter.credit.SuccedCreditAdapter;
import com.grandream.dagt.fragment.credit.LockContractsFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.credit.CreditDetailBody;
import com.grandream.dagt.utils.Utils;

/**
 * 授信失败
 * Created by chenjing on 2018/5/10.
 */

public class CreditFailureDetailFragment extends BaseFragment {
    private CreditFailureDetailLayoutBinding binding;
    private String order_sequence_code;
    private CollateralDataAdapter adapter;
    private int button;
    private CreditDetail creditDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.credit_failure_detail_layout, container, false);
        binding.setClick(this);
        savedInstanceState = getArguments();
        order_sequence_code = savedInstanceState.getString("ORDER_SEQUENCE_CODE");
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        binding.collateral.setLayoutManager(linearLayoutManager);
        getData();
    }

    public void getData() {
        final CreditDetailBody body = new CreditDetailBody(Utils.getUser_id(getActivity()) + "", order_sequence_code);
        ApiService.creditDetail(getActivity(), new IHttpCallback<CreditDetail>() {
            @Override
            public void onNext(HttpResult<CreditDetail> t) {
                creditDetail = t.getResponse_data();
                binding.setBean(creditDetail.getData());
                adapter = new CollateralDataAdapter(getActivity(), t.getResponse_data().getData().getTrade_info());
                binding.collateral.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("ORDER_SEQUENCE_CODE", creditDetail.getData().getOrder_sequence_code());
                        bundle.putString("TRADE_CODE", creditDetail.getData().getTrade_info().get(position).getTrade_code());
                        GenericFragmentActivity.start(getActivity(), TransactionDetailsFragment.class, bundle);
                    }
                });
            }


            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));

    }


}