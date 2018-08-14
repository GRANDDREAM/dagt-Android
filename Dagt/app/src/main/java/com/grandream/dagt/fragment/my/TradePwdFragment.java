package com.grandream.dagt.fragment.my;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.home.msg.MarKAllResult;
import com.grandream.dagt.bean.login.RegisterSmsCode;
import com.grandream.dagt.databinding.TradepwdLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.my.setTrade;
import com.grandream.dagt.ui.popuwindow.TPPasswordWindow;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 交易密码设置
 * Created by chenjing on 2018/4/17.
 */

public class TradePwdFragment extends BaseFragment {
    private TradepwdLayoutBinding binding;
    private boolean runningDownTimer;

    TPPasswordWindow tpWindow;
    List<String> list = new ArrayList<String>();
    String password = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tradepwd_layout, container, false);
        binding.setClick(this);
        binding.tel.setText(Utils.getUser_name(getContext()));
        return binding.getRoot();
    }

    public void sendBack(View view) {
        sendKeyBackEvent();
    }

    public void getSMSCode(View view) {
        //如果60秒倒计时没有结束
        if (runningDownTimer) {
            return;
        }

        binding.getSms.setText("正在发送");
        getSMSCode();
    }

    /**
     * 倒计时
     */
    private CountDownTimer downTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            runningDownTimer = true;
            binding.getSms.setText((l / 1000) + "秒后重发");
        }

        @Override
        public void onFinish() {
            runningDownTimer = false;
            binding.getSms.setText("重新发送");
        }

    };

    private void getSMSCode() {
        parameters.clear();
        parameters.put("telephone", binding.tel.getText().toString());
        ApiService.smsCode(getActivity(), new IHttpCallback<RegisterSmsCode>() {
            @Override
            public void onNext(HttpResult<RegisterSmsCode> t) {
                downTimer.start();  // 倒计时开始
            }

            @Override
            public void onError(int code, String message) {
                runningDownTimer = false;
                binding.getSms.setText("重新发送");
            }
        }, parameters);
    }

    public void NextClick(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen) {
            imm.hideSoftInputFromWindow(binding.msgCodeEd.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
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
        tpWindow.showAtLocation(binding.commit, Gravity.BOTTOM
                , 40, 0); // 设置layout在PopupWindow中显示的位置

        tpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                binding.cover.setVisibility(View.GONE);
                if (list.size() == 6) {
                    for (int i = 0; i < list.size(); i++) {
                        password = password + list.get(i);
                    }
                    next();
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

    private void next() {
        setTrade setTrade = new setTrade();
        setTrade.setTrade_password(password);
        setTrade.setSms_code(binding.msgCodeEd.getEditableText().toString());
        setTrade.setTelephone(binding.tel.getText().toString());
        ApiService.setTrade(getActivity(), new IHttpCallback<MarKAllResult>() {
            @Override
            public void onNext(HttpResult<MarKAllResult> t) {
                if (t.getResponse_data().getSuccess() == 1) {
                    CommonUtil.showToast(t.getResponse_text());
                    getActivity().finish();
                } else {
                    CommonUtil.showToast(t.getResponse_text());
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(setTrade));
    }


}
