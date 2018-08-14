package com.grandream.dagt.fragment.adapter.credit;

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
import com.grandream.dagt.bean.credit.CreditListData;
import com.grandream.dagt.bean.credit.OperatinData;
import com.grandream.dagt.databinding.CreditSuccedItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.credit.manage.AdditionalPledgeFragment;

import java.util.List;

/**
 * Created by chenjing on 2018/5/7.
 */

public class SuccedCreditAdapter extends RecyclerView.Adapter<SuccedCreditAdapter.BindingHolder> {

    Context context;
    List<CreditListData.DataBean.FormatOrderInfoBean> formatOrderInfoBeanList;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    String image_path, path;
    String status;

    public SuccedCreditAdapter(Context context, List<CreditListData.DataBean.FormatOrderInfoBean> formatOrderInfoBeanList, String status) {
        this.context = context;
        this.formatOrderInfoBeanList = formatOrderInfoBeanList;
        this.status = status;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public SuccedCreditAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CreditSuccedItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.credit_succed_item, parent, false);
        SuccedCreditAdapter.BindingHolder holder = new SuccedCreditAdapter.BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final SuccedCreditAdapter.BindingHolder holder, int position) {
        final CreditListData.DataBean.FormatOrderInfoBean bean = formatOrderInfoBeanList.get(position);
        holder.binding.setVariable(BR.bean, bean);
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
        if (status.equals("1")) {
            holder.binding.amountTitle.setText("授信额度");
            holder.binding.loanAmount.setText(bean.getReal_loan_amount());
        } else {
            holder.binding.loanAmount.setText(bean.getNeed_amount_limit());
        }
        if (bean.getShow_warning() == 1) {
            holder.binding.showWarning.setVisibility(View.VISIBLE);
        } else {
            holder.binding.showWarning.setVisibility(View.GONE);
        }
        holder.binding.executePendingBindings();
        holder.binding.showWarningSubmitText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("ORDER_SEQUENCE_CODE", bean.getOrder_sequence_code());
                GenericFragmentActivity.start((Activity) context, AdditionalPledgeFragment.class, bundle);
            }
        });

    }


    @Override
    public int getItemCount() {
        return formatOrderInfoBeanList.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        CreditSuccedItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public CreditSuccedItemBinding getBinding() {
            return binding;
        }

        public void setBinding(CreditSuccedItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}