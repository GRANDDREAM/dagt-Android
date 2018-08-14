package com.grandream.dagt.fragment.my.gesture;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.grandream.dagt.R;
import com.grandream.dagt.base.BaseFragmentActivity;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.SerciceFeeReslut;
import com.grandream.dagt.bean.home.msg.MarKAllResult;
import com.grandream.dagt.databinding.ActivityCreateGestureBinding;
import com.grandream.dagt.databinding.CreditinformationLayoutBinding;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.my.GestureGetData;
import com.grandream.dagt.requestbody.my.GesturePasswordSet;
import com.grandream.dagt.ui.lockpattern.LockPatternView;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.LockPatternUtil;
import com.grandream.dagt.utils.Utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjing on 2018/5/4.
 */

public class CreateGestureActivity extends BaseFragmentActivity {
    private ActivityCreateGestureBinding binding;
    private List<LockPatternView.Cell> mChosenPattern = null;
    private static final long DELAYTIME = 600L;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_gesture);
        binding.tel.setText(Utils.replaceToXing(Utils.getUser_name(this), 4, Utils.getUser_name(this).length() - 4));
        binding.setClick(this);
        initView();
    }

    private void initView() {
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.lockPatternView.setOnPatternListener(patternListener);

    }

    /**
     * 手势监听
     */
    private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {

        @Override
        public void onPatternStart() {
            binding.lockPatternView.removePostClearPatternRunnable();
            //updateStatus(Status.DEFAULT, null);
            binding.lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> pattern) {
            //Log.e(TAG, "--onPatternDetected--");
            if (mChosenPattern == null && pattern.size() >= 4) {
                mChosenPattern = new ArrayList<LockPatternView.Cell>(pattern);
                updateStatus(Status.CORRECT, pattern);
            } else if (mChosenPattern == null && pattern.size() < 4) {
                updateStatus(Status.LESSERROR, pattern);
            } else if (mChosenPattern != null) {
                if (mChosenPattern.equals(pattern)) {
                    updateStatus(Status.CONFIRMCORRECT, pattern);
                } else {
                    updateStatus(Status.CONFIRMERROR, pattern);
                }
            }
        }
    };

    /**
     * 更新状态
     *
     * @param status
     * @param pattern
     */
    private void updateStatus(Status status, List<LockPatternView.Cell> pattern) {
        binding.messageTv.setTextColor(getResources().getColor(status.colorId));
        binding.messageTv.setText(status.strId);
        switch (status) {
            case DEFAULT:
                binding.lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case CORRECT:
                updateLockPatternIndicator();
                binding.lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case LESSERROR:
                binding.lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case CONFIRMERROR:
                binding.lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                binding.lockPatternView.postClearPatternRunnable(DELAYTIME);
                break;
            case CONFIRMCORRECT:
                saveChosenPattern(pattern);
                binding.lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                setLockPatternSuccess();
                break;
        }
    }

    /**
     * 更新 Indicator
     */
    private void updateLockPatternIndicator() {
        if (mChosenPattern == null)
            return;
        binding.lockPatterIndicator.setIndicator(mChosenPattern);
    }

    /**
     * 重新设置手势
     */
    public void resetLockPattern(View view) {
        mChosenPattern = null;
        binding.lockPatterIndicator.setDefaultIndicator();
        updateStatus(Status.DEFAULT, null);
        binding.lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
    }

    /**
     * 成功设置了手势密码(跳到首页)
     */
    private void setLockPatternSuccess() {
//        Toast.makeText(this, "create gesture success", Toast.LENGTH_SHORT).show();
    }

    /**
     * 保存手势密码
     */
    private void saveChosenPattern(List<LockPatternView.Cell> cells) {
        String bytes = LockPatternUtil.patternToHash(cells);
        try {
            password = Utils.md5(bytes.toString() + Utils.getUser_name(CreateGestureActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setGesture(password);
    }

    private void setGesture(String password) {
        GesturePasswordSet body = new GesturePasswordSet();
        body.setTelephone(Utils.getUser_name(this));
        body.setGesture_password(password);
        ApiService.setGesture(this, new IHttpCallback<MarKAllResult>() {
            @Override
            public void onNext(HttpResult<MarKAllResult> t) {
                if (t.getResponse_data().getSuccess() == 1) {
                    CommonUtil.showToast(t.getResponse_text());
                    setSwitch();
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    public void setSwitch() {
        GestureGetData body = new GestureGetData(Utils.getUser_id(this), Utils.getUser_name(this), 2);
        ApiService.setSwitch(this, new IHttpCallback<SerciceFeeReslut>() {
            @Override
            public void onNext(HttpResult<SerciceFeeReslut> t) {
                CommonUtil.showToast(t.getResponse_text());
                finish();
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    private enum Status {
        //默认的状态，刚开始的时候（初始化状态）
        DEFAULT(R.string.create_gesture_default, R.color.grey_a5a5a5),
        //第一次记录成功
        CORRECT(R.string.create_gesture_correct, R.color.grey_a5a5a5),
        //连接的点数小于4（二次确认的时候就不再提示连接的点数小于4，而是提示确认错误）
        LESSERROR(R.string.create_gesture_less_error, R.color.red_f4333c),
        //二次确认错误
        CONFIRMERROR(R.string.create_gesture_confirm_error, R.color.red_f4333c),
        //二次确认正确
        CONFIRMCORRECT(R.string.create_gesture_confirm_correct, R.color.grey_a5a5a5);

        private Status(int strId, int colorId) {
            this.strId = strId;
            this.colorId = colorId;
        }

        private int strId;
        private int colorId;
    }
}
