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

import com.grandream.dagt.BR;
import com.grandream.dagt.R;
import com.grandream.dagt.bean.credit.CreditDetail;
import com.grandream.dagt.bean.my.LenInstiutionBean;
import com.grandream.dagt.databinding.CollateralLitemBinding;
import com.grandream.dagt.databinding.CollateralLitemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by chenjing on 2018/4/28.
 */

public class CollateralDataAdapter extends RecyclerView.Adapter<CollateralDataAdapter.BindingHolder> {

    Context context;
    List<CreditDetail.DataBean.ShxTradeInfoBean> shxTradeInfoBeen;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    String image_path, path;

    public CollateralDataAdapter(Context context, List<CreditDetail.DataBean.ShxTradeInfoBean> shxTradeInfoBeen) {
        this.context = context;
        this.shxTradeInfoBeen = shxTradeInfoBeen;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CollateralDataAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CollateralLitemBinding binding = DataBindingUtil.inflate(inflater, R.layout.collateral_litem, parent, false);
        CollateralDataAdapter.BindingHolder holder = new CollateralDataAdapter.BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final CollateralDataAdapter.BindingHolder holder, int position) {
        CreditDetail.DataBean.ShxTradeInfoBean bean = shxTradeInfoBeen.get(position);
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
        return shxTradeInfoBeen.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        CollateralLitemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public CollateralLitemBinding getBinding() {
            return binding;
        }

        public void setBinding(CollateralLitemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}