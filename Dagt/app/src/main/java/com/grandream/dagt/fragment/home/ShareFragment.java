package com.grandream.dagt.fragment.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.home.share.RewardCount;
import com.grandream.dagt.bean.home.share.ShareData;
import com.grandream.dagt.bean.home.share.ShareUrl;
import com.grandream.dagt.databinding.ShareLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;


/**
 * 分享页面
 * Created by chenjing on 2018/4/19.
 */

public class ShareFragment extends BaseFragment implements PlatformActionListener {
    private ShareLayoutBinding binding;
    Platform plat;
    ShareData shareData;

    /**
     * converts ShareSDK actions into string
     */
    //#endif
    public static String actionToString(int action) {
        switch (action) {
            case Platform.ACTION_AUTHORIZING: {
                return "ACTION_AUTHORIZING";
            }
            case Platform.ACTION_GETTING_FRIEND_LIST: {
                return "ACTION_GETTING_FRIEND_LIST";
            }
            case Platform.ACTION_FOLLOWING_USER: {
                return "ACTION_FOLLOWING_USER";
            }
            case Platform.ACTION_SENDING_DIRECT_MESSAGE: {
                return "ACTION_SENDING_DIRECT_MESSAGE";
            }
            case Platform.ACTION_TIMELINE: {
                return "ACTION_TIMELINE";
            }
            case Platform.ACTION_USER_INFOR: {
                return "ACTION_USER_INFOR";
            }
            case Platform.ACTION_SHARE: {
                return "ACTION_SHARE";
            }
            default: {
                return "UNKNOWN";
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.share_layout, container, false);
        binding.setClick(this);
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        getShareData();
        return binding.getRoot();
    }


    public void queryrecomend(View view) {
        GenericFragmentActivity.start(getActivity(), RecommendAwardQueryFragment.class, new Bundle());
    }

    public void WeChatShare(View view) {
        WechatShare("Wechat");
    }

    private void WechatShare(String type) {
        getShareURl(type, shareData);
    }


    public void WechatMomentShare(View view) {
        WechatShare("WechatMoments");
    }

    public void ShareQQ(View view) {
        getShareURl(SinaWeibo.NAME, shareData);
    }


    public void onComplete(Platform plat, int action,
                           HashMap<String, Object> res) {
        Message msg = new Message();
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = plat;
        UIHandler.sendMessage(msg, (Handler.Callback) this);
    }


    public void onCancel(Platform plat, int action) {
        Message msg = new Message();
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = plat;
        UIHandler.sendMessage(msg, (Handler.Callback) this);
    }

    public void onError(Platform plat, int action, Throwable t) {
        t.printStackTrace();

        Message msg = new Message();
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = t;
        UIHandler.sendMessage(msg, (Handler.Callback) this);
    }

    public boolean handleMessage(Message msg) {
        String text = ShareFragment.actionToString(msg.arg2);
        switch (msg.arg1) {
            case 1: {
                //#if def{lang} == cn
                // 成功
                //#elif def{lang} == en
                // success
                //#endif
                int resId = ResHelper.getStringRes(getContext(), "ssdk_oks_share_completed");
                if (resId > 0) {
                    text = getContext().getString(resId);
                }
            }
            break;
            case 2: {
                //#if def{lang} == cn
                // 失败
                //#elif def{lang} == en
                // failed
                //#endif
                if ("WechatClientNotExistException".equals(msg.obj.getClass().getSimpleName())) {
                    text = getContext().getString(R.string.ssdk_wechat_client_inavailable);
                } else if ("WechatTimelineNotSupportedException".equals(msg.obj.getClass().getSimpleName())) {
                    text = getContext().getString(R.string.ssdk_wechat_client_inavailable);
                } else {
                    text = "分享失败";
                }
            }
            break;
            case 3: {
                //#if def{lang} == cn
                // 取消
                //#elif def{lang} == en
                // canceled
                //#endif
                int resId = ResHelper.getStringRes(getContext(), "ssdk_oks_share_canceled");
                if (resId > 0) {
                    text = getContext().getString(resId);
                }
            }
            break;
        }

        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
        return false;
    }


    public void getShareData() {
        parameters.put("user_id", Utils.getUser_id(getActivity()));
        ApiService.ShareLink(getActivity(), new IHttpCallback<ShareUrl>() {
            @Override
            public void onNext(HttpResult<ShareUrl> t) {
                shareData = t.getResponse_data().getData();
                binding.setSharebean(shareData);
                binding.totalSumRecommend.setText("大家已通过推荐好友获得" + t.getResponse_data().getData().getTotal_sum_recommend() + "  个DAGT");
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }

    public void getShareURl(final String type, ShareData shareData) {
        if (type.equals(SinaWeibo.NAME)) {
            Platform.ShareParams sp = new Platform.ShareParams();
            sp.setTitle(shareData.getShare_title());
            sp.setText(shareData.getShare_content());
            sp.setAddress(shareData.getShare_url());
            sp.setShareType(Platform.SHARE_TEXT);
            sp.setImagePath(shareData.getReward_coin_info());
            plat = ShareSDK.getPlatform(SinaWeibo.NAME);
            plat.setPlatformActionListener(ShareFragment.this);
            plat.share(sp);
        } else {
            Platform.ShareParams sp = new Platform.ShareParams();
            sp.setTitle(shareData.getShare_title());
            sp.setText(shareData.getShare_content());
            sp.setAddress(shareData.getShare_url());
            sp.setShareType(Platform.SHARE_TEXT);
//        sp.setImagePath(MainActivity.testImage);
            plat = ShareSDK.getPlatform(type);
            plat.setPlatformActionListener(ShareFragment.this);
            plat.share(sp);
        }
    }
}
