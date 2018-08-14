package com.grandream.dagt.ui.dialog;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.grandream.dagt.ui.dialog.exchange.DialogExchangeModel;
import com.grandream.dagt.utils.LogUtils;


/**
 * Created by Administrator on 2016/2/2.
 */
public class CustomerDialogFragment extends BaseDialogFragment {

    private CustomerFragmentCallBack mCallBack;

    public static CustomerDialogFragment getInstance(Bundle bundle) {
        CustomerDialogFragment baseDialogFragment = new CustomerDialogFragment();
        baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
    }

    public CustomerDialogFragment() {

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
                mCallBack = DialogExchangeModel.getCustomerFragmentCallBack();
            }
        }
    }

    public void setCustomerFragmentCallBack(CustomerFragmentCallBack callback) {
        mCallBack = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mContentView = null;
        if (getTargetFragment() != null && getTargetFragment() instanceof CustomerFragmentCallBack) {
            mContentView = ((CustomerFragmentCallBack) getTargetFragment()).getCustomerView(mDialogTag);
        } else if (getActivity() != null && getActivity() instanceof CustomerFragmentCallBack) {
            mContentView = ((CustomerFragmentCallBack) getActivity()).getCustomerView(mDialogTag);
        } else if (mCallBack != null) {
            mContentView = mCallBack.getCustomerView(mDialogTag);
        }
        FrameLayout layout = new FrameLayout(getActivity());
        layout.setClickable(true);
        layout.setOnClickListener(mSpaceClickListener);
        if (mContentView != null && mContentView.getLayoutParams() != null){
            if (null == mContentView.getParent()){
                LogUtils.d("custom,mContentView.getParent()");
                layout.addView(mContentView, mContentView.getLayoutParams());
            }
            mContentView.setClickable(true);
        }
        return layout;
    }

}
