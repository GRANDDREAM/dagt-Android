package com.grandream.dagt.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.grandream.dagt.base.BaseFragmentActivity;
import com.grandream.dagt.ui.dialog.exchange.DialogExchangeModel;
import com.grandream.dagt.utils.ActivityChecker;
import com.grandream.dagt.utils.DeviceUtil;


/**
 * Created by Administrator on 2016/2/2.
 */
public class DialogManager {

    public final static int DIALOG_REQUEST_CODE = 0x2001;
    /**
     * 文案中存在电话号码，转换的弹框
     */
    public static final String CONTENT_HAS_NUMBERINFO = "content has number";
    /**
     * Voip确认弹框
     */
    public static final String VOIP_CHECK_COMMON_DIALOG = "voip common dialog";
    /**
     * Voip校验错误弹框
     */
    public static final String VOIP_CHECK_ERROR_DIALOG = "voip error dialog";
    /**
     * 普遍错误弹框
     */
    public static final String COMMON_BUSSINESS_ERROR_DIALOG = "error dialog";
    /**
     * 普遍网络中断弹框
     */
    public static final String COMMON_BUSSINESS_ERROR_DIALOG_WITH_CALL = "error dialog with call";

    /**
     * 弹框方法
     * <p>
     * fragment与baseActivityV2不可同时为NULL
     *
     * @param fragmentManager     (必传字段)
     * @param DialogExchangeModel (必传字段)
     * @param fragment            (选传)
     * @param baseActivityV2      (选传)
     * @return BaseDialogFragmentV2对象
     */
    public static BaseDialogFragment showDialogFragment(FragmentManager fragmentManager, DialogExchangeModel DialogExchangeModel, Fragment fragment, BaseFragmentActivity baseActivityV2) {

        BaseDialogFragment baseDialogFragment = null;
        if (DialogExchangeModel != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(" HDBaseDialogFragment", DialogExchangeModel.DialogExchangeModelBuilder);
            DialogType HDDialogType = DialogExchangeModel.getDialogType();
            String contextStr = DialogExchangeModel.getDialogContext();
            if (null != contextStr && (contextStr.contains("4000086666") || contextStr.contains("400-008-6666"))) {
                DialogExchangeModel.DialogExchangeModelBuilder.setOldDialogType(HDDialogType);
                HDDialogType = DialogType.EXCUTE;
                DialogExchangeModel.DialogExchangeModelBuilder.setDialogType(DialogType.EXCUTE);
                DialogExchangeModel.DialogExchangeModelBuilder.setPostiveText("呼叫").setNegativeText("知道了");
                DialogExchangeModel.DialogExchangeModelBuilder.setOldTag(DialogExchangeModel.getTag());
                DialogExchangeModel.DialogExchangeModelBuilder.setTag(DialogManager.CONTENT_HAS_NUMBERINFO);
            }
            if (HDDialogType == DialogType.SINGLE) {
                baseDialogFragment = SingleInfoDialogFragment.getInstance(bundle);
            } else if (HDDialogType == DialogType.EXCUTE) {
                baseDialogFragment = ExcuteInfoDialogFragment.getInstance(bundle);
            } else if (HDDialogType == DialogType.CUSTOMER) {
                baseDialogFragment = CustomerDialogFragment.getInstance(bundle);
            } else if (HDDialogType == DialogType.PROGRESS) {
                baseDialogFragment = ProcessDialogFragment.getInstance(bundle);
            }
        }
        if (baseDialogFragment != null) {
            baseDialogFragment.compatibilityListener = DialogExchangeModel.compatibilityListener;
            baseDialogFragment.compatibilityNegativeListener = DialogExchangeModel.compatibilityNegativeListener;
            baseDialogFragment.compatibilityPositiveListener = DialogExchangeModel.compatibilityPositiveListener;
        }
        if (baseDialogFragment != null) {
            if (fragment != null) {
                baseDialogFragment.setTargetFragment(fragment, DIALOG_REQUEST_CODE);
            }
            if (baseActivityV2 != null) {
            }
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(baseDialogFragment, DialogExchangeModel.getTag());
            ft.commitAllowingStateLoss();
        }
        return baseDialogFragment;
    }

    public static BaseDialogFragment showDialogFragment(FragmentManager fragmentManager, DialogExchangeModel DialogExchangeModel, Fragment fragment, BaseFragmentActivity baseActivityV2
            , int animIn, int animOut, int animCloseIn, int animCloseOut) {

        BaseDialogFragment baseDialogFragment = null;
        if (DialogExchangeModel != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(" HDBaseDialogFragment", DialogExchangeModel.DialogExchangeModelBuilder);
            DialogType HDDialogType = DialogExchangeModel.getDialogType();
            String contextStr = DialogExchangeModel.getDialogContext();
            if (null != contextStr && (contextStr.contains("4000086666") || contextStr.contains("400-008-6666"))) {
                DialogExchangeModel.DialogExchangeModelBuilder.setOldDialogType(HDDialogType);
                HDDialogType = DialogType.EXCUTE;
                DialogExchangeModel.DialogExchangeModelBuilder.setDialogType(DialogType.EXCUTE);
                DialogExchangeModel.DialogExchangeModelBuilder.setPostiveText("呼叫").setNegativeText("知道了");
                DialogExchangeModel.DialogExchangeModelBuilder.setOldTag(DialogExchangeModel.getTag());
                DialogExchangeModel.DialogExchangeModelBuilder.setTag(DialogManager.CONTENT_HAS_NUMBERINFO);
            }
            if (HDDialogType == DialogType.SINGLE) {
                baseDialogFragment = SingleInfoDialogFragment.getInstance(bundle);
            } else if (HDDialogType == DialogType.EXCUTE) {
                baseDialogFragment = ExcuteInfoDialogFragment.getInstance(bundle);
            } else if (HDDialogType == DialogType.CUSTOMER) {
                baseDialogFragment = CustomerDialogFragment.getInstance(bundle);
            } else if (HDDialogType == DialogType.PROGRESS) {
                baseDialogFragment = ProcessDialogFragment.getInstance(bundle);
            }
        }
        if (baseDialogFragment != null) {
            baseDialogFragment.compatibilityListener = DialogExchangeModel.compatibilityListener;
            baseDialogFragment.compatibilityNegativeListener = DialogExchangeModel.compatibilityNegativeListener;
            baseDialogFragment.compatibilityPositiveListener = DialogExchangeModel.compatibilityPositiveListener;
        }
        if (baseDialogFragment != null) {
            if (fragment != null) {
                baseDialogFragment.setTargetFragment(fragment, DIALOG_REQUEST_CODE);
            }
            if (baseActivityV2 != null) {
            }
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
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.setCustomAnimations(animIn, animOut, animCloseIn, animCloseOut);
            ft.add(baseDialogFragment, DialogExchangeModel.getTag());
            ft.commitAllowingStateLoss();
        }
        return baseDialogFragment;
    }


    //封装的一些方法:
    public static void showExecuteInfoDialog(BaseFragmentActivity activity, String titel, String content, String tag, boolean isSpaceable,
                                             boolean isBackRemoveable, String postiveText, String negativeText) {
        DialogExchangeModel.DialogExchangeModelBuilder builder = new DialogExchangeModel.DialogExchangeModelBuilder(DialogType.EXCUTE, tag);
        builder.setTag(tag);
        builder.setDialogContext(content);
        builder.setBackable(isBackRemoveable);
        if (!TextUtils.isEmpty(postiveText)) {
            builder.setPostiveText(postiveText);
        }
        if (!TextUtils.isEmpty(negativeText)) {
            builder.setNegativeText(negativeText);
        }

        DialogExchangeModel model = builder.creat();
        showDialogFragment(activity.getSupportFragmentManager(), model, null, activity);
    }

    public static BaseDialogFragment showProcessDialog(BaseFragmentActivity activity, String isShow, String tag, boolean isBackRemoveable,
                                                       boolean isCancelable, boolean isSpaceable) {
        if (isShow.equals("true") || isShow == "true") {
            if (ActivityChecker.needBreak(activity)) {
                return null;
            }
            DialogExchangeModel.DialogExchangeModelBuilder builder = new DialogExchangeModel.DialogExchangeModelBuilder(DialogType.PROGRESS, tag);
            builder.setTag(tag);
            builder.setBackable(isBackRemoveable);
            builder.setSpaceable(isSpaceable);
            builder.setBussinessCancleable(isCancelable);
            DialogExchangeModel model = builder.creat();
            return showDialogFragment(activity.getSupportFragmentManager(), model, null, activity);
        }
        return null;
    }

    //海豚dialog
    public static BaseDialogFragment showScoreProcessDialog(BaseFragmentActivity activity) {
        if (ActivityChecker.needBreak(activity)) {
            return null;
        }
        DialogExchangeModel.DialogExchangeModelBuilder builder = new DialogExchangeModel.DialogExchangeModelBuilder(DialogType.DOLPHIN_PROGRESS, null);
        builder.setBackable(false);
        builder.setSpaceable(false);
        builder.setBussinessCancleable(false);
        DialogExchangeModel model = builder.creat();
        return showDialogFragment(activity.getSupportFragmentManager(), model, null, activity);
    }

    public static void showErrorInfo(BaseFragmentActivity activity, String tag, String title, String content, String buttonText,
                                     boolean isSpaceable, boolean isBackable) {
        if (ActivityChecker.needBreak(activity)) {
            return;
        }
        DialogExchangeModel.DialogExchangeModelBuilder builder = new DialogExchangeModel.DialogExchangeModelBuilder(DialogType.SINGLE, tag);
        builder.setDialogContext(content);
        builder.setTag(tag);
        builder.setDialogTitle(title);
        builder.setBackable(isBackable);
        builder.setSpaceable(isSpaceable);
        builder.setSingleText(buttonText);
        DialogExchangeModel model = builder.creat();
        showDialogFragment(activity.getSupportFragmentManager(), model, null, activity);
    }


}
