package com.grandream.dagt.fragment.wallet;

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
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.AddReslutData;
import com.grandream.dagt.bean.wallet.DrawDetailBean;
import com.grandream.dagt.bean.wallet.WalletIndext;
import com.grandream.dagt.databinding.DrawCoinLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.my.TakeCoinAddressFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.WalletCoinDetailBody;
import com.grandream.dagt.requestbody.wallt.WithDarwSubmitBody;
import com.grandream.dagt.ui.dialog.LoginOutAlertDialog;
import com.grandream.dagt.ui.dialog.helper.FragmentExchangeController;
import com.grandream.dagt.ui.popuwindow.TPPasswordWindow;
import com.grandream.dagt.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjing on 2018/5/14.
 */

public class DrawCoinFragment extends BaseFragment {
    private static final String FRAGMENTERROE = "DRAWCOINFRAGMENT ERROR";
    private DrawCoinLayoutBinding binding;
    private String coin_alias_name;
    private DrawDetailBean bean;

    private boolean runningDownTimer;

    TPPasswordWindow tpWindow;
    List<String> list = new ArrayList<String>();
    String password = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.draw_coin_layout, container, false);
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
        binding.navigation.setRightText("添加提币地址");
        binding.navigation.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenericFragmentActivity.start(getActivity(), TakeCoinAddressFragment.class, new Bundle());
            }
        });
        getData();
    }

    public void getData() {
        final WalletCoinDetailBody body = new WalletCoinDetailBody(Utils.getUser_id(getActivity()) + "", coin_alias_name);

        ApiService.withdrawCoin(getActivity(), new IHttpCallback<DrawDetailBean>() {
            @Override
            public void onNext(HttpResult<DrawDetailBean> t) {
                bean = t.getResponse_data();
                binding.setBean(t.getResponse_data());
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    public void NextClick(View view) {
        showPassWordPop(getActivity());
    }

    private void Commit() {
        WithDarwSubmitBody body = new WithDarwSubmitBody(Utils.getUser_id(getActivity()) + "", Utils.getUser_name(getActivity()) + "", coin_alias_name, binding.msgCodeEd.getEditableText().toString(), binding.wallAddress.getEditableText().toString(), binding.drawCoinNum.getEditableText().toString(), binding.drawCoinFee.getEditableText().toString(), password);
        ApiService.withdrawSubmit(getActivity(), new IHttpCallback<AddReslutData>() {
            @Override
            public void onNext(HttpResult<AddReslutData> t) {
                //提币申请成功或失败，0为失败1为成功
                if (t.getResponse_data().getSuccess() == 1) {
                    getActivity().finish();
                } else {
                    Utils.ShowDialog(t.getResponse_text(), FRAGMENTERROE, DrawCoinFragment.this, getActivity());
                }

            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }


    public void max(View v) {
        binding.drawCoinFee.setText(Utils.toDouble(binding.drawCoinFee.getEditableText().toString()) + Utils.toDouble(bean.getDraw_coin_fee()) + "");
        binding.actualAmount.setText("实际到账：  " + (Utils.toDouble(binding.drawCoinNum.getEditableText().toString()) - Utils.toDouble(binding.drawCoinFee.getEditableText().toString())) + "  ETH");
    }

    public void mini(View v) {
        if (Utils.toDouble(binding.drawCoinFee.getEditableText().toString()) > 0.0000) {
            binding.drawCoinFee.setText(Utils.toDouble(binding.drawCoinFee.getEditableText().toString()) - Utils.toDouble(bean.getDraw_coin_fee()) + "");
            binding.actualAmount.setText("实际到账：  " + (Utils.toDouble(binding.drawCoinNum.getEditableText().toString()) - Utils.toDouble(binding.drawCoinFee.getEditableText().toString())) + "  ETH");
        }
    }

    public void chooseAddress(View v) {
        ChooseAddressFragment chosse = new ChooseAddressFragment();
        chosse.setChooseClickListener(new ChooseAddressFragment.ChooseClickListener() {
            @Override
            public void sendData(String wall_address) {
                binding.wallAddress.setText(wall_address);
            }
        }, bean);
        FragmentExchangeController.addFragment(getFragmentManager(), chosse, chosse.getTagName());
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
                    Commit();
                }
                list.clear();
                password = "";
            }
        });

        tpWindow.getContentView().findViewById(R.id.bank_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tpWindow.dismiss();
            }
        });
    }


    public void getMsg(View view) {
        //如果60秒倒计时没有结束
        if (runningDownTimer) {
            return;
        }

        binding.getSms.setText("正在发送");
        SendMsg();
    }

    private void SendMsg() {
        parameters.put("user_id", Utils.getUser_id(getActivity()));
        parameters.put("telephone", Utils.getUser_name(getActivity()));
        ApiService.drawCoinSmsCode(getActivity(), new IHttpCallback<WalletIndext>() {
            @Override
            public void onNext(HttpResult<WalletIndext> t) {
                downTimer.start();
            }

            @Override
            public void onError(int code, String message) {
                runningDownTimer = false;
                binding.getSms.setText("重新发送");
            }
        }, parameters);
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
}
