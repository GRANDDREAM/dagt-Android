package com.grandream.dagt.fragment.credit.manage;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.SerciceFeeReslut;
import com.grandream.dagt.bean.credit.ServerFeeData;
import com.grandream.dagt.databinding.PayCreditServiceFeeLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.credit.ServeiceFeeCoinRecyCleryAdapter;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.credit.CreditDetailBody;
import com.grandream.dagt.ui.popuwindow.TPPasswordWindow;
import com.grandream.dagt.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付授信服务费
 * Created by chenjing on 2018/5/8.
 */

public class PaycreditServiceFeeFragment extends BaseFragment {
    private static final String FRAGMENTERROR = "PAYCREDITSERVICEFEEFRAGMENT ERROR";
    private PayCreditServiceFeeLayoutBinding binding;
    private String order_sequence_code;
    private ServeiceFeeCoinRecyCleryAdapter adapter;
    private TPPasswordWindow tpWindow;
    private List<String> list = new ArrayList<String>();
    private String password = "";
    private String coin_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.pay_credit_service_fee_layout, container, false);
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
        binding.coinRcy.setLayoutManager(manager);
        getData();
    }

    public void payServerFee(View v) {
        showPassWordPop(getActivity());
    }

    private void showPassWordPop(final Activity context) {
        binding.cover.setVisibility(View.VISIBLE);
        tpWindow = new TPPasswordWindow(context, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageView img_psd1 = (ImageView) tpWindow.getContentView().findViewById(R.id.img_psd1);
                ImageView img_psd2 = (ImageView) tpWindow.getContentView().findViewById(R.id.img_psd2);
                ImageView img_psd3 = (ImageView) tpWindow.getContentView().findViewById(R.id.img_psd3);
                ImageView img_psd4 = (ImageView) tpWindow.getContentView().findViewById(R.id.img_psd4);
                ImageView img_psd5 = (ImageView) tpWindow.getContentView().findViewById(R.id.img_psd5);
                ImageView img_psd6 = (ImageView) tpWindow.getContentView().findViewById(R.id.img_psd6);

                if (position < 9) {
                    list.add((position + 1) + "");
                } else if (position == 9) {

                } else if (position == 10) {
                    list.add("0");
                } else if (position == 11) {
                    if (list.size() > 0) {
                        list.remove(list.size() - 1);
                    }
                }

                if (list.size() == 0) {
                    img_psd1.setVisibility(View.GONE);
                    img_psd2.setVisibility(View.GONE);
                    img_psd3.setVisibility(View.GONE);
                    img_psd4.setVisibility(View.GONE);
                    img_psd5.setVisibility(View.GONE);
                    img_psd6.setVisibility(View.GONE);

                } else if (list.size() == 1) {
                    img_psd1.setVisibility(View.VISIBLE);
                    img_psd2.setVisibility(View.GONE);
                    img_psd3.setVisibility(View.GONE);
                    img_psd4.setVisibility(View.GONE);
                    img_psd5.setVisibility(View.GONE);
                    img_psd6.setVisibility(View.GONE);
                } else if (list.size() == 2) {
                    img_psd1.setVisibility(View.VISIBLE);
                    img_psd2.setVisibility(View.VISIBLE);
                    img_psd3.setVisibility(View.GONE);
                    img_psd4.setVisibility(View.GONE);
                    img_psd5.setVisibility(View.GONE);
                    img_psd6.setVisibility(View.GONE);
                } else if (list.size() == 3) {
                    img_psd1.setVisibility(View.VISIBLE);
                    img_psd2.setVisibility(View.VISIBLE);
                    img_psd3.setVisibility(View.VISIBLE);
                    img_psd4.setVisibility(View.GONE);
                    img_psd5.setVisibility(View.GONE);
                    img_psd6.setVisibility(View.GONE);
                } else if (list.size() == 4) {
                    img_psd1.setVisibility(View.VISIBLE);
                    img_psd2.setVisibility(View.VISIBLE);
                    img_psd3.setVisibility(View.VISIBLE);
                    img_psd4.setVisibility(View.VISIBLE);
                    img_psd5.setVisibility(View.GONE);
                    img_psd6.setVisibility(View.GONE);
                } else if (list.size() == 5) {
                    img_psd1.setVisibility(View.VISIBLE);
                    img_psd2.setVisibility(View.VISIBLE);
                    img_psd3.setVisibility(View.VISIBLE);
                    img_psd4.setVisibility(View.VISIBLE);
                    img_psd5.setVisibility(View.VISIBLE);
                    img_psd6.setVisibility(View.GONE);
                } else if (list.size() == 6) {
                    img_psd1.setVisibility(View.VISIBLE);
                    img_psd2.setVisibility(View.VISIBLE);
                    img_psd3.setVisibility(View.VISIBLE);
                    img_psd4.setVisibility(View.VISIBLE);
                    img_psd5.setVisibility(View.VISIBLE);
                    img_psd6.setVisibility(View.VISIBLE);
                    tpWindow.dismiss();
                }
            }
        });
        tpWindow.setBackgroundDrawable(null);
        // 显示窗口
        tpWindow.showAtLocation(binding.payServerFeeLin, Gravity.BOTTOM
                , 40, 0); // 设置layout在PopupWindow中显示的位置

        tpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                binding.cover.setVisibility(View.GONE);
                if (list.size() == 6) {
                    for (int i = 0; i < list.size(); i++) {
                        password = password + list.get(i);
                    }
                    sendPayData(password);
                }
                list.clear();
                password = "";
            }
        });

//        tpWindow.getContentView().findViewById(R.id.forget_pw).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GenericFragmentActivity.start(mActivity, ResetFundPasswordFragment1.class, new Bundle());
//                tpWindow.dismiss();
//            }
//        });
        tpWindow.getContentView().findViewById(R.id.bank_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tpWindow.dismiss();
            }
        });
    }

    public void sendPayData(String password) {
        final CreditDetailBody body = new CreditDetailBody(Utils.getUser_id(getActivity()) + "", order_sequence_code);
        body.setTrade_password(password);
        body.setCoin_id(coin_id);
        ApiService.payServerFee(getActivity(), new IHttpCallback<SerciceFeeReslut>() {
            @Override
            public void onNext(HttpResult<SerciceFeeReslut> t) {
                //支付结果，0为失败，1为成功。
                Bundle bundle = new Bundle();
                bundle.putSerializable("SERCICEFEERESLUT", t.getResponse_data());
                GenericFragmentActivity.start(getActivity(), ServeiceFeeReslutFragment.class, bundle);
                getActivity().finish();
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    public void getData() {
        CreditDetailBody body = new CreditDetailBody(Utils.getUser_id(getActivity()) + "", order_sequence_code);

        ApiService.showServerFee(getActivity(), new IHttpCallback<ServerFeeData>() {
            @Override
            public void onNext(final HttpResult<ServerFeeData> t) {
                binding.setBean(t.getResponse_data().getData());
                coin_id=t.getResponse_data().getData().getPay_type().get(0).getCoin_id();
                adapter = new ServeiceFeeCoinRecyCleryAdapter(getActivity(), t.getResponse_data().getData().getPay_type());
                binding.coinRcy.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        coin_id = t.getResponse_data().getData().getPay_type().get(position).getCoin_id();
                    }
                });
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }
}
