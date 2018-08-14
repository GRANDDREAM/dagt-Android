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
import com.grandream.dagt.bean.credit.CoinData;
import com.grandream.dagt.bean.wallet.DrawDetailBean;
import com.grandream.dagt.databinding.ChooseAddressItemBinding;
import com.grandream.dagt.databinding.ChooseAddressItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjing on 2018/4/23.
 */

public class ChooseAddressAdapter extends RecyclerView.Adapter<ChooseAddressAdapter.BindingHolder> {

    Context context;
    List<DrawDetailBean.UserDrawCoinAddressBean> userDrawCoinAddressBeen;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    private List<Boolean> isClicks;

    public ChooseAddressAdapter(Context context, List<DrawDetailBean.UserDrawCoinAddressBean> userDrawCoinAddressBeen) {
        this.context = context;
        this.userDrawCoinAddressBeen = userDrawCoinAddressBeen;
        inflater = LayoutInflater.from(context);
        isClicks = new ArrayList<>();
        for (int i = 0; i < userDrawCoinAddressBeen.size(); i++) {
            isClicks.add(false);
        }
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChooseAddressItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.choose_address_item, parent, false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final BindingHolder holder, int position) {
        DrawDetailBean.UserDrawCoinAddressBean bean = userDrawCoinAddressBeen.get(position);
        holder.binding.setVariable(BR.bean, bean);
        holder.binding.coinCb.setChecked(isClicks.get(position));
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
                        notifyDataSetChanged();
                        mOnItemClickListener.onItemClick(v, position);
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return userDrawCoinAddressBeen.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        ChooseAddressItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public ChooseAddressItemBinding getBinding() {
            return binding;
        }

        public void setBinding(ChooseAddressItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}