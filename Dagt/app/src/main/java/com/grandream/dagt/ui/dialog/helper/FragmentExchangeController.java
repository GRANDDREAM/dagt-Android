package com.grandream.dagt.ui.dialog.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;


import com.grandream.dagt.R;
import com.grandream.dagt.common.AppContext;
import com.grandream.dagt.utils.DeviceUtil;
import com.grandream.dagt.utils.LogUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/2.
 */
public class FragmentExchangeController {

    public static void replaceFragment(FragmentManager fragmentManager, Fragment targetFragment, String tag) {
        replaceFragment(fragmentManager, targetFragment, tag, android.R.id.content);
    }

//    public static void replaceFragmentdown(FragmentManager fragmentManager, Fragment fragment, String tag) {
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        //添加Fragment的进入动画
//        transaction.setCustomAnimations(R.anim.anim_sild_down, 0, R.anim.anim_sild_down, 0)
//                .replace(android.R.id.content, fragment,tag)
//                .commit();
//    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment targetFragment, String tag, int postion) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(postion, targetFragment, tag);
        transaction.commitAllowingStateLoss();
    }

    public static void initFragment(FragmentManager fragmentManager, Fragment targetFragment, String tag) {
        initFragment(fragmentManager, targetFragment, tag, android.R.id.content);
    }

    public static void initFragment(FragmentManager fragmentManager, Fragment targetFragment, String tag, int postion) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(postion, targetFragment, tag);
        transaction.commitAllowingStateLoss();
    }

    public static void initFragment(FragmentManager fragmentManager, Fragment targetFragment, String tag, int postion, int animIn, int animOut, int animCloseIn, int animCloseOut) {
        if (!DeviceUtil.getAnimationSetting()) {
            animIn = 0;
            animOut = 0;
            animCloseIn = 0;
            animCloseOut = 0;
        } else {
            if (animIn < 0) {
                animIn = 0;
            }
            if (animOut < 0) {
                animOut = 0;
            }
            if (animCloseIn < 0) {
                animCloseIn = 0;
            }
            if (animCloseOut < 0) {
                animCloseOut = 0;
            }
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(animIn, animOut, animCloseIn, animCloseOut);
        transaction.add(postion, targetFragment, tag);
        transaction.commitAllowingStateLoss();
    }

    public static void addFragment(FragmentManager supportFragmentManager, Fragment baseDialogFragment, String tag) {
        addFragment(supportFragmentManager, baseDialogFragment, Window.ID_ANDROID_CONTENT, tag);
    }


    public static void addFragmentdown(FragmentManager supportFragmentManager, Fragment baseDialogFragment, String tag) {
        replaceFragmentdown(supportFragmentManager, baseDialogFragment, Window.ID_ANDROID_CONTENT, tag);
    }


    public static void addFragment(FragmentManager supportFragmentManager, Fragment baseDialogFragment, int content, String tag) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_fragment_in, R.anim.anim_fragment_out, R.anim.anim_fragment_close_in, R.anim.anim_fragment_close_out);
        Fragment fragment = supportFragmentManager.findFragmentById(content);
        if (fragment != null) {
            if (fragment instanceof OnBackStackChangedListener) {
                supportFragmentManager.addOnBackStackChangedListener((OnBackStackChangedListener) fragment);
            }
            transaction.hide(fragment);
        }
        transaction.add(content, baseDialogFragment, tag);
        transaction.addToBackStack(tag);
        transaction.commitAllowingStateLoss();
    }

    public static void replaceFragmentdown(FragmentManager supportFragmentManager, Fragment baseDialogFragment, int content, String tag) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_sild_down, 0, R.anim.anim_sild_down, 0);
        Fragment fragment = supportFragmentManager.findFragmentById(content);
        if (fragment != null) {
            if (fragment instanceof OnBackStackChangedListener) {
                supportFragmentManager.addOnBackStackChangedListener((OnBackStackChangedListener) fragment);
            }
            transaction.hide(fragment);
        }
        transaction.add(content, baseDialogFragment, tag);
        transaction.addToBackStack(tag);
        transaction.commitAllowingStateLoss();
    }


    public static void addFragmentForFlightTransitionToOrder(FragmentManager supportFragmentManager, Fragment baseDialogFragment, int content, String tag) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_fragment_in, R.anim.anim_fragment_out, R.anim.anim_fragment_close_in, R.anim.anim_fragment_close_out);
        Fragment fragment = supportFragmentManager.findFragmentById(content);
        if (fragment != null) {
            if (fragment instanceof OnBackStackChangedListener) {
                supportFragmentManager.addOnBackStackChangedListener((OnBackStackChangedListener) fragment);
            }
            transaction.hide(fragment);
        }
        transaction.add(Window.ID_ANDROID_CONTENT, baseDialogFragment, tag);
        transaction.addToBackStack(tag);
        transaction.commitAllowingStateLoss();
    }

    public static void addFragment(FragmentManager supportFragmentManager, Fragment baseDialogFragment, int content, String tag, int animIn, int animOut, int animCloseIn, int animCloseOut) {
        if (!DeviceUtil.getAnimationSetting()) {
            animIn = 0;
            animOut = 0;
            animCloseIn = 0;
            animCloseOut = 0;
        } else {
            if (animIn < 0) {
                animIn = 0;
            }
            if (animOut < 0) {
                animOut = 0;
            }
            if (animCloseIn < 0) {
                animCloseIn = 0;
            }
            if (animCloseOut < 0) {
                animCloseOut = 0;
            }
        }

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.setCustomAnimations(animIn, animOut, animCloseIn, animCloseOut);
        Fragment fragment = supportFragmentManager.findFragmentById(content);
        if (fragment != null) {
            if (fragment instanceof OnBackStackChangedListener) {
                supportFragmentManager.addOnBackStackChangedListener((OnBackStackChangedListener) fragment);
            }
            transaction.hide(fragment);
        }
        transaction.add(content, baseDialogFragment, tag);
        transaction.addToBackStack(tag);
        transaction.commitAllowingStateLoss();
    }


    public static void addWithoutAnimFragment(FragmentManager supportFragmentManager, Fragment baseDialogFragment, String tag) {
        addWithoutAnimFragment(supportFragmentManager, baseDialogFragment, android.R.id.content, tag);
    }

    public static void addWithoutAnimFragment(FragmentManager supportFragmentManager, Fragment baseDialogFragment, int content, String tag) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(content, baseDialogFragment, tag);
        transaction.addToBackStack(tag);
        transaction.commitAllowingStateLoss();
    }

    public static void addWithoutStackFragment(FragmentManager supportFragmentManager, Fragment baseDialogFragment, int content, String tag) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(content, baseDialogFragment, tag);
        transaction.commitAllowingStateLoss();
    }

    public static void addWithAnimWithoutStackFragment(FragmentManager supportFragmentManager, Fragment baseDialogFragment, int content, String tag) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_fragment_bottom_in, -1, -1, -1);
        Fragment fragment = supportFragmentManager.findFragmentById(content);
        if (fragment != null) {
            if (fragment instanceof OnBackStackChangedListener) {
                supportFragmentManager.addOnBackStackChangedListener((OnBackStackChangedListener) fragment);
            }
            transaction.hide(fragment);
        }
        transaction.add(content, baseDialogFragment, tag);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 移除Fragment
     *
     * @param fragmentManager
     * @param tag
     */
    public static void removeFragment(FragmentManager fragmentManager, String tag) {
        // TODO Auto-generated method stub
        if (fragmentManager != null) {
            try {
                if (fragmentManager != null && fragmentManager.findFragmentByTag(tag) != null) {
                    fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            Fragment targetFragment = fragmentManager.findFragmentByTag(tag);
            if (targetFragment != null) {
                FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();
                localFragmentTransaction.remove(targetFragment);
                localFragmentTransaction.commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
        }
    }

    /**
     * 移除Fragment
     *
     * @param fragmentManager
     * @param targetFragment
     */
    public static void removeFragment(FragmentManager fragmentManager, Fragment targetFragment) {
        // TODO Auto-generated method stub
        if (fragmentManager != null) {
            String tag = targetFragment.getTag();
            try {
                try {
                    if (fragmentManager != null && fragmentManager.findFragmentByTag(tag) != null) {
                        fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }
                } catch (Exception e) {
                }
                FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();
                localFragmentTransaction.remove(targetFragment);
                localFragmentTransaction.commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();

                Fragment fragment = fragmentManager.findFragmentByTag(tag);
                if (fragment != null) {
                    // FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();
                    localFragmentTransaction.remove(fragment);
                    localFragmentTransaction.commitAllowingStateLoss();
                    fragmentManager.executePendingTransactions();
                }
            } catch (Exception e) {
            }
        }
    }

    public static int getStatuBar() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = AppContext.getInstance().getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Fragment> getAllFragments(FragmentActivity fragmentActivity) {
        ArrayList<Fragment> fragments = null;
        if (fragmentActivity == null) {
            return fragments;
        }

        FragmentManager fragmentMgr = fragmentActivity.getSupportFragmentManager();
        if (fragmentMgr == null) {
            return fragments;
        }

        try {
            Class<?> clazz = fragmentMgr.getClass();
            Field field = clazz.getDeclaredField("mAdded");
            field.setAccessible(true);
            fragments = new ArrayList<Fragment>();
            if (field.get(fragmentMgr) != null) {
                fragments.addAll((ArrayList<Fragment>) field.get(fragmentMgr));
            }

        } catch (Exception e) {
                LogUtils.w("Exception", e);
        }

        return fragments;
    }


}
