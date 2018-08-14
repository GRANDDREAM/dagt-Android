package com.grandream.dagt.fragment.home.msg;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.bean.home.msg.MsgDeatilBean;
import com.grandream.dagt.databinding.MsgDetailLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.home.GetMsgDetail;
import com.grandream.dagt.utils.Utils;

/**
 * Created by chenjing on 2018/4/16.
 */

public class MsgDetailFragment extends BaseFragment {
    private String id, type, read;
    private MsgDetailLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        savedInstanceState = getArguments();
        id = savedInstanceState.getString("id");
        type = savedInstanceState.getString("type");
        read = savedInstanceState.getString("read");
        getMsgDetail(id, type, read);
        binding = DataBindingUtil.inflate(inflater, R.layout.msg_detail_layout, container, false);
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        return binding.getRoot();
    }

    private void getMsgDetail(String id, String type, String read) {
        GetMsgDetail getMsgDetail = new GetMsgDetail(Utils.getUser_id(getActivity()), id, type, read);
        ApiService.getMsgDetail(getActivity(), new IHttpCallback<MsgDeatilBean>() {
            @Override
            public void onNext(HttpResult<MsgDeatilBean> t) {
                binding.setMsgdetail(t.getResponse_data().getData());
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(getMsgDetail));
    }


}
