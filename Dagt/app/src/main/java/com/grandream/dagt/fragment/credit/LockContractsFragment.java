package com.grandream.dagt.fragment.credit;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.AgainReslut;
import com.grandream.dagt.bean.credit.CreditDetail;
import com.grandream.dagt.bean.credit.CreditInfoReslut;
import com.grandream.dagt.bean.credit.LockContractReslut;
import com.grandream.dagt.bean.credit.OperatinData;
import com.grandream.dagt.bean.credit.Qrcode;
import com.grandream.dagt.databinding.LockcontractsLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.credit.NoDealAdapter;
import com.grandream.dagt.fragment.my.CommonProblemFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.credit.ChainTradeNumber;
import com.grandream.dagt.requestbody.credit.TransactionSerialNumBean;
import com.grandream.dagt.ui.dialog.DialogClick;
import com.grandream.dagt.ui.dialog.EditAlertDialog;
import com.grandream.dagt.ui.dialog.LoginOutAlertDialog;
import com.grandream.dagt.ui.dialog.SingleDialogFragmentCallBack;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 合约锁定
 * Created by chenjing on 2018/4/18.
 */

public class LockContractsFragment extends BaseFragment implements SingleDialogFragmentCallBack {
    private LockcontractsLayoutBinding binding;
    private CreditInfoReslut creditInfoReslut;
    private OperatinData operatinData;
    private ChainTradeNumber chainTradeNumber;
    private String url;
    private ClipData mClipData;
    private ClipboardManager mClipboardManager;
    private NoDealAdapter adapter;
    List<OperatinData.DataBean> dataBean = new ArrayList<OperatinData.DataBean>();
    private int rule_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        savedInstanceState = getArguments();
        creditInfoReslut = (CreditInfoReslut) savedInstanceState.getSerializable("CREDITINFORESLUT");
        rule_id = savedInstanceState.getInt("RULE_ID");
        binding = DataBindingUtil.inflate(inflater, R.layout.lockcontracts_layout, container, false);
        operatinData = new OperatinData();
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.setBean(creditInfoReslut.getData());
        binding.setClick(this);
        binding.commit.setEnabled(false);
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        binding.navigation.setRightText("授信规则");
        binding.navigation.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("RULE_ID", rule_id);
                GenericFragmentActivity.start(getActivity(), CommonProblemFragment.class, bundle);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.noRecy.setLayoutManager(manager);
//        getImageData();

    }

    /**
     * 添加交易流水
     *
     * @param view
     */
    public void addNo(View view) {
        ShowAddNo("1");
    }

    /**
     * 添加交易流水
     */
    private void ShowAddNo(final String type) {
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
                                addTradeCode(value, type);
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
        Utils.ShowDialog("0xa39e7a2260864206724ff2bbacd68c25aca4c7575aae1e5601cc68dbd86cd303", FRAGMENTERROR, LockContractsFragment.this, getActivity());
    }

    /**
     * 授信申请-》智能合约锁定 –》添加链流水
     */
    public void addTradeCode(final String value, String type) {
        /**
         * 交易类型，1代表授信申请，2代表授信补仓
         */
        TransactionSerialNumBean body = new TransactionSerialNumBean();
        body.setUser_id(Utils.getUser_id(getActivity()));
        body.setUser_wallet_address(creditInfoReslut.getData().getUser_wallet_address());
        body.setOrder_sequence_code(creditInfoReslut.getData().getOrder_sequence_code());
        body.setChain_trade_code(value);
        body.setTrade_type(type);
        body.setOrder_id(creditInfoReslut.getOrder_id());
        ApiService.addTradeCode(getActivity(), new IHttpCallback<OperatinData>() {
            @Override
            public void onNext(HttpResult<OperatinData> t) {
                operatinData = t.getResponse_data();
                dataBean.add(t.getResponse_data().getData());
                adapter = new NoDealAdapter(getActivity(), dataBean);
                binding.noRecy.setAdapter(adapter);
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));


    }

    /**
     * 展示二维码
     *
     * @param view
     */
    public void tokenQrcode(View view) {
        showBigImage();
    }


    public void copy(View view) {
        mClipData = ClipData.newPlainText("Simple test", creditInfoReslut.getData().getWallet_contract_address());
        //把clip对象放在剪贴板中
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(mClipData);
        Toast.makeText(getActivity(), "文本已经复制成功",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取二维码
     */
    private void getImageData() {
        ApiService.Qrcode(getActivity(), new IHttpCallback<Qrcode>() {
            @Override
            public void onNext(HttpResult<Qrcode> t) {
                url = t.getResponse_data().getData();

            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    private void showBigImage() {
        Bundle bundle = new Bundle();
        bundle.putString("URL", creditInfoReslut.getData().getWallet_contract_address());
        bundle.putString("TYPE", "WALLET_CONTRACT_ADDRESS");
        GenericFragmentActivity.start(getActivity(), QrcodeFragment.class, bundle);
    }


    /**
     * 转币成功，创建授信函
     */
    public void NextClick(View view) {
        if (CheckValue()) {
            creditConfirm();
            binding.commit.setEnabled(false);
            binding.commit.setClickable(false);
        }
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

    private boolean CheckValue() {
        if (binding.noRecy.getAdapter() == null) {
            Utils.ShowDialog("请添加交易流水", FRAGMENTERROR, this, getContext());
            return false;
        }
        return true;
    }

    /**
     * 创建授信函
     */
    private void creditConfirm() {
        HashMap<String, String> chain_trade_number = new HashMap<
                String, String>();
        for (int i = 0; i < dataBean.size(); i++) {
            chain_trade_number.put(dataBean.get(i).getTrade_code(), dataBean.get(i).getChain_trade_code());
        }
        chainTradeNumber = new ChainTradeNumber();
        chainTradeNumber.setUser_id(Utils.getUser_id(getActivity()));
        chainTradeNumber.setOrder_id(creditInfoReslut.getOrder_id());
        chainTradeNumber.setOrder_sequence_code(creditInfoReslut.getData().getOrder_sequence_code());
        chainTradeNumber.setUser_wallet_address(creditInfoReslut.getData().getUser_wallet_address());
        chainTradeNumber.setChain_trade_number(chain_trade_number);
        ApiService.creditConfirm(getActivity(), new IHttpCallback<LockContractReslut>() {
            @Override
            public void onNext(final HttpResult<LockContractReslut> t) {
                binding.commit.setEnabled(true);
                binding.commit.setClickable(true);
//                状态有0，1,2,3,，0为校验失败，1为处理成功，2为抵押资产少于借款金额，3为添加的链上交易流水还有未处理完的状态。
                switch (t.getResponse_data().getSuccess()) {
                    case 0:
                        //普通校验失敗
                        Utils.ShowDialog(t.getResponse_text(), FRAGMENTERROR, LockContractsFragment.this, getActivity());
                        break;
                    case 1:
                        //跳转下个页面
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("NEXT_STEP_INFO", t.getResponse_data().getNext_step_info());
                        GenericFragmentActivity.start(getActivity(), CreditResultFragment.class, bundle);
                        getActivity().finish();
                        break;
                    case 2:
                        //弹出两个对话框
                        LoginOutAlertDialog twodialog = new LoginOutAlertDialog(getActivity());
                        twodialog.builder().setCanceledOnTouchOutside(false)
                                .setMsg(t.getResponse_text())
                                .setTitle("提示")
                                .setPositiveButton(t.getResponse_data().getLeft_submit_text(),
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                confirmgain(t.getResponse_data().getData().getUser_real_damage_amount());
                                            }
                                        })
                                .setNegativeButton(t.getResponse_data().getRight_submit_text(),
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                ShowAddNo("2");
                                            }
                                        }).show();
                        break;
                    case 3:
                        Utils.ShowClickDialog(t.getResponse_text(), FRAGMENTERROR, LockContractsFragment.this, getActivity(), t.getResponse_data().getShwo_detail_submit_text());
                        break;
                }
            }

            @Override
            public void onError(int code, String message) {
                binding.commit.setEnabled(true);
                binding.commit.setClickable(true);
            }
        }, Utils.getRequestBody(chainTradeNumber));
    }

    /**
     * 额度更新 确认之后再次请求确认授信函
     *
     * @param user_real_damage_amount
     */
    private void confirmgain(String user_real_damage_amount) {
        chainTradeNumber.setCurrent_loan_coin_total(creditInfoReslut.getData().getLoan_coin_total());
        chainTradeNumber.setUser_real_damage_amount(user_real_damage_amount);
        ApiService.confirmgain(getActivity(), new IHttpCallback<AgainReslut>() {
            @Override
            public void onNext(HttpResult<AgainReslut> t) {
                //更新成功或失败，0为失败，1为成功
                if (t.getResponse_data().getUpdate() == 0) {
                    Utils.ShowDialog(t.getResponse_text(), FRAGMENTERROR, LockContractsFragment.this, getActivity());
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("NEXT_STEP_INFO", t.getResponse_data().getNext_step_info());
                    GenericFragmentActivity.start(getActivity(), CreditResultFragment.class, bundle);
                    getActivity().finish();
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(chainTradeNumber));
    }

    @Override
    public void onSingleBtnClick(String tag) {
        //单选按钮点击  去查看详情

    }
}
