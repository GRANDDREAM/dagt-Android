package com.grandream.dagt.fragment.adapter.my;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grandream.dagt.BR;
import com.grandream.dagt.R;
import com.grandream.dagt.bean.credit.OperatinData;
import com.grandream.dagt.bean.my.LenInstiutionBean;
import com.grandream.dagt.databinding.OrgItemBinding;
import com.grandream.dagt.databinding.OrgItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by chenjing on 2018/4/28.
 */

public class LendAdapter extends RecyclerView.Adapter<LendAdapter.BindingHolder> {

    Context context;
    List<LenInstiutionBean.DataBean> dataBeanList;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    String image_path, path;

    public LendAdapter(Context context, List<LenInstiutionBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public LendAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrgItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.org_item, parent, false);
        LendAdapter.BindingHolder holder = new LendAdapter.BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final LendAdapter.BindingHolder holder, int position) {
        final LenInstiutionBean.DataBean bean = dataBeanList.get(position);
        holder.binding.setVariable(BR.bean, bean);
        Glide.with(context).load(bean.getOrg_icon())
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.binding.img);
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
        holder.binding.appdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(bean.getOrg_app_download_url())) {
                    Uri uri = Uri.parse(bean.getOrg_app_download_url());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        OrgItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public OrgItemBinding getBinding() {
            return binding;
        }

        public void setBinding(OrgItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}