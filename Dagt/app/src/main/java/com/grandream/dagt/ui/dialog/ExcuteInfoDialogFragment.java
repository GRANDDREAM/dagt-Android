package com.grandream.dagt.ui.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grandream.dagt.R;
import com.grandream.dagt.base.BaseFragmentActivity;
import com.grandream.dagt.ui.dialog.exchange.DialogExchangeModel;
import com.grandream.dagt.utils.StringUtils;

/**
 * Created by Administrator on 2016/2/2.
 */
public class ExcuteInfoDialogFragment extends BaseDialogFragment {


    private TextView mDlgContent, mBtnLeft/* , mDlgTitle */, mRightBtn;
    private OnClickListener mExcuitePositiveListener, mExcuiteNegativeListener;
    private String mOldTag;
    private DialogType mOldDialogTag;
    public static ExcuteInfoDialogFragment getInstance(Bundle bundle) {
        ExcuteInfoDialogFragment baseDialogFragment = new ExcuteInfoDialogFragment();
        baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
    }

    public ExcuteInfoDialogFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            DialogExchangeModel DialogExchangeModel = ((DialogExchangeModel.DialogExchangeModelBuilder) bundle.getSerializable(TAG)).creat();
            if (DialogExchangeModel != null) {
                mDialogTag = DialogExchangeModel.getTag();
                mOldTag = DialogExchangeModel.getOldTag();
                mOldDialogTag = DialogExchangeModel.getOldDialogType();
                mTitleTxt = DialogExchangeModel.getDialogTitle();
                mPositiveBtnTxt = DialogExchangeModel.getPostiveText();
                mNegativeBtnTxt = DialogExchangeModel.getNegativeText();
                mContentTxt = DialogExchangeModel.getDialogContext();
                gravity = DialogExchangeModel.getGravity();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_excute_layout, container, false);
        view.setOnClickListener(mSpaceClickListener);

        mDlgContent = (TextView) view.findViewById(R.id.content_text);
        if (!StringUtils.emptyOrNull(mContentTxt)) {
            mDlgContent.setText(mContentTxt);
            if (gravity != -1) {
                mDlgContent.setGravity(gravity);
            }

        }
        mBtnLeft = (TextView) view.findViewById(R.id.lef_btn);
        mRightBtn = (TextView) view.findViewById(R.id.right_btn);

        mExcuitePositiveListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment tarFragment = getTargetFragment();
                Activity activity = getActivity();
                if (mDialogTag.equals(DialogManager.CONTENT_HAS_NUMBERINFO)) {
                    dismissSelf();
                    if (activity != null && activity instanceof BaseFragmentActivity) {
                    }

                } else if (mDialogTag.equals(DialogManager.VOIP_CHECK_COMMON_DIALOG)) {
                    if (getTargetFragment() != null && getTargetFragment() instanceof ExcuteDialogFragmentCallBack) {
                        ((ExcuteDialogFragmentCallBack) getTargetFragment()).onPositiveBtnClick(mDialogTag);
                    } else if (activity != null && activity instanceof ExcuteDialogFragmentCallBack) {
                        ((ExcuteDialogFragmentCallBack) activity).onPositiveBtnClick(mDialogTag);
                    }
                    dismissSelf();
                } else if (mDialogTag.equals(DialogManager.VOIP_CHECK_ERROR_DIALOG)) {
                    if (getActivity() != null && getActivity() instanceof BaseFragmentActivity) {

                    }

                    dismissSelf();
                }

                else {
                    dismissSelf();
                    if (tarFragment != null && tarFragment instanceof ExcuteDialogFragmentCallBack) {
                        ((ExcuteDialogFragmentCallBack) tarFragment).onPositiveBtnClick(mDialogTag);
                    } else if (activity != null && activity instanceof ExcuteDialogFragmentCallBack) {
                        ((ExcuteDialogFragmentCallBack) activity).onPositiveBtnClick(mDialogTag);
                    }

                }
            }
        };

        mExcuiteNegativeListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mDialogTag.equals(DialogManager.CONTENT_HAS_NUMBERINFO)) {
                    Fragment tarFragment = getTargetFragment();
                    Activity activity = getActivity();
                    dismissSelf();
                    if (mOldDialogTag == DialogType.SINGLE) {
                        if (tarFragment != null && tarFragment instanceof SingleDialogFragmentCallBack) {
                            ((SingleDialogFragmentCallBack) tarFragment).onSingleBtnClick(mOldTag);
                        } else if (activity != null && activity instanceof SingleDialogFragmentCallBack) {
                            ((SingleDialogFragmentCallBack) activity).onSingleBtnClick(mOldTag);
                        }
                    } else if (mOldDialogTag == DialogType.EXCUTE) {
                        if (tarFragment != null && tarFragment instanceof ExcuteDialogFragmentCallBack) {
                            ((ExcuteDialogFragmentCallBack) tarFragment).onNegtiveBtnClick(mOldTag);
                        } else if (activity != null && activity instanceof ExcuteDialogFragmentCallBack) {
                            ((ExcuteDialogFragmentCallBack) activity).onNegtiveBtnClick(mOldTag);
                        }
                    }
                }
                else {
                    Fragment tarFragment = getTargetFragment();
                    Activity activity = getActivity();
                    dismissSelf();
                    if (tarFragment != null && tarFragment instanceof ExcuteDialogFragmentCallBack) {
                        ((ExcuteDialogFragmentCallBack) tarFragment).onNegtiveBtnClick(mDialogTag);
                    } else if (activity != null && activity instanceof ExcuteDialogFragmentCallBack) {
                        ((ExcuteDialogFragmentCallBack) activity).onNegtiveBtnClick(mDialogTag);
                    }
                }
            }
        };

        if (android.os.Build.VERSION.SDK_INT >= 14) {
            if (!StringUtils.emptyOrNull(mPositiveBtnTxt)) {
                mRightBtn.setText(mPositiveBtnTxt);
            } else {
                mRightBtn.setText(R.string.ok);
            }
            mRightBtn.setOnClickListener(mExcuitePositiveListener);
            mRightBtn.setBackgroundResource(R.drawable.btn_dialog_selector);


            if (!StringUtils.emptyOrNull(mNegativeBtnTxt)) {
                mBtnLeft.setText(mNegativeBtnTxt);
            } else {
                mBtnLeft.setText(R.string.cancel);
            }
            mBtnLeft.setOnClickListener(mExcuiteNegativeListener);
            mBtnLeft.setBackgroundResource(R.drawable.btn_dialog_selector);

        } else {
            if (!StringUtils.emptyOrNull(mPositiveBtnTxt)) {
                mBtnLeft.setText(mPositiveBtnTxt);
            } else {
                mBtnLeft.setText(R.string.ok);
            }
            mBtnLeft.setOnClickListener(mExcuitePositiveListener);
            mBtnLeft.setBackgroundResource(R.drawable.btn_dialog_selector);


            if (!StringUtils.emptyOrNull(mNegativeBtnTxt)) {
                mRightBtn.setText(mNegativeBtnTxt);
            } else {
                mRightBtn.setText(R.string.cancel);
            }
            mRightBtn.setOnClickListener(mExcuiteNegativeListener);
            mRightBtn.setBackgroundResource(R.drawable.btn_dialog_selector);

        }

        return view;
    }

}
