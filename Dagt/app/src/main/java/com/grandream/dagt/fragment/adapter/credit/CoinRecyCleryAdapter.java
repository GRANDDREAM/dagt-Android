package com.grandream.dagt.fragment.adapter.credit;

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
import com.grandream.dagt.bean.credit.CoinData;
import com.grandream.dagt.databinding.CreditCoinRecyItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.http.RxRetrogitLog;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjing on 2018/4/23.
 */

public class CoinRecyCleryAdapter extends RecyclerView.Adapter<CoinRecyCleryAdapter.BindingHolder> {

    Context context;
    List<CoinData.CoinDataBean> coinDataBeen;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    private List<Boolean> isClicks;
    private List<Boolean> isClicks2;

    public CoinRecyCleryAdapter(Context context, List<CoinData.CoinDataBean> coinDataBeen) {
        this.context = context;
        this.coinDataBeen = coinDataBeen;
        inflater = LayoutInflater.from(context);
        isClicks = new ArrayList<>();
        isClicks2 = new ArrayList<>();
        for (int i = 0; i < coinDataBeen.size(); i++) {
            isClicks.add(false);
            if (i == 0) {
                isClicks2.add(true);
            } else {
                isClicks2.add(false);
            }
        }


    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CreditCoinRecyItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.credit_coin_recy_item, parent, false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final BindingHolder holder, int position) {
        CoinData.CoinDataBean bean = coinDataBeen.get(position);
        holder.binding.setVariable(BR.bean, bean);
        holder.binding.coinCb.setChecked(isClicks2.get(position));
        Glide.with(context).load(bean.getDigit_coin_icon())
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
                        for (int i = 0; i < isClicks.size(); i++) {
                            isClicks.set(i, false);
                        }
                        isClicks.set(position, true);
                        isClicks2 = isClicks;
                        notifyDataSetChanged();
                        mOnItemClickListener.onItemClick(v, position);
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return coinDataBeen.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        CreditCoinRecyItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public CreditCoinRecyItemBinding getBinding() {
            return binding;
        }

        public void setBinding(CreditCoinRecyItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}