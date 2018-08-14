package com.grandream.dagt.ui.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grandream.dagt.R;
import com.grandream.dagt.ui.dialog.exchange.DialogExchangeModel;

/**
 * Created by Administrator on 2016/2/2.
 */
public class SingleInfoDialogFragment extends BaseDialogFragment {


    private TextView mDlgContent, mDlgButton/* , mDlgTitle */;

    public static SingleInfoDialogFragment getInstance(Bundle bundle){
        SingleInfoDialogFragment baseDialogFragment = new SingleInfoDialogFragment();
        baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
    }
    public SingleInfoDialogFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            DialogExchangeModel DialogExchangeModel = ((DialogExchangeModel.DialogExchangeModelBuilder) bundle.getSerializable(TAG)).creat();
            if(DialogExchangeModel!=null){
                mDialogTag = DialogExchangeModel.getTag();
                mTitleTxt = DialogExchangeModel.getDialogTitle();
                mSingleBtnTxt = DialogExchangeModel.getSingleText();
                mContentTxt = DialogExchangeModel.getDialogContext();
                gravity = DialogExchangeModel.getGravity();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.dialog_error_layout, container, false);
        contentView.setOnClickListener(mSpaceClickListener);


        mDlgContent = (TextView) contentView.findViewById(R.id.content_text);
        if (!TextUtils.isEmpty(mContentTxt)) {
            mDlgContent.setText(mContentTxt);
            mDlgContent.setGravity(gravity);
        }

        mDlgButton = (TextView) contentView.findViewById(R.id.single_btn);
        mDlgButton.setClickable(true);
        if (!TextUtils.isEmpty(mSingleBtnTxt)) {
            mDlgButton.setText(mSingleBtnTxt);
        }

        mDlgButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Fragment tarFragment = getTargetFragment();
                Activity activity = getActivity();
                dismissSelf();
                if (tarFragment != null && tarFragment instanceof SingleDialogFragmentCallBack) {
                    ((SingleDialogFragmentCallBack) tarFragment).onSingleBtnClick(mDialogTag);
                } else if (activity != null && activity instanceof SingleDialogFragmentCallBack) {
                    ((SingleDialogFragmentCallBack) activity).onSingleBtnClick(mDialogTag);
                }

            }
        });

        return contentView;
    }


}
