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
import com.grandream.dagt.bean.credit.AgainReslut;
import com.grandream.dagt.bean.credit.OperatinData;
import com.grandream.dagt.databinding.NoDealItemBinding;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.credit.TransactionSerialNumBean;
import com.grandream.dagt.utils.Utils;

import java.util.List;

/**
 * Created by chenjing on 2018/4/28.
 */

public class NoDealAdapter extends RecyclerView.Adapter<NoDealAdapter.BindingHolder> {

    Context context;
    List<OperatinData.DataBean> chain_trade_code;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    String image_path, path;

    public NoDealAdapter(Context context, List<OperatinData.DataBean> chain_trade_code) {
        this.context = context;
        this.chain_trade_code = chain_trade_code;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NoDealAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NoDealItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.no_deal_item, parent, false);
        NoDealAdapter.BindingHolder holder = new NoDealAdapter.BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onBindViewHolder(final NoDealAdapter.BindingHolder holder, final int position) {
        OperatinData.DataBean bean = chain_trade_code.get(position);
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
        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delTradeCode(position);
            }
        });
    }

    private void delTradeCode(final int position) {
        TransactionSerialNumBean body = new TransactionSerialNumBean();
        body.setChain_trade_code(chain_trade_code.get(position).getChain_trade_code());
        body.setUser_id(Utils.getUser_id(context));
        ApiService.delTradeCode(context, new IHttpCallback<AgainReslut>() {
            @Override
            public void onNext(HttpResult<AgainReslut> t) {
                if (t.getResponse_data().getDel() == 1) {
                    //成功
                    chain_trade_code.remove(position);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(body));
    }

    @Override
    public int getItemCount() {
        return chain_trade_code.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        NoDealItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public NoDealItemBinding getBinding() {
            return binding;
        }

        public void setBinding(NoDealItemBinding binding) {
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}