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
import com.grandream.dagt.bean.credit.CreditInfo;
import com.grandream.dagt.databinding.OrgRecyItemBinding;
import com.grandream.dagt.databinding.OrgRecyItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjing on 2018/4/23.
 */

public class OrgInfoRcleryAdapter extends RecyclerView.Adapter<OrgInfoRcleryAdapter.BindingHolder> {

    Context context;
    List<CreditInfo.DataBean.LoanOrganizationBean> organizationBeen;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    private List<Boolean> isClicks;
    private List<Boolean> isClicks2;

    public OrgInfoRcleryAdapter(Context context, List<CreditInfo.DataBean.LoanOrganizationBean> organizationBeen) {
        this.context = context;
        this.organizationBeen = organizationBeen;
        inflater = LayoutInflater.from(context);
        isClicks = new ArrayList<>();
        isClicks2 = new ArrayList<>();
        for (int i = 0; i < organizationBeen.size(); i++) {
            isClicks.add(false);
            if (i==0){
                isClicks2.add(true);
            }else {
                isClicks2.add(false);
            }
        }
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrgRecyItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.org_recy_item, parent, false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final BindingHolder holder, int position) {
        CreditInfo.DataBean.LoanOrganizationBean bean = organizationBeen.get(position);
        holder.binding.setVariable(BR.orgbean, bean);
        holder.binding.orgCb.setChecked(isClicks2.get(position));
        Glide.with(context).load(bean.getOrg_icon())
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.binding.img);
        holder.binding.executePendingBindings();
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
                    isClicks2=isClicks;
                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(v, position);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return organizationBeen.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        OrgRecyItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public OrgRecyItemBinding getBinding() {
            return binding;
        }

        public void setBinding(OrgRecyItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}