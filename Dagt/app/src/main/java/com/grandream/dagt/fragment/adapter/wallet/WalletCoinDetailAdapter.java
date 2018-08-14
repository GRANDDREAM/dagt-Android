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

import com.grandream.dagt.BR;
import com.grandream.dagt.R;
import com.grandream.dagt.bean.wallet.CoinDetail;
import com.grandream.dagt.bean.wallet.WalletIndext;
import com.grandream.dagt.databinding.WalletTradingRecordItemBinding;
import com.grandream.dagt.databinding.WalletTradingRecordItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by chenjing on 2018/4/28.
 */

public class WalletCoinDetailAdapter extends RecyclerView.Adapter<WalletCoinDetailAdapter.BindingHolder> {

    Context context;
    List<CoinDetail.TradeInfoBean> ownerCoinInfoBeen;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    String image_path, path;

    public WalletCoinDetailAdapter(Context context, List<CoinDetail.TradeInfoBean> ownerCoinInfoBeen) {
        this.context = context;
        this.ownerCoinInfoBeen = ownerCoinInfoBeen;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public WalletCoinDetailAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WalletTradingRecordItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.wallet_trading_record_item, parent, false);
        WalletCoinDetailAdapter.BindingHolder holder = new WalletCoinDetailAdapter.BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final WalletCoinDetailAdapter.BindingHolder holder, int position) {
        CoinDetail.TradeInfoBean bean = ownerCoinInfoBeen.get(position);
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
        return ownerCoinInfoBeen.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        WalletTradingRecordItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public WalletTradingRecordItemBinding getBinding() {
            return binding;
        }

        public void setBinding(WalletTradingRecordItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}