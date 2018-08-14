package com.grandream.dagt.fragment.credit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.credit.AgainReslut;
import com.grandream.dagt.bean.credit.NextStepInfoBean;
import com.grandream.dagt.databinding.CreditinformationLayoutBinding;
import com.grandream.dagt.databinding.CreditresultLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;

/**
 * 授信结果
 * Created by chenjing on 2018/4/18.
 */

public class CreditResultFragment extends BaseFragment {
    private CreditresultLayoutBinding binding;
    private NextStepInfoBean next_step_info;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.creditresult_layout, container, false);
        savedInstanceState=getArguments();
        next_step_info= (NextStepInfoBean) savedInstanceState.getSerializable("NEXT_STEP_INFO");
        binding.setBean(next_step_info);
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        return binding.getRoot();
    }
}
