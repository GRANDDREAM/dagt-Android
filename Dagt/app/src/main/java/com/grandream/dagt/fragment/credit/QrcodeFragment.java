package com.grandream.dagt.fragment.credit;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.zxing.WriterException;
import com.grandream.dagt.R;
import com.grandream.dagt.databinding.QrcodeLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.utils.Utils;

/**
 * Created by chenjing on 2018/4/28.
 */

public class QrcodeFragment extends BaseFragment {
    private QrcodeLayoutBinding binding;
    private String url;
    private String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        savedInstanceState = getArguments();
        url = savedInstanceState.getString("URL");
        type = savedInstanceState.getString("TYPE");
        binding = DataBindingUtil.inflate(inflater, R.layout.qrcode_layout, container, false);
        if (type.equals("LockContracts")) {
            Glide.with(getActivity()).load(url)
                    .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(binding.qrcode);
        } else {
            try {
                Bitmap bitmap = Utils.create2DCode(url);
                binding.qrcode.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        binding.setClick(this);
        return binding.getRoot();
    }

    public void sendBack(View view) {
        getActivity().finish();
    }
}
