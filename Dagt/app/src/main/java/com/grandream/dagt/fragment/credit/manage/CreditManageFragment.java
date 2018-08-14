package com.grandream.dagt.fragment.credit.manage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.activity.MainActivity;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.CreditListData;
import com.grandream.dagt.bean.login.RegisterSmsCode;
import com.grandream.dagt.databinding.CreditManageLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.credit.CreditCoinRecyAdapter;
import com.grandream.dagt.fragment.user.LogingFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.ui.pulltorefresh.XRefreshView;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;
import com.itingchunyu.badgeview.BadgeTextView;

import java.util.ArrayList;

/**
 * 授信管理
 * Created by chenjing on 2018/3/29.
 */

public class CreditManageFragment extends BaseFragment {
    private CreditManageLayoutBinding binding;
    private Fragment fragmemt1, fragmemt2, fragmemt3;
    private ArrayList<Fragment> fragmentsList;
    private HelpFragmentPagerAdapter pagerAdapter;
    private int currIndex = 0;
    private CreditCoinRecyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.credit_manage_layout, container, false);
        binding.setClick(this);
        binding.navigation.hideLeft(true);
        initView();
        initViewPager();
        return binding.getRoot();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.coinCreditRcy.setLayoutManager(manager);
        getData();
    }

    private void initViewPager() {
        fragmentsList = new ArrayList<Fragment>();
        pagerAdapter = new HelpFragmentPagerAdapter(getChildFragmentManager(),
                fragmentsList);
        fragmemt1 = new CreditSucceedFragment();
        fragmemt2 = new CreditProcessingFragment();
        fragmemt3 = new CreditFailureFragment();
//        fragmemt1.setArguments(bundle);
        fragmentsList.add(fragmemt1);
        fragmentsList.add(fragmemt2);
        fragmentsList.add(fragmemt3);

        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.setOffscreenPageLimit(0);
        binding.viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        binding.viewPager.setCurrentItem(0);
    }

    public void Credit_Succeed(View view) {
        binding.viewPager.setCurrentItem(0);
    }

    public void In_Hand_Click(View view) {
        binding.viewPager.setCurrentItem(1);

    }

    public void CreditFailure_Click(View view) {
        binding.viewPager.setCurrentItem(2);

    }

    public void getData() {
        parameters.put("user_id", Utils.getUser_id(getActivity()));
        parameters.put("page_size", 1);
        parameters.put("page_number", 1);
        parameters.put("label", 1);
        ApiService.CreditManagesIndex(getActivity(), new IHttpCallback<CreditListData>() {
            @Override
            public void onNext(HttpResult<CreditListData> t) {
                binding.setBean(t.getResponse_data().getData());
                adapter = new CreditCoinRecyAdapter(getActivity(), t.getResponse_data().getData().getFormat_loan_coin_info());
                binding.coinCreditRcy.setAdapter(adapter);
                BadgeTextView mBadgeTextView = new BadgeTextView(getContext());
                if (t.getResponse_data().getData().getUnprocess_order_info()==1) {
                    mBadgeTextView.setTargetView(binding.tvInHand);
                    mBadgeTextView.setBadgeCount(0).setmDefaultTopPadding(0).setmDefaultRightPadding(0).setmDefaultRadius(8).setBackgroundColor(getResources().getColor(R.color.red));
                    mBadgeTextView.setBadgeShown(true);
                } else {
                    mBadgeTextView.setBadgeShown(false);
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    /**
     * 页面变化监听
     *
     * @author Administrator
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    binding.CreditSucceedText.setTextColor(getActivity().getResources().getColor(R.color.title_bg));
                    binding.tvInHand.setTextColor(getActivity().getResources().getColor(R.color.black));
                    binding.tvCreditFailure.setTextColor(getActivity().getResources().getColor(R.color.black));
                    binding.CreditSucceedBottomline.setVisibility(View.VISIBLE);
                    binding.InHandBottomline.setVisibility(View.INVISIBLE);
                    binding.CreditFailureBottomline.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    binding.CreditSucceedText.setTextColor(getActivity().getResources().getColor(R.color.black));
                    binding.tvInHand.setTextColor(getActivity().getResources().getColor(R.color.title_bg));
                    binding.tvCreditFailure.setTextColor(getActivity().getResources().getColor(R.color.black));
                    binding.CreditSucceedBottomline.setVisibility(View.INVISIBLE);
                    binding.InHandBottomline.setVisibility(View.VISIBLE);
                    binding.CreditFailureBottomline.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    binding.CreditSucceedText.setTextColor(getActivity().getResources().getColor(R.color.black));
                    binding.tvInHand.setTextColor(getActivity().getResources().getColor(R.color.black));
                    binding.tvCreditFailure.setTextColor(getActivity().getResources().getColor(R.color.title_bg));
                    binding.CreditSucceedBottomline.setVisibility(View.INVISIBLE);
                    binding.InHandBottomline.setVisibility(View.INVISIBLE);
                    binding.CreditFailureBottomline.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            currIndex = arg0;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    public class HelpFragmentPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentsList;
        boolean flag = false;

        public HelpFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public HelpFragmentPagerAdapter(FragmentManager fm,
                                        ArrayList<Fragment> fragments) {
            super(fm);
            this.fragmentsList = fragments;
        }

        @Override
        public int getCount() {
            return fragmentsList.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragmentsList.get(arg0);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public Object instantiateItem(View container, int position) {
            // 得到缓存的fragment
            Fragment fragment = (Fragment) super.instantiateItem(container,
                    position);
            FragmentManager fm = getChildFragmentManager();
            // 得到tag，这点很重要
            String fragmentTag = fragment.getTag();
            // 因为固定要更新第二个fragment，所以position固定写成1，而flag是我写的更新的标记，默认为false，如果要更新，则置为true
            if (flag && position == 0) {
                // 如果这个fragment需要更新
                FragmentTransaction ft = fm.beginTransaction();
                // 移除旧的fragment
                ft.remove(fragment);
                // 换成新的fragment，定义成你想要的新的fragment
//                fragment = new ProjectInformationFragment();

                // 添加新fragment时必须用前面获得的tag，这点很重要
                ft.add(container.getId(), fragment, fragmentTag);
                ft.attach(fragment);
                ft.commitAllowingStateLoss();
            } else if (flag && position == 1) {
                // 如果这个fragment需要更新
                FragmentTransaction ft = fm.beginTransaction();
                // 移除旧的fragment
                ft.remove(fragment);
                // 换成新的fragment，定义成你想要的新的fragment
//                fragment = new InvestInTalentFragment();

                // 添加新fragment时必须用前面获得的tag，这点很重要Fs
                ft.add(container.getId(), fragment, fragmentTag);
                ft.attach(fragment);
                ft.commitAllowingStateLoss();
            } else if (flag && position == 2) {
                // 如果这个fragment需要更新
                FragmentTransaction ft = fm.beginTransaction();
                // 移除旧的fragment
                ft.remove(fragment);
                // 换成新的fragment，定义成你想要的新的fragment
//                fragment = new RepaymentPlanFragment();

                // 添加新fragment时必须用前面获得的tag，这点很重要Fs
                ft.add(container.getId(), fragment, fragmentTag);
                ft.attach(fragment);
                ft.commitAllowingStateLoss();
            }
            return fragment;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
