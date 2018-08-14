package com.grandream.dagt.fragment.adapter.share;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grandream.dagt.BR;
import com.grandream.dagt.R;
import com.grandream.dagt.bean.home.share.QueryShare;
import com.grandream.dagt.databinding.RecommendListItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by chenjing on 2018/7/4.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.BindingHolder> {

    Context context;
    List<QueryShare.DataBean> dataBeen;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    String image_path, path;

    public RecommendAdapter(Context context, List<QueryShare.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecommendAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecommendListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.recommend_list_item, parent, false);
        RecommendAdapter.BindingHolder holder = new RecommendAdapter.BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final RecommendAdapter.BindingHolder holder, int position) {
        final QueryShare.DataBean bean = dataBeen.get(position);
        if (!TextUtils.isEmpty(bean.getAvatar())) {
            Glide.with(context).load(bean.getAvatar())
                    .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.binding.userImg);
        }
        holder.binding.setVariable(BR.bean, bean);
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
        }


    }

    @Override
    public int getItemCount() {
        return dataBeen.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        RecommendListItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public RecommendListItemBinding getBinding() {
            return binding;
        }

        public void setBinding(RecommendListItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}