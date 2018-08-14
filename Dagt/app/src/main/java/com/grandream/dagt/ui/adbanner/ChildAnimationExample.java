package com.grandream.dagt.ui.adbanner;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;

import com.grandream.dagt.R;
import com.grandream.dagt.ui.adbanner.animations.BaseAnimationInterface;


public class ChildAnimationExample implements BaseAnimationInterface {

    private final static String TAG = "ChildAnimationExample";

    @Override
    public void onPrepareCurrentItemLeaveScreen(View current) {
        View descriptionLayout = current.findViewById(R.id.description_layout);
        if(descriptionLayout!=null){
            current.findViewById(R.id.description_layout).setVisibility(View.INVISIBLE);
        }
        Log.e(TAG,"onPrepareCurrentItemLeaveScreen called");
    }

    @Override
    public void onPrepareNextItemShowInScreen(View next) {
        View descriptionLayout = next.findViewById(R.id.description_layout);
        if(descriptionLayout!=null){
            next.findViewById(R.id.description_layout).setVisibility(View.INVISIBLE);
        }
        Log.e(TAG,"onPrepareNextItemShowInScreen called");
    }

    @Override
    public void onCurrentItemDisappear(View view) {
        Log.e(TAG,"onCurrentItemDisappear called");
    }

    @Override
    public void onNextItemAppear(View view) {

        View descriptionLayout = view.findViewById(R.id.description_layout);
        if(descriptionLayout!=null){
            view.findViewById(R.id.description_layout).setVisibility(View.VISIBLE);
            ValueAnimator animator = ObjectAnimator.ofFloat(
                    descriptionLayout, "y", -descriptionLayout.getHeight(),
                    0).setDuration(500);
            animator.start();
        }
        Log.e(TAG,"onCurrentItemDisappear called");
    }
}
