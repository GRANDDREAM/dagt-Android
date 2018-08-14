package com.grandream.dagt.ui.dialog;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.grandream.dagt.R;
import com.grandream.dagt.ui.dialog.exchange.DialogExchangeModel;

/**
 * Created by Administrator on 2016/2/2.
 */
public class ProcessDialogFragment extends BaseDialogFragment {


    //private TextView mDlgContent;
    private ImageView img_gold;
    public String mBussinessCode;// 服务token
    private boolean bIsBussinessCancelable;// 服务是否可取消

    public static ProcessDialogFragment getInstance(Bundle bundle) {
        ProcessDialogFragment baseDialogFragment = new ProcessDialogFragment();
        baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
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
                mBussinessCode = DialogExchangeModel.getTag();
                mContentTxt = DialogExchangeModel.getDialogContext();
                bIsBussinessCancelable = DialogExchangeModel.isBussinessCancleable();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.process_load_data_layout, container, false);
        layoutView.setOnClickListener(mSpaceClickListener);

        img_gold = (ImageView) layoutView.findViewById(R.id.img_loading);
        //设置动画属性
        final Animation anim1 = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_front_to_back);
        final Animation anim2 = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_back__to_front);

        img_gold.startAnimation(anim1);
        anim1.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                img_gold.startAnimation(anim2);

            }
        });

        anim2.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                img_gold.startAnimation(anim1);
            }
        });
        return layoutView;
    }

    @Override
    public void dismiss() {

        super.dismiss();
    }

    @Override
    public void dismissSelf() {
        // TODO Auto-generated method stub

        super.dismissSelf();
    }

}
