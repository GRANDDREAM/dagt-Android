package com.grandream.dagt.fragment.credit.manage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.credit.QueryPledgeData;
import com.grandream.dagt.databinding.QueryPledgeLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.credit.CreditDetailBody;
import com.grandream.dagt.utils.Utils;

/**
 * Created by chenjing on 2018/5/9.
 */

public class QueryPledgeFragment extends BaseFragment {
    private QueryPledgeLayoutBinding binding;
    String order_sequence_code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.query_pledge_layout, container, false);
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
        getData();
    }

    public void getData() {
        CreditDetailBody body = new CreditDetailBody(Utils.getUser_id(getActivity()) + "", order_sequence_code);
        ApiService.queryPledgeGoods(getActivity(), new IHttpCallback<QueryPledgeData>() {
            @Override
            public void onNext(HttpResult<QueryPledgeData> t) {
                binding.setBean(t.getResponse_data().getData());
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }
}
