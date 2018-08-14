package com.grandream.dagt.fragment.adapter.msg;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.BR;
import com.grandream.dagt.R;
import com.grandream.dagt.bean.home.msg.MsgList.MsgListData;
import com.grandream.dagt.databinding.MsgItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.utils.Utils;

import java.util.List;

/**
 * Created by chenjing on 2018/4/16.
 */

public class MsgRecyclerViewAdapter extends RecyclerView.Adapter<MsgRecyclerViewAdapter.BindingHolder> {

    Context context;
    List<MsgListData> sys_bean;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    String image_path, path;

    public MsgRecyclerViewAdapter(Context context, List<MsgListData> sys_bean) {
        this.context = context;
        this.sys_bean = sys_bean;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MsgItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.msg_item, parent, false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final BindingHolder holder, int position) {
        MsgListData bean = sys_bean.get(position);
        holder.binding.setVariable(BR.msglistdata, bean);
        holder.binding.executePendingBindings();
        if (mOnItemClickListener != null) {
            /**
             * 这里加了判断，itemViewHolder.itemView.hasOnClickListeners()
             * 目的是减少对象的创建，如果已经为view设置了click监听事件,就不用重复设置了
             * 不然每次调用onBindViewHolder方法，都会创建一个监听事件对象，增加了内存的开销
             */
            if (!holder.itemView.hasOnClickListeners()) {
                Log.e("ListAdapter", "setOnClickListener");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getPosition();
                        mOnItemClickListener.onItemClick(v, position);
                    }
                });
            }
            int read = Utils.toInteger(bean.getRead());
            Drawable read_msg = context.getResources().getDrawable(R.mipmap.read_msg);
            Drawable new_msg = context.getResources().getDrawable(R.mipmap.new_msg);

            read_msg.setBounds(0, 0, read_msg.getMinimumWidth(), read_msg.getMinimumHeight());//非常重要，必须设置，否则图片不会显示
            new_msg.setBounds(0, 0, read_msg.getMinimumWidth(), read_msg.getMinimumHeight());//非常重要，必须设置，否则图片不会显示
            if (read == 0) {
                holder.binding.title.setCompoundDrawables(read_msg, null, null, null);
            } else if (read == 1) {
                holder.binding.title.setCompoundDrawables(new_msg, null, null, null);
            }
        }

    }

    @Override
    public int getItemCount() {
        return sys_bean.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        MsgItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public MsgItemBinding getBinding() {
            return binding;
        }

        public void setBinding(MsgItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}