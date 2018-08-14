package com.grandream.dagt.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.grandream.dagt.R;
import com.grandream.dagt.base.BaseFragmentActivity;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.home.HomeBottom;
import com.grandream.dagt.common.ViewConstant;
import com.grandream.dagt.databinding.ActivityMainBinding;
import com.grandream.dagt.fragment.credit.manage.CreditManageFragment;
import com.grandream.dagt.fragment.home.HomeFragment;
import com.grandream.dagt.fragment.my.MyDetailFragment;
import com.grandream.dagt.fragment.user.LogingFragment;
import com.grandream.dagt.fragment.user.SecondLoginFragment;
import com.grandream.dagt.fragment.wallet.WalletFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.my.WrningBody;
import com.grandream.dagt.utils.Utils;
import com.itingchunyu.badgeview.BadgeTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjing on 2018/3/19.
 */

public class MainActivity extends BaseFragmentActivity {
    private String TAG = "MainActivity";
    private HomeFragment homeFragment;
    private ActivityMainBinding binding;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private CreditManageFragment creditManageFragment;
    private WalletFragment walletFragment;
    private MyDetailFragment meFragment;
    private int warning;
    int currSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setClick(this);
        fragmentManager = getSupportFragmentManager();
        if (Utils.isLogin(this)) {
            getData();
        }
        initData();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (creditManageFragment != null) {
            fragmentTransaction.hide(creditManageFragment);
        }
        if (walletFragment != null) {
            fragmentTransaction.hide(walletFragment);
        }
        if (meFragment != null) {
            fragmentTransaction.hide(meFragment);
        }
    }

    /**
     * 初始化控件属性
     */
    private void initData() {
        transaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle1 = new Bundle();
        homeFragment.setArguments(bundle1);
        mFragmentList.add(homeFragment);

        CreditManageFragment creditManageFragment = new CreditManageFragment();
        Bundle bundle2 = new Bundle();
        creditManageFragment.setArguments(bundle2);
        mFragmentList.add(creditManageFragment);

        WalletFragment walletFragment = new WalletFragment();
        Bundle bundle3 = new Bundle();
        walletFragment.setArguments(bundle3);
        mFragmentList.add(walletFragment);

        MyDetailFragment meFragment = new MyDetailFragment();
        Bundle bundle4 = new Bundle();
        meFragment.setArguments(bundle4);
        mFragmentList.add(meFragment);
        changeFragment(0); //默认显示第一页
        select(0);
    }

    /**
     * 选择的tab
     *
     * @param i
     */
    public void select(int i) {
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        hideFragment(fragmentTransaction);
        // 选中
        Drawable top_home_check = getResources().getDrawable(
                R.mipmap.menu_1);
        top_home_check.setBounds(0, 0, top_home_check.getMinimumWidth(),
                top_home_check.getMinimumHeight());

        Drawable top_invest_check = getResources().getDrawable(
                R.mipmap.menu_2);
        top_invest_check.setBounds(0, 0, top_invest_check.getMinimumWidth(),
                top_invest_check.getMinimumHeight());

        Drawable top_asset_check = getResources().getDrawable(
                R.mipmap.menu_3);
        top_asset_check.setBounds(0, 0, top_asset_check.getMinimumWidth(),
                top_asset_check.getMinimumHeight());

        Drawable top_wyrz_check = getResources().getDrawable(
                R.mipmap.menu_4);
        top_wyrz_check.setBounds(0, 0, top_wyrz_check.getMinimumWidth(),
                top_wyrz_check.getMinimumHeight());

        // 未选中
        Drawable top_home_uncheck = getResources().getDrawable(
                R.mipmap.menu_1_normal);
        top_home_uncheck.setBounds(0, 0, top_home_uncheck.getMinimumWidth(),
                top_home_uncheck.getMinimumHeight());

        Drawable top_invest_uncheck = getResources().getDrawable(
                R.mipmap.menu_2_normal);
        top_invest_uncheck.setBounds(0, 0,
                top_invest_uncheck.getMinimumWidth(),
                top_invest_uncheck.getMinimumHeight());

        Drawable top_asset_uncheck = getResources().getDrawable(
                R.mipmap.menu_3_normal);
        top_asset_uncheck.setBounds(0, 0, top_asset_uncheck.getMinimumWidth(),
                top_asset_uncheck.getMinimumHeight());

        Drawable top_wyrz_uncheck = getResources().getDrawable(
                R.mipmap.menu_4_normal);
        top_wyrz_uncheck.setBounds(0, 0, top_wyrz_uncheck.getMinimumWidth(),
                top_wyrz_uncheck.getMinimumHeight());

        switch (i) {
            case 0:
                currSel = 0;
                binding.home.setTextColor(getResources().getColor(
                        R.color.tab_selected_color));
                binding.creditManager.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));
                binding.wallet.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));
                binding.me.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));
                binding.home
                        .setCompoundDrawables(null, top_home_check, null, null);
                binding.creditManager.setCompoundDrawables(null, top_invest_uncheck, null,
                        null);
                binding.wallet.setCompoundDrawables(null, top_asset_uncheck,
                        null, null);
                binding.me.setCompoundDrawables(null, top_wyrz_uncheck,
                        null, null);
                break;
            case 1:
                currSel = 1;
                binding.home.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));
                binding.creditManager.setTextColor(getResources().getColor(
                        R.color.tab_selected_color));
                binding.wallet.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));
                binding.me.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));

                binding.home.setCompoundDrawables(null, top_home_uncheck, null,
                        null);
                binding.creditManager
                        .setCompoundDrawables(null, top_invest_check, null, null);
                binding.wallet.setCompoundDrawables(null, top_asset_uncheck,
                        null, null);
                binding.me.setCompoundDrawables(null, top_wyrz_uncheck,
                        null, null);
                break;
            case 2:
                currSel = 2;
                binding.home.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));
                binding.creditManager.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));
                binding.wallet.setTextColor(getResources().getColor(
                        R.color.tab_selected_color));
                binding.me.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));

                binding.home.setCompoundDrawables(null, top_home_uncheck, null,
                        null);
                binding.creditManager.setCompoundDrawables(null, top_invest_uncheck, null,
                        null);
                binding.wallet.setCompoundDrawables(null, top_asset_check, null,
                        null);
                binding.me.setCompoundDrawables(null, top_wyrz_uncheck,
                        null, null);
                break;
            case 3:
                binding.home.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));
                binding.creditManager.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));
                binding.wallet.setTextColor(getResources().getColor(
                        R.color.tab_normal_color));
                binding.me.setTextColor(getResources().getColor(
                        R.color.tab_selected_color));

                binding.home.setCompoundDrawables(null, top_home_uncheck, null,
                        null);
                binding.creditManager.setCompoundDrawables(null, top_invest_uncheck, null,
                        null);
                binding.wallet.setCompoundDrawables(null, top_asset_uncheck,
                        null, null);
                binding.me.setCompoundDrawables(null, top_wyrz_check, null,
                        null);
                currSel = 3;
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void changeFragment(int currentPosition) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, mFragmentList.get(currentPosition));
        transaction.commit();
    }

    @Override
    protected void onResume() {
        if (Utils.isLogin(this)) {
            getData();
        }
        super.onResume();
    }

    public void getData() {
        WrningBody body = new WrningBody();
        body.setUser_id(Utils.getUser_id(this));
        ApiService.bottomData(this, new IHttpCallback<HomeBottom>() {
            @Override
            public void onNext(HttpResult<HomeBottom> t) {
                BadgeTextView mBadgeTextView = new BadgeTextView(MainActivity.this);
                if (t.getResponse_data().getDatabean().getWarning() == 1) {
                    mBadgeTextView.setTargetView(binding.creditManager);
                    mBadgeTextView.setBadgeCount(0).setmDefaultTopPadding(20).setmDefaultRightPadding(100).setmDefaultRadius(8).setBackgroundColor(getResources().getColor(R.color.red));
                    mBadgeTextView.setBadgeShown(true);
                } else {
                    mBadgeTextView.setBadgeShown(false);
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }


    public void tohome(View v) {
        changeFragment(0);
        select(0);
    }

    public void tocreditmanager(View v) {
        if (!Utils.isLogin(this)) {
            Utils.toLoging(this);
            changeFragment(0);
            select(0);
        } else {
            changeFragment(1);
            select(1);
        }
    }

    public void towallet(View v) {
        if (!Utils.isLogin(this)) {
            Utils.toLoging(this);
            changeFragment(0);
            select(0);
        } else {
            changeFragment(2);
            select(2);
        }
    }


    public void tome(View v) {
        if (!Utils.isLogin(this)) {
            Utils.toLoging(this);
            changeFragment(0);
            select(0);
        } else {
            changeFragment(3);
            select(3);
        }
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // moveTaskToBack(true);

            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                if (currSel != 0) {
                    changeFragment(0);
                    select(0);
                } else {
                    if ((System.currentTimeMillis() - exitTime) > 2000) {
                        Toast.makeText(getApplicationContext(), "再按一次退出程序",
                                Toast.LENGTH_SHORT).show();
                        exitTime = System.currentTimeMillis();
                    } else {
                        quit();
                    }
                }
            } else {
                try {
                    getSupportFragmentManager().popBackStackImmediate();
                } catch (Exception e) {
                    // TODO: handle exception
                }
                return true;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
