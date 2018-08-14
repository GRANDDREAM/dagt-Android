package com.grandream.dagt.fragment.credit.manage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.credit.AddReslutData;
import com.grandream.dagt.databinding.AddReslutaLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;

/**
 * 追加结果页面
 * Created by chenjing on 2018/5/9.
 */

public class AddReslutaFragment extends BaseFragment {
    private AddReslutaLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_resluta_layout, container, false);
        savedInstanceState = getArguments();
        AddReslutData.NextStepInfoBean nextStepInfoBean = (AddReslutData.NextStepInfoBean) savedInstanceState.getSerializable("NEXT_STEP_INFO");
        binding.setBean(nextStepInfoBean);
        binding.setClick(this);
        return binding.getRoot();
    }

    public void Commit(View view) {
        getActivity().finish();
    }
}
