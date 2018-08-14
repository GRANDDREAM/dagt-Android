package com.grandream.dagt.fragment.credit.manage;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.AddPledgeData;
import com.grandream.dagt.bean.credit.AddReslutData;
import com.grandream.dagt.bean.credit.OperatinData;
import com.grandream.dagt.databinding.AdditionalPledgeLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.credit.NoDealAdapter;
import com.grandream.dagt.fragment.credit.QrcodeFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.credit.ChainTradeNumber;
import com.grandream.dagt.requestbody.credit.CreditDetailBody;
import com.grandream.dagt.requestbody.credit.TransactionSerialNumBean;
import com.grandream.dagt.ui.dialog.DialogClick;
import com.grandream.dagt.ui.dialog.EditAlertDialog;
import com.grandream.dagt.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 追加质押品
 * Created by chenjing on 2018/5/8.
 */

public class AdditionalPledgeFragment extends BaseFragment {
    private static String FRAGMENTERROR = "AdditionalPledgeFragment error";
    private AdditionalPledgeLayoutBinding binding;
    private String order_sequence_code;
    private ClipData mClipData;
    private ClipboardManager mClipboardManager;
    private AddPledgeData bean;
    private OperatinData operatinData;
    private NoDealAdapter adapter;
    List<OperatinData.DataBean> dataBean = new ArrayList<OperatinData.DataBean>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.additional_pledge_layout, container, false);
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
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.TradingVolume.setLayoutManager(manager);
        binding.commit.setEnabled(false);
        getData();
    }

    public void token_qrcode(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("URL", bean.getData().getWallet_contract_address());
        bundle.putString("TYPE", "WALLET_CONTRACT_ADDRESS");
        GenericFragmentActivity.start(getActivity(), QrcodeFragment.class, bundle);
    }

    public void addNo(View view) {
        ShowAddNo();
    }

    public void copy(View v) {
        mClipData = ClipData.newPlainText("Simple test", bean.getData().getWallet_contract_address());
        //把clip对象放在剪贴板中
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(mClipData);
        Toast.makeText(getActivity(), "文本已经复制成功",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * 添加交易流水
     */
    private void ShowAddNo() {
        EditAlertDialog editAlertDialog = new EditAlertDialog(getActivity());
        editAlertDialog.builder()
                .setCanceledOnTouchOutside(false)
                .setTitle("请输入链上交易流水号")
                .setPositiveButton("提交",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }, new DialogClick() {
                            @Override
                            public void onDiaLogClick(String value) {
                                addTradeCode(value);
                            }
                        })
                .setNegativeButton("取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                return;
                            }
                        }).show();
    }

    /**
     * 示例
     *
     * @param view
     */
    public void Examples(View view) {
        Utils.ShowDialog("0xa39e7a2260864206724ff2bbacd68c25aca4c7575aae1e5601cc68dbd86cd303", FRAGMENTERROR, AdditionalPledgeFragment.this, getActivity());
    }


    /**
     * 追加提交
     *
     * @param v
     */
    public void Commit(View v) {
        if (CheckValue()) {
            Commit();
            binding.commit.setEnabled(false);
            binding.commit.setClickable(false);
        }
    }

    private boolean CheckValue() {
        if (binding.TradingVolume.getAdapter() == null) {
            Utils.ShowDialog("请添加交易流水", FRAGMENTERROR, this, getContext());
            return false;
        }
        return true;
    }

    public void checkCommit(View view) {
        if (!binding.checkBox.isChecked()) {
            binding.commit.setBackgroundColor(this.getResources().getColor(R.color.tab_normal_color));
            binding.commit.setEnabled(false);
            binding.commit.setClickable(false);
        } else {
            binding.commit.setBackgroundColor(this.getResources().getColor(R.color.title_bg));
            binding.commit.setEnabled(true);
            binding.commit.setClickable(true);
        }
    }

    private void Commit() {
        HashMap<String, String> chain_trade_number = new HashMap<
                String, String>();
        final ChainTradeNumber body = new ChainTradeNumber();
        body.setUser_id(Utils.getUser_id(getActivity()));
        body.setOrder_sequence_code(order_sequence_code);
        for (int i = 0; i < dataBean.size(); i++) {
            chain_trade_number.put(dataBean.get(i).getTrade_code(), dataBean.get(i).getChain_trade_code());
        }
        body.setChain_trade_number(chain_trade_number);
        ApiService.creditManagesSubmit(getActivity(), new IHttpCallback<AddReslutData>() {
            @Override
            public void onNext(HttpResult<AddReslutData> t) {
                //去结果页面  0失败  1成功
                if (t.getResponse_data().getSuccess() == 0) {
                    Utils.ShowDialog(t.getResponse_text(), FRAGMENTERROR, AdditionalPledgeFragment.this, getActivity());
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("NEXT_STEP_INFO", t.getResponse_data().getNext_step_info());
                    GenericFragmentActivity.start(getActivity(), AddReslutaFragment.class, bundle);
                    getActivity().finish();
                }

            }

            @Override
            public void onError(int code, String message) {
                binding.commit.setEnabled(true);
                binding.commit.setClickable(true);
            }
        }, Utils.getRequestBody(body));
    }


    public void getData() {
        CreditDetailBody body = new CreditDetailBody(Utils.getUser_id(getActivity()) + "", order_sequence_code);
        ApiService.appendCredit(getActivity(), new IHttpCallback<AddPledgeData>() {
            @Override
            public void onNext(HttpResult<AddPledgeData> t) {
                bean = t.getResponse_data();
                binding.setBean(bean.getData());
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    /**
     * 授信申请-》智能合约锁定 –》添加链流水
     */
    public void addTradeCode(final String value) {
        TransactionSerialNumBean body = new TransactionSerialNumBean();
        body.setUser_id(Utils.getUser_id(getActivity()));
        body.setUser_wallet_address(bean.getData().getUser_wallet_address());
        body.setOrder_sequence_code(bean.getData().getOrder_sequence_code());
        body.setChain_trade_code(value);
        body.setTrade_type("2");

        ApiService.addTradeCode(getActivity(), new IHttpCallback<OperatinData>() {
            @Override
            public void onNext(HttpResult<OperatinData> t) {
                dataBean.add(t.getResponse_data().getData());
                adapter = new NoDealAdapter(getActivity(), dataBean);
                binding.TradingVolume.setAdapter(adapter);
                getData();
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }
}
