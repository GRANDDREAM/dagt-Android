package com.grandream.dagt.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.help.FragmentExchangeConroller;
import com.grandream.dagt.utils.ActivityChecker;

/**
 * 支持Fragment跳转的 基于BaseActivity的container
 * Created by chenjing on 2018/3/9.
 */

public class GenericFragmentActivity extends BaseFragmentActivity {
    protected static final String KEY_FRAGMENT_CLASS = "KEY_FRAGMENT_CLASS";
    protected static final String KEY_FRAGMENT_ARGS = "KEY_FRAGMENT_ARGS";
    protected String mFragmentClassName;
//    AlertDialog alertDialog = new AlertDialog(AppContext.getInstance());

    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
//        Utils.showClickNet(getApp/licationContext(),alertDialog);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Bundle args = getIntent().getExtras();
        String fragmentClassName = args.getString(KEY_FRAGMENT_CLASS);
        try {
            mFragment = (Fragment) Class.forName(fragmentClassName).newInstance();
            Bundle argument = args.getBundle(KEY_FRAGMENT_ARGS);
            mFragment.setArguments(argument);
            FragmentExchangeConroller.initFragment(
                    getSupportFragmentManager(), mFragment, fragmentClassName);
            mFragmentClassName = fragmentClassName;
        } catch (Exception e) {
            throw new IllegalStateException("Has error in new instance of fragment");
        }
    }

    @Override
    protected void onResume() {
//        Utils.showClickNet(getApplicationContext(), alertDialog);
        super.onResume();
    }

    public static void start(Activity from, Class<?> fragmentClass, Bundle args) {
        if (ActivityChecker.needBreak(from))
            return;
        Intent intent = new Intent(from, GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        from.startActivity(intent);
    }

    public static void startForResult(Activity from, Class<?> fragmentClass, Bundle args, int requestCode) {
        if (ActivityChecker.needBreak(from))
            return;
        Intent intent = new Intent(from, GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        from.startActivityForResult(intent, requestCode);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mFragment != null && mFragment instanceof BaseFragment.OnActivityResultListener) {
            ((BaseFragment.OnActivityResultListener) mFragment).onActivityResult(this, requestCode, resultCode, data);
        }
    }

}