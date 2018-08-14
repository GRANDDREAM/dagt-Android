package com.grandream.dagt.fragment.credit.manage;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.credit.SerciceFeeReslut;
import com.grandream.dagt.databinding.ServeicefeeReslutLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;

/**
 * Created by chenjing on 2018/5/9.
 */

public class ServeiceFeeReslutFragment extends BaseFragment {
    private ServeicefeeReslutLayoutBinding binding;
    private SerciceFeeReslut serciceFeeReslut;
    private Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.serveicefee_reslut_layout, container, false);
        savedInstanceState = getArguments();
        serciceFeeReslut = (SerciceFeeReslut) savedInstanceState.getSerializable("SERCICEFEERESLUT");
        initView();
        return binding.getRoot();
    }

    private void initView() {

        binding.setBean(serciceFeeReslut.getNext_step_info());
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        if (serciceFeeReslut.getSuccess()) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pay_success);
        } else {
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pay_error);
        }
        binding.payStatus.setImageBitmap(bitmap);
    }
}
