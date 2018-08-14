package com.grandream.dagt.base;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.grandream.dagt.activity.MainActivity;
import com.grandream.dagt.activity.SplashActivity;
import com.grandream.dagt.common.AppContext;
import com.grandream.dagt.common.AppManager;
import com.grandream.dagt.common.ViewConstant;
import com.grandream.dagt.fragment.my.gesture.GestureLoginActivity;
import com.grandream.dagt.utils.ACache;
import com.grandream.dagt.utils.Utils;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;

/**
 * BsaeActivity
 * Created by chenjing on 18/2/6.
 */

public class BaseFragmentActivity extends FragmentActivity {
    public ACache aCache;
    private static BaseFragmentActivity AppInstance;
    protected ImmersionBar mImmersionBar;
    private AppContext appContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.getInstance().setCurrentActivity(this);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        // 修改状态栏颜色，4.4+生效
        if (isImmersionBarEnabled())
            initImmersionBar();

        aCache = AppContext.getInstance().getACache();
        appContext = AppContext.getInstance();
    }


    protected boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        //DecorView即为activity的顶级view
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return screenHeight * 2 / 3 > rect.bottom;
    }

    public static BaseFragmentActivity getInstance() {
        return AppInstance;
    }

    @TargetApi(19)
    protected void setTranslucentStatus() {
        Window window = getWindow();
        // Translucent status bar
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        // Translucent navigation bar
//        window.setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//     WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    public void quit() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra(ViewConstant.EXIT_APP, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }


    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
        // 结束Activity从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }


    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ViewConstant.ISACTIVE) {
            if (Utils.isLogin(this)) {
                if (Utils.getIsOpen(this)) {
                    Intent intent = new Intent(this, GestureLoginActivity.class);
                    intent.putExtra("ISOPEN", false);
                    intent.putExtra("ONSTRART", true);
                    startActivity(intent);
                }
            }
        }
    }
}

