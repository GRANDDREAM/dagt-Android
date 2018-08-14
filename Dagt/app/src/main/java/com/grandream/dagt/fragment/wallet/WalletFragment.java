package com.grandream.dagt.fragment.wallet;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.wallet.WalletIndext;
import com.grandream.dagt.databinding.WalletLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.wallet.WalletIndexCoinAdapter;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.utils.AES;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

/**
 * 钱包地址
 * Created by chenjing on 2018/4/16.
 */

public class WalletFragment extends BaseFragment {
    private WalletLayoutBinding binding;
    private WalletIndext bean;
    private ClipData mClipData;
    private ClipboardManager mClipboardManager;
    private WalletIndexCoinAdapter adapter;
    private String code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.wallet_layout, container, false);
        binding.setClick(this);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        getWallData();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.coinRcy.setLayoutManager(manager);
    }

    public void getWallData() {
        parameters.put("user_id", Utils.getUser_id(getActivity()));
        ApiService.WalletsIndex(getActivity(), new IHttpCallback<WalletIndext>() {
            @Override
            public void onNext(final HttpResult<WalletIndext> t) {
                bean = t.getResponse_data();
                binding.setBean(t.getResponse_data());
                try {
                    code = AES.Decrypt(bean.getWallet_dagt_address(), Utils.GetToken(getContext()).substring(0, 16));
                    binding.tokenCode.setText(code);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter = new WalletIndexCoinAdapter(getActivity(), t.getResponse_data().getOwner_coin_info());
                binding.coinRcy.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("COIN_ALIAS_NAME", t.getResponse_data().getOwner_coin_info().get(position).getCoin_alias_name());
                        GenericFragmentActivity.start(getActivity(), CoinDetailFragment.class, bundle);
                    }
                });
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    public void token_qrcode(View v) {
        Bundle bundle = new Bundle();
        try {
            bundle.putString("URL", code);
            GenericFragmentActivity.start(getActivity(), DagtAddressQrcodeFragment.class, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copy(View v) {
        mClipData = ClipData.newPlainText("Simple test", code);
        //把clip对象放在剪贴板中
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(mClipData);
        Toast.makeText(getActivity(), "文本已经复制成功",
                Toast.LENGTH_SHORT).show();
    }

    public void getKeyStore(View v) {
        GenericFragmentActivity.start(getActivity(), BackupKeystoreFragment.class, new Bundle());
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
