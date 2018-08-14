package com.grandream.dagt.ui.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import com.grandream.dagt.R;
import com.grandream.dagt.base.BaseFragmentActivity;
import com.grandream.dagt.ui.dialog.exchange.DialogExchangeModel;
import com.grandream.dagt.ui.dialog.helper.FragmentExchangeController;


/**
 * Created by Administrator on 2016/2/2.
 */
public class BaseDialogFragment extends DialogFragment {

    public final static String TAG = " HDBaseDialogFragment";
    protected String mDialogTag;// 标记
    protected String mTitleTxt;// 标题
    protected String mPositiveBtnTxt;// 确认操作
    protected String mNegativeBtnTxt;// 取消操作
    protected String mSingleBtnTxt;// 单个button文字
    protected String mContentTxt;// 内容
    public boolean bIsBackable;// 是否back取消
    public boolean bIsSpaceable;// 是否空白取消
    public OnClickListener compatibilityListener;// 错误弹框 按键点击事件
    public OnClickListener compatibilityPositiveListener, compatibilityNegativeListener;
    public int gravity = Gravity.CENTER;
    protected OnClickListener mSpaceClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (bIsSpaceable) {
                Fragment tarFragment = getTargetFragment();
                Activity activity = getActivity();
                dismissSelf();
                if (tarFragment != null && tarFragment instanceof SpaceAndCancelCallBack) {
                    ((SpaceAndCancelCallBack) tarFragment).onSpaceClick(mDialogTag);
                } else if (activity != null && activity instanceof SpaceAndCancelCallBack) {
                    ((SpaceAndCancelCallBack) activity).onSpaceClick(mDialogTag);
                }
            }
        }
    };

    public static BaseDialogFragment getInstance(Bundle bundle) {
        BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
        baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
    }

    public BaseDialogFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.ThemeHolo);
        initArgus();
    }

    protected void initArgus() {
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            DialogExchangeModel DialogExchangeModel = ((DialogExchangeModel.DialogExchangeModelBuilder) bundle.getSerializable(TAG)).creat();
            if (DialogExchangeModel != null) {
                mDialogTag = DialogExchangeModel.getTag();
                bIsBackable = DialogExchangeModel.isBackable();
                bIsSpaceable = DialogExchangeModel.isSpaceable();
                mContentTxt = DialogExchangeModel.getDialogContext();
                compatibilityListener = DialogExchangeModel.compatibilityListener;
                setCancelable(bIsBackable);
            }
        }
    }

    @Override
    //移除Fragment
    public void dismiss() {
        if (getActivity() != null && getActivity() instanceof BaseFragmentActivity) {
        }
        super.dismissAllowingStateLoss();
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        return show(transaction, tag, true);
    }

    public int show(FragmentTransaction transaction, String tag, boolean allowStateLoss) {
        transaction.add(this, tag);
        int mBackStackId = allowStateLoss ? transaction.commitAllowingStateLoss() : transaction.commit();
        return mBackStackId;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        // TODO Auto-generated method stub
        super.onCancel(dialog);
        Activity activity = getActivity();
        if (getTargetFragment() != null && getTargetFragment() instanceof SpaceAndCancelCallBack) {
            ((SpaceAndCancelCallBack) getTargetFragment()).onCanceled(mDialogTag);
        } else if (activity != null && activity instanceof SpaceAndCancelCallBack) {
            ((SpaceAndCancelCallBack) activity).onCanceled(mDialogTag);
        }
    }

    public void dismissSelf() {
        // TODO Auto-generated method stub
        String tag = "";
        tag = getTag();
        FragmentExchangeController.removeFragment(getFragmentManager(), this);
        try {
            if (tag != null && getFragmentManager() != null && getFragmentManager().findFragmentByTag(tag) != null)
                getFragmentManager().popBackStack(tag,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);
            // }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


}
