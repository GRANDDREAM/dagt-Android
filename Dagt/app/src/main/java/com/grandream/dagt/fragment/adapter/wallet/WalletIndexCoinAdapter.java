package com.grandream.dagt.fragment.adapter.wallet;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grandream.dagt.BR;
import com.grandream.dagt.R;
import com.grandream.dagt.bean.credit.OperatinData;
import com.grandream.dagt.bean.wallet.WalletIndext;
import com.grandream.dagt.databinding.WallCoinItemBinding;
import com.grandream.dagt.databinding.WallCoinItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by chenjing on 2018/4/28.
 */

public class WalletIndexCoinAdapter extends RecyclerView.Adapter<WalletIndexCoinAdapter.BindingHolder> {

    Context context;
    List<WalletIndext.OwnerCoinInfoBean> ownerCoinInfoBeen;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    String image_path, path;

    public WalletIndexCoinAdapter(Context context, List<WalletIndext.OwnerCoinInfoBean> ownerCoinInfoBeen) {
        this.context = context;
        this.ownerCoinInfoBeen = ownerCoinInfoBeen;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public WalletIndexCoinAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WallCoinItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.wall_coin_item, parent, false);
        WalletIndexCoinAdapter.BindingHolder holder = new WalletIndexCoinAdapter.BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final WalletIndexCoinAdapter.BindingHolder holder, int position) {
        WalletIndext.OwnerCoinInfoBean bean = ownerCoinInfoBeen.get(position);
        holder.binding.setVariable(BR.bean, bean);
        Glide.with(context).load(bean.getCoin_icon())
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.binding.coinImg);
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
        return ownerCoinInfoBeen.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        WallCoinItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public WallCoinItemBinding getBinding() {
            return binding;
        }

        public void setBinding(WallCoinItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}