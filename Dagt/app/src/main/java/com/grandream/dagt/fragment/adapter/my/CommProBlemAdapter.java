package com.grandream.dagt.fragment.adapter.my;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grandream.dagt.BR;
import com.grandream.dagt.R;
import com.grandream.dagt.bean.my.CommProBean;
import com.grandream.dagt.bean.my.LenInstiutionBean;
import com.grandream.dagt.databinding.ProblemItemBinding;
import com.grandream.dagt.databinding.ProblemItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by chenjing on 2018/4/28.
 */

public class CommProBlemAdapter extends RecyclerView.Adapter<CommProBlemAdapter.BindingHolder> {

    Context context;
    List<CommProBean.DataBean> dataBeanList;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    String image_path, path;

    public CommProBlemAdapter(Context context, List<CommProBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CommProBlemAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProblemItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.problem_item, parent, false);
        CommProBlemAdapter.BindingHolder holder = new CommProBlemAdapter.BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final CommProBlemAdapter.BindingHolder holder, int position) {
        CommProBean.DataBean bean = dataBeanList.get(position);
        holder.binding.setVariable(BR.bean, bean);
        holder.binding.executePendingBindings();
        if (bean.getShow()) {
            holder.binding.content.setVisibility(View.VISIBLE);
            holder.binding.name.setTextColor(context.getResources().getColor(R.color.title_bg));
            holder.binding.isShow.setBackground(context.getDrawable(R.mipmap.item_arrow_up));
        }
        holder.binding.titlt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (holder.binding.content.getVisibility() == View.VISIBLE) {
                    holder.binding.content.setVisibility(View.GONE);
                    holder.binding.isShow.setBackground(context.getDrawable(R.mipmap.item_arrow_down));
                } else {
                    holder.binding.content.setVisibility(View.VISIBLE);
                    holder.binding.isShow.setBackground(context.getDrawable(R.mipmap.item_arrow_up));
                }
            }
        });
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
        return dataBeanList.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        ProblemItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public ProblemItemBinding getBinding() {
            return binding;
        }

        public void setBinding(ProblemItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}