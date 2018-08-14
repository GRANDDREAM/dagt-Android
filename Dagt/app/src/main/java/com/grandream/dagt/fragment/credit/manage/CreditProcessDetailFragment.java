package com.grandream.dagt.fragment.credit.manage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.AgainReslut;
import com.grandream.dagt.bean.credit.CreditDetail;
import com.grandream.dagt.bean.credit.CreditInfoReslut;
import com.grandream.dagt.bean.credit.LockContractReslut;
import com.grandream.dagt.bean.credit.OperatinData;
import com.grandream.dagt.bean.credit.SerciceFeeReslut;
import com.grandream.dagt.databinding.CreditProcessDetailLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.credit.CollateralDataAdapter;
import com.grandream.dagt.fragment.adapter.credit.NoDealAdapter;
import com.grandream.dagt.fragment.credit.CreditResultFragment;
import com.grandream.dagt.fragment.credit.LockContractsFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.credit.ChainTradeNumber;
import com.grandream.dagt.requestbody.credit.CreditDetailBody;
import com.grandream.dagt.requestbody.credit.TransactionSerialNumBean;
import com.grandream.dagt.ui.dialog.DialogClick;
import com.grandream.dagt.ui.dialog.EditAlertDialog;
import com.grandream.dagt.ui.dialog.LoginOutAlertDialog;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

import java.util.HashMap;

/**
 * Created by chenjing on 2018/5/15.
 */

public class CreditProcessDetailFragment extends BaseFragment {
    private static final String FRAGMENTERROR = "CREDITPROCESSDETAILFRAGMENT ERROR";
    private CreditProcessDetailLayoutBinding binding;
    private String order_sequence_code;
    private CollateralDataAdapter adapter;
    private int button;
    private CreditDetail creditDetail;
    private ChainTradeNumber chainTradeNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.credit_process_detail_layout, container, false);
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
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        binding.collateral.setLayoutManager(linearLayoutManager);
        getData();
    }

    public void getData() {
        final CreditDetailBody body = new CreditDetailBody(Utils.getUser_id(getActivity()) + "", order_sequence_code);
        ApiService.creditDetail(getActivity(), new IHttpCallback<CreditDetail>() {
            @Override
            public void onNext(HttpResult<CreditDetail> t) {
                creditDetail = t.getResponse_data();
                binding.setBean(creditDetail.getData());
                binding.setBt(creditDetail);
                adapter = new CollateralDataAdapter(getActivity(), t.getResponse_data().getData().getTrade_info());
                binding.collateral.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("ORDER_SEQUENCE_CODE", creditDetail.getData().getOrder_sequence_code());
                        bundle.putString("TRADE_CODE", creditDetail.getData().getTrade_info().get(position).getTrade_code());
                        GenericFragmentActivity.start(getActivity(), TransactionDetailsFragment.class, bundle);
                    }
                });
                button = t.getResponse_data().getButton();
//                button	int	按钮值为：0,1,2,3,4,5	非空
//                left_text	string	当按钮值为3时，使用左边按钮内容	非空
//                middle_text	string	当按钮值为0,1,2,4,5使用中间按钮内容	非空
//                right_text	string	当按钮值为3时，使用右边按钮内容	非空

                switch (t.getResponse_data().getButton()) {
                    case 1:
                        binding.centerBt.setVisibility(View.GONE);
                        binding.leftBt.setVisibility(View.VISIBLE);
                        binding.rightBt.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        binding.centerBt.setVisibility(View.GONE);
                        binding.leftBt.setVisibility(View.VISIBLE);
                        binding.rightBt.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        binding.centerBt.setClickable(false);
                        binding.centerBt.setEnabled(false);
                        binding.centerBt.setBackground(getResources().getDrawable(R.drawable.gradient_bt_bg));
                        binding.centerBt.setVisibility(View.VISIBLE);
                        binding.leftBt.setVisibility(View.GONE);
                        binding.rightBt.setVisibility(View.GONE);
                        break;
                    default:
                        binding.centerBt.setVisibility(View.VISIBLE);
                        binding.leftBt.setVisibility(View.GONE);
                        binding.rightBt.setVisibility(View.GONE);
                        break;

                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));

    }

    public void centerClick(View view) {
        // 1：//继续授信
        // 2：刷新状态
        // 3：完成授信和取消授信两个按钮
        //4：追加质押品
        //5：订单过期         middle_text	string	当按钮值为0,1,2,4,5使用中间按钮内容	非空
        switch (button) {
            case 0:
                break;
            case 1:
                CreditInfoReslut reslut = new CreditInfoReslut();
                CreditInfoReslut.DataBean data = new CreditInfoReslut.DataBean();
                data.setOrder_sequence_code(creditDetail.getData().getOrder_sequence_code());
                data.setWallet_contract_address(creditDetail.getData().getUser_wallet_address());
                data.setLoan_coin_id(creditDetail.getData().getLoan_coin_id());
                data.setLoan_coin_total_text(creditDetail.getCredit_confirm_tip_data().getLoan_coin_total_text());
                data.setUser_wallet_address(creditDetail.getData().getUser_wallet_address());
                data.setTip_info(creditDetail.getCredit_confirm_tip_data().getTip_info());
                data.setSubmit_text(creditDetail.getCredit_confirm_tip_data().getSubmit_text());
                data.setSelect_agreement_tips(creditDetail.getCredit_confirm_tip_data().getSelect_agreement_tips());
                reslut.setData(data);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CREDITINFORESLUT", reslut);
                GenericFragmentActivity.start(getActivity(), LockContractsFragment.class, bundle);
                break;
            case 2:
                getData();
                break;
            case 4:
                ShowAddNo();
                break;
            case 5:
                binding.centerBt.setBackground(getResources().getDrawable(R.drawable.gradient_bt_bg));
                break;
        }
    }

    public void leftClick(View view) {
        switch (button) {
            case 1:
                CreditInfoReslut reslut = new CreditInfoReslut();
                CreditInfoReslut.DataBean data = new CreditInfoReslut.DataBean();
                data.setOrder_sequence_code(creditDetail.getData().getOrder_sequence_code());
                data.setWallet_contract_address(creditDetail.getData().getUser_wallet_address());
                data.setLoan_coin_id(creditDetail.getData().getLoan_coin_id());
                data.setLoan_coin_total_text(creditDetail.getCredit_confirm_tip_data().getLoan_coin_total_text());
                data.setUser_wallet_address(creditDetail.getData().getUser_wallet_address());
                data.setTip_info(creditDetail.getCredit_confirm_tip_data().getTip_info());
                data.setSubmit_text(creditDetail.getCredit_confirm_tip_data().getSubmit_text());
                data.setSelect_agreement_tips(creditDetail.getCredit_confirm_tip_data().getSelect_agreement_tips());
                reslut.setData(data);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CREDITINFORESLUT", reslut);
                GenericFragmentActivity.start(getActivity(), LockContractsFragment.class, bundle);
                break;
            case 3:
                binding.leftBt.setEnabled(false);
                binding.leftBt.setClickable(false);
                creditConfirm();
                break;
        }

    }

    public void rightClick(View view) {
        CreditDetailBody body = new CreditDetailBody(Utils.getUser_id(getActivity()) + "", creditDetail.getData().getOrder_sequence_code());
        ApiService.Canel(getActivity(), new IHttpCallback<SerciceFeeReslut>() {
            @Override
            public void onNext(HttpResult<SerciceFeeReslut> t) {
                if (t.getResponse_data().getSuccess()) {
                    CommonUtil.showToast("取消授信成功");
                    getActivity().finish();
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    /**
     * 创建授信函
     */
    private void creditConfirm() {
        HashMap<String, String> chain_trade_number = new HashMap<
                String, String>();
        for (int i = 0; i < creditDetail.getData().getTrade_info().size(); i++) {
            chain_trade_number.put(creditDetail.getData().getTrade_info().get(i).getTrade_code(), creditDetail.getData().getTrade_info().get(i).getChain_trade_code());
        }
        chainTradeNumber = new ChainTradeNumber();
        chainTradeNumber.setUser_id(Utils.getUser_id(getActivity()));
        chainTradeNumber.setOrder_id(creditDetail.getData().getId());
        chainTradeNumber.setOrder_sequence_code(creditDetail.getData().getOrder_sequence_code());
        chainTradeNumber.setUser_wallet_address(creditDetail.getData().getUser_wallet_address());
        chainTradeNumber.setChain_trade_number(chain_trade_number);

        ApiService.creditConfirm(getActivity(), new IHttpCallback<LockContractReslut>() {
            @Override
            public void onNext(final HttpResult<LockContractReslut> t) {
//                状态有0，1,2,3,，0为校验失败，1为处理成功，2为抵押资产少于借款金额，3为添加的链上交易流水还有未处理完的状态。
                switch (t.getResponse_data().getSuccess()) {
                    case 0:
                        //普通校验失敗
                        Utils.ShowDialog(t.getResponse_text(), FRAGMENTERROR, CreditProcessDetailFragment.this, getActivity());
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
                                                ShowAddNo();
                                            }
                                        }).show();
                        binding.leftBt.setEnabled(true);
                        binding.leftBt.setClickable(true);
                        break;
                    case 3:
                        Utils.ShowClickDialog(t.getResponse_text(), FRAGMENTERROR, CreditProcessDetailFragment.this, getActivity(), t.getResponse_data().getShwo_detail_submit_text());
                        break;
                }
            }

            @Override
            public void onError(int code, String message) {
                binding.leftBt.setEnabled(true);
                binding.leftBt.setClickable(true);
                Utils.ShowDialog(message, FRAGMENTERROR, CreditProcessDetailFragment.this, getActivity());
            }
        }, Utils.getRequestBody(chainTradeNumber));
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
                                addTradeCode(value, "2");
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
     * 授信申请-》智能合约锁定 –》添加链流水
     */
    public void addTradeCode(final String value, String type) {
        TransactionSerialNumBean body = new TransactionSerialNumBean();
        body.setUser_id(Utils.getUser_id(getActivity()));
        body.setUser_wallet_address(creditDetail.getData().getUser_wallet_address());
        body.setOrder_sequence_code(creditDetail.getData().getOrder_sequence_code());
        body.setTrade_type(type);
        body.setChain_trade_code(value);

        ApiService.addTradeCode(getActivity(), new IHttpCallback<OperatinData>() {
            @Override
            public void onNext(HttpResult<OperatinData> t) {
                getData();
            }

            @Override
            public void onError(int code, String message) {
                Utils.ShowDialog(message, FRAGMENTERROR, CreditProcessDetailFragment.this, getActivity());
            }
        }, Utils.getRequestBody(body));


    }

    /**
     * 额度更新 确认之后再次请求确认授信函
     *
     * @param user_real_damage_amount
     */
    private void confirmgain(String user_real_damage_amount) {
        chainTradeNumber.setCurrent_loan_coin_total(creditDetail.getData().getLoan_coin_real_total());
        chainTradeNumber.setUser_real_damage_amount(user_real_damage_amount);
        ApiService.confirmgain(getActivity(), new IHttpCallback<AgainReslut>() {
            @Override
            public void onNext(HttpResult<AgainReslut> t) {
                //更新成功或失败，0为失败，1为成功
                if (t.getResponse_data().getUpdate() == 0) {
                    Utils.ShowDialog(t.getResponse_text(), FRAGMENTERROR, CreditProcessDetailFragment.this, getActivity());
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("NEXT_STEP_INFO", t.getResponse_data().getNext_step_info());
                    GenericFragmentActivity.start(getActivity(), CreditResultFragment.class, bundle);
                    getActivity().finish();
                }
            }

            @Override
            public void onError(int code, String message) {
                binding.leftBt.setEnabled(true);
                binding.leftBt.setClickable(true);
                Utils.ShowDialog(message, FRAGMENTERROR, CreditProcessDetailFragment.this, getActivity());
            }
        }, Utils.getRequestBody(chainTradeNumber));
    }

}
