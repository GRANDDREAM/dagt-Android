package com.grandream.dagt.fragment.home.msg;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.home.msg.MarKAllResult;
import com.grandream.dagt.bean.home.msg.MsgList;
import com.grandream.dagt.databinding.MsgCenterLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.msg.MsgRecyclerViewAdapter;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.home.msg.MarkMsgAllRead;
import com.grandream.dagt.utils.Utils;

/**
 * 消息中心
 * Created by chenjing on 2018/4/16.
 */

public class MsgCenterFragment extends BaseFragment {
    private MsgCenterLayoutBinding binding;
    private MsgRecyclerViewAdapter adapter;
    MsgList msgList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.msg_center_layout, container, false);
        initView();
        getMsgData();
        return binding.getRoot();
    }

    private void initView() {
        binding.navigation.setRightText(getResources().getString(R.string.mark_all));
        binding.navigation.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MarkALL();
            }
        });
        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.msgRcv.setLayoutManager(manager);
    }

    /**
     * 标记全部已读
     */
    private void MarkALL() {
        MarkMsgAllRead markMsgAllRead = new MarkMsgAllRead(Utils.getUser_id(getActivity()), msgList.getData());
        ApiService.MarkAllRead(getActivity(), new IHttpCallback<MarKAllResult>() {
            @Override
            public void onNext(HttpResult<MarKAllResult> t) {
                getMsgData();
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(markMsgAllRead));
    }

    /**
     * 获取消息数据
     */
    public void getMsgData() {
        parameters.clear();
        parameters.put("user_id", Utils.getUser_id(getActivity()));
        ApiService.getMsgList(getActivity(), new IHttpCallback<MsgList>() {
            @Override
            public void onNext(final HttpResult<MsgList> t) {
                msgList = t.getResponse_data();
                adapter = new MsgRecyclerViewAdapter(getActivity(), t.getResponse_data().getData());
                binding.msgRcv.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ReadDetail(position, msgList.getData().get(position).getId(), msgList.getData().get(position).getRead(), msgList.getData().get(position).getMsg_type_tid());
                    }
                });
            }

            @Override
            public void onError(int code, String message) {

            }
        }, parameters);
    }


    /**
     * 点开阅读详情
     *
     * @param position
     * @param id
     * @param read
     * @param type
     */
    private void ReadDetail(int position, String id, String read, String type) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("read", read);
        bundle.putString("type", type);
        GenericFragmentActivity.start(getActivity(), MsgDetailFragment.class, bundle);
    }
}
