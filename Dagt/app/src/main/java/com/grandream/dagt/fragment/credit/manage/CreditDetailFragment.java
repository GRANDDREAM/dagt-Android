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
import com.grandream.dagt.databinding.CreditdetailLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.credit.CollateralDataAdapter;
import com.grandream.dagt.fragment.my.CommonProblemFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.credit.CreditDetailBody;
import com.grandream.dagt.utils.Utils;

import retrofit2.http.POST;

/**
 * 授信详情
 * Created by chenjing on 2018/5/8.
 */

public class CreditDetailFragment extends BaseFragment {
    private CreditdetailLayoutBinding binding;
    String order_sequence_code;
    private CollateralDataAdapter adapter;
    int show_warning;
    int rule_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.creditdetail_layout, container, false);
        savedInstanceState = getArguments();
        order_sequence_code = savedInstanceState.getString("ORDER_SEQUENCE_CODE");
        show_warning = savedInstanceState.getInt("SHOW_WARNING");
        binding.setClick(this);
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
        binding.navigation.setRightText("规则");
        if (show_warning == 1) {
            binding.pledgedRate.setTextColor(this.getResources().getColor(R.color.red));
        } else {
            binding.pledgedRate.setTextColor(this.getResources().getColor(R.color.black));
        }
        binding.navigation.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("RULE_ID", rule_id);
                GenericFragmentActivity.start(getActivity(), CommonProblemFragment.class, bundle);
            }
        });
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
        CreditDetailBody body = new CreditDetailBody(Utils.getUser_id(getActivity()) + "", order_sequence_code);
        ApiService.creditDetail(getActivity(), new IHttpCallback<CreditDetail>() {
            @Override
            public void onNext(final HttpResult<CreditDetail> t) {
                binding.setBean(t.getResponse_data().getData());
                rule_id = t.getResponse_data().getRule_id();
                adapter = new CollateralDataAdapter(getActivity(), t.getResponse_data().getData().getTrade_info());
                binding.collateral.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("ORDER_SEQUENCE_CODE", t.getResponse_data().getData().getOrder_sequence_code());
                        bundle.putString("TRADE_CODE", t.getResponse_data().getData().getTrade_info().get(position).getTrade_code());
                        GenericFragmentActivity.start(getActivity(), TransactionDetailsFragment.class, bundle);
                    }
                });
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));

    }

    public void AddPledgeFragment(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("ORDER_SEQUENCE_CODE", order_sequence_code);
        GenericFragmentActivity.start(getActivity(), AdditionalPledgeFragment.class, bundle);
        getActivity().finish();
    }

    public void QueryPledge(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("ORDER_SEQUENCE_CODE", order_sequence_code);
        GenericFragmentActivity.start(getActivity(), QueryPledgeFragment.class, bundle);
    }

    public void payServerFee(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("ORDER_SEQUENCE_CODE", order_sequence_code);
        GenericFragmentActivity.start(getActivity(), PaycreditServiceFeeFragment.class, bundle);
        getActivity().finish();
    }

}
