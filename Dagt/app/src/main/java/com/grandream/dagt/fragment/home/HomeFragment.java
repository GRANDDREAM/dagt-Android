package com.grandream.dagt.fragment.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.home.ADSBean;
import com.grandream.dagt.bean.home.HomeCoinTrendBean;
import com.grandream.dagt.bean.home.msg.MsgList;
import com.grandream.dagt.databinding.HomeLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.credit.ApplyForCreditFragment;
import com.grandream.dagt.fragment.home.msg.MsgCenterFragment;
import com.grandream.dagt.fragment.user.LogingFragment;
import com.grandream.dagt.fragment.user.SecondLoginFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.ui.adbanner.SliderLayout;
import com.grandream.dagt.ui.adbanner.animations.DescriptionAnimation;
import com.grandream.dagt.ui.adbanner.indicators.PagerIndicator;
import com.grandream.dagt.ui.adbanner.slidertypes.BaseSliderView;
import com.grandream.dagt.ui.adbanner.slidertypes.TextSliderView;
import com.grandream.dagt.ui.trendchart.DateValue;
import com.grandream.dagt.ui.trendchart.Line;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;
import com.itingchunyu.badgeview.BadgeTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 首頁
 * Created by chenjing on 2018/3/19.
 */

public class HomeFragment extends BaseFragment {
    HomeLayoutBinding binding;
    List<DateValue> dvTrend = new ArrayList<>();
    List<DateValue> dvTrend1 = new ArrayList<>();
    List<DateValue> dvTrend2 = new ArrayList<>();
    List<HashMap<String, Object>> banner = new ArrayList<HashMap<String, Object>>();
    Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_layout, container, false);
        binding.setClick(this);
        initView();
        getADS();
        getCoinTrend();
        if (Utils.isLogin(getContext())) {
            unReadMsg();
        }
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getADS();
    }

    /**
     * 獲取用戶是否有未读消息
     */
    private void unReadMsg() {
        parameters.put("user_id", Utils.getUser_id(getContext()));
        ApiService.unReadMsg(getContext(), new IHttpCallback<MsgList>() {
            @Override
            public void onNext(HttpResult<MsgList> t) {
                if (t.getResponse_data().getTotal() > 0) {
                    BadgeTextView mBadgeTextView = new BadgeTextView(getContext());
                    mBadgeTextView.setTargetView(binding.icMore);
                    mBadgeTextView.setBadgeCount(0).setmDefaultTopPadding(0).setmDefaultRightPadding(55);
                    mBadgeTextView.setBadgeShown(true);
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    private void initView() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.slider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
            }
        }, 1800);
        binding.trendView.setSelected(false);
        binding.trendView1.setSelected(false);
        binding.trendView2.setSelected(false);
    }

    /**
     * 头部消息点击
     *
     * @param view
     */
    public void MSG_CLick(View view) {
        if (!Utils.isLogin(getContext())) {
            if (Utils.getIsFirst(getContext())) {
                Bundle bundle = new Bundle();
                bundle.putString("user_name", Utils.getUser_name(getContext()));
                GenericFragmentActivity.start(getActivity(), SecondLoginFragment.class, bundle);
            } else {
                GenericFragmentActivity.start(getActivity(), LogingFragment.class, new Bundle());
            }
        } else {
            GenericFragmentActivity.start(getActivity(), MsgCenterFragment.class, new Bundle());

        }
    }

    public void getCoinTrend() {
        ApiService.getCoinTrend(getActivity(), new IHttpCallback<HomeCoinTrendBean>() {

            @Override
            public void onNext(HttpResult<HomeCoinTrendBean> t) {
                HomeCoinTrendBean.DataBean.BtcusdtBean btcusdt = t.getResponse_data().getData().getBtcusdt();
                HomeCoinTrendBean.DataBean.EthusdtBean ethusdtBean = t.getResponse_data().getData().getEthusdt();
                HomeCoinTrendBean.DataBean.DagtusdtBean dagtusdtBean = t.getResponse_data().getData().getDagtusdt();
                binding.setBean(t.getResponse_data().getData());
                for (int i = 0; i < btcusdt.get_$24_hours_k_line_info().size(); i++) {
                    dvTrend.add(new DateValue(btcusdt.get_$24_hours_k_line_info().get(i).getPrice(), btcusdt.get_$24_hours_k_line_info().get(i).getData()));
                }
                binding.trendView
                        .withLine(new Line(dvTrend))
                        .withPrevClose(42.2)
                        .withDisplayFrom(0)
                        .withDisplayNumber(Utils.getTrend().size()).show();
                for (int i = 0; i < ethusdtBean.get_$24_hours_k_line_info().size(); i++) {
                    dvTrend1.add(new DateValue(ethusdtBean.get_$24_hours_k_line_info().get(i).getPrice(), ethusdtBean.get_$24_hours_k_line_info().get(i).getData()));
                }
                binding.trendView1
                        .withLine(new Line(dvTrend1))
                        .withPrevClose(30.2)
                        .withDisplayFrom(0)
                        .withDisplayNumber(Utils.getTrend().size()).show();
            }

            @Override
            public void onError(int code, String message) {
            }
        }, parameters);
    }

    public void CreditApplication(View view) {
        if (!Utils.isLogin(getContext())) {
            if (Utils.getIsFirst(getContext())) {
                Bundle bundle = new Bundle();
                bundle.putString("user_name", Utils.getUser_name(getContext()));
                GenericFragmentActivity.start(getActivity(), SecondLoginFragment.class, bundle);
            } else {
                GenericFragmentActivity.start(getActivity(), LogingFragment.class, new Bundle());
            }
        } else {
            GenericFragmentActivity.start(getActivity(), ApplyForCreditFragment.class, new Bundle());

        }
    }

    public void toShare(View view) {
        if (!Utils.isLogin(getContext())) {
            if (Utils.getIsFirst(getContext())) {
                Bundle bundle = new Bundle();
                bundle.putString("user_name", Utils.getUser_name(getContext()));
                GenericFragmentActivity.start(getActivity(), SecondLoginFragment.class, bundle);
            } else {
                GenericFragmentActivity.start(getActivity(), LogingFragment.class, new Bundle());
            }
        } else {
            GenericFragmentActivity.start(getActivity(), ShareFragment.class, new Bundle());
        }
    }

    public void getADS() {
        ApiService.adsIndex(getActivity(), new IHttpCallback<ADSBean>() {
            @Override
            public void onNext(HttpResult<ADSBean> t) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.slider.startAutoCycle();
                        binding.slider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
                    }
                }, 2000);
                TextSliderView textSliderView = new TextSliderView(getActivity(), t.getResponse_data().getData());
                for (int i = 0; i < t.getResponse_data().getData().size(); i++) {
                    textSliderView
                            .image(t.getResponse_data().getData().get(i).getContent())
                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                @Override
                                public void onSliderClick(BaseSliderView slider) {

                                }
                            });
                    binding.slider.addSlider(textSliderView);
                }
                binding.slider.setPresetTransformer(SliderLayout.Transformer.Stack);
                binding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                binding.slider.setCustomAnimation(new DescriptionAnimation());
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }


    @Override
    public void onPause() {
        super.onPause();
        binding.slider.pauseAutoCycle();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.slider.recoverCycle();
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.slider.pauseAutoCycle();
    }

}
