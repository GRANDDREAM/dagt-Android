package com.grandream.dagt.fragment.wallet;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.grandream.dagt.R;
import com.grandream.dagt.bean.wallet.RechargeBean;
import com.grandream.dagt.databinding.WalletRechargeLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.WalletCoinDetailBody;
import com.grandream.dagt.utils.Utils;

/**
 * 充值
 * Created by chenjing on 2018/5/11.
 */

public class WalletRechargeFragment extends BaseFragment {
    private WalletRechargeLayoutBinding binding;
    private String coin_alias_name;
    private ClipData mClipData;
    private ClipboardManager mClipboardManager;
    private RechargeBean bean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.wallet_recharge_layout, container, false);
        savedInstanceState = getArguments();
        coin_alias_name = savedInstanceState.getString("COIN_ALIAS_NAME");
        initView();
        binding.setClick(this);
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

    public void copy(View v) {
        mClipData = ClipData.newPlainText("Simple test", bean.getCoin_address());
        //把clip对象放在剪贴板中
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(mClipData);
        Toast.makeText(getActivity(), "文本已经复制成功",
                Toast.LENGTH_SHORT).show();
    }

    public void getData() {
        WalletCoinDetailBody body = new WalletCoinDetailBody(Utils.getUser_id(getActivity()) + "", coin_alias_name);
        ApiService.rechargeCoin(getActivity(), new IHttpCallback<RechargeBean>() {
            @Override
            public void onNext(HttpResult<RechargeBean> t) {
                bean = t.getResponse_data();
                try {
                    binding.setBean(bean);
                    Bitmap bitmap = Utils.create2DCode(t.getResponse_data().getCoin_address());
                    binding.qrcode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }
}
