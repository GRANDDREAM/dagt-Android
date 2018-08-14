package com.grandream.dagt.fragment.credit.manage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.CreditListData;
import com.grandream.dagt.databinding.CreditsucceedLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.credit.SuccedCreditAdapter;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.utils.Utils;

/**
 * 授信成功
 * Created by chenjing on 2018/5/7.
 */

public class CreditSucceedFragment extends BaseFragment {
    private CreditsucceedLayoutBinding binding;
    private SuccedCreditAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.creditsucceed_layout, container, false);
        initView();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.creditSucccedRcy.setLayoutManager(manager);
    }


    public void getData() {
        parameters.put("user_id", Utils.getUser_id(getActivity()));
        parameters.put("page_size", 1);
        parameters.put("page_number", 1000000);
        parameters.put("label", 1);
        ApiService.CreditManagesIndex(getActivity(), new IHttpCallback<CreditListData>() {
            @Override
            public void onNext(final HttpResult<CreditListData> t) {
                adapter = new SuccedCreditAdapter(getActivity(), t.getResponse_data().getData().getFormat_order_info(), "1");
                binding.creditSucccedRcy.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("SHOW_WARNING", t.getResponse_data().getData().getFormat_order_info().get(position).getShow_warning());
                        bundle.putString("ORDER_SEQUENCE_CODE", t.getResponse_data().getData().getFormat_order_info().get(position).getOrder_sequence_code());
                        GenericFragmentActivity.start(getActivity(), CreditDetailFragment.class, bundle);
                    }
                });
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }
}
