package com.grandream.dagt.fragment.wallet;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.WriterException;
import com.grandream.dagt.R;
import com.grandream.dagt.databinding.DagtAdQrcodeLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.utils.Utils;

/**
 * Created by chenjing on 2018/4/28.
 */

public class DagtAddressQrcodeFragment extends BaseFragment {
    private DagtAdQrcodeLayoutBinding binding;
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        savedInstanceState = getArguments();
        url = savedInstanceState.getString("URL");
        binding = DataBindingUtil.inflate(inflater, R.layout.dagt_ad_qrcode_layout, container, false);
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        try {
            binding.verifyCode.setText(url);
            Bitmap bitmap = Utils.create2DCode(url);
            binding.qrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return binding.getRoot();
    }

    public void sendBack(View view) {
        sendKeyBackEvent();
    }
}
