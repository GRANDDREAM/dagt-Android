package com.grandream.dagt.fragment.adapter.my;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.BR;
import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.my.TakeCoinAddressData;
import com.grandream.dagt.databinding.CoinAddressItemBinding;
import com.grandream.dagt.databinding.CoinAddressItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.my.EditCoinAddressFragment;

import java.util.List;

/**
 * Created by chenjing on 2018/4/28.
 */

public class MakeCoinAddressAdapter extends RecyclerView.Adapter<MakeCoinAddressAdapter.BindingHolder> {

    Context context;
    List<TakeCoinAddressData.DataBean> dataBeen;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    String image_path, path;

    public MakeCoinAddressAdapter(Context context, List<TakeCoinAddressData.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MakeCoinAddressAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CoinAddressItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.coin_address_item, parent, false);
        MakeCoinAddressAdapter.BindingHolder holder = new MakeCoinAddressAdapter.BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final MakeCoinAddressAdapter.BindingHolder holder, int position) {
        final TakeCoinAddressData.DataBean bean = dataBeen.get(position);
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
        holder.binding.editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATAINFO", bean);
                bundle.putBoolean("ADD", false);
                GenericFragmentActivity.start((Activity) context, EditCoinAddressFragment.class, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataBeen.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        CoinAddressItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public CoinAddressItemBinding getBinding() {
            return binding;
        }

        public void setBinding(CoinAddressItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}