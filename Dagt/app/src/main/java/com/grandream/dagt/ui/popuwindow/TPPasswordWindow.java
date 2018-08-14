package com.grandream.dagt.ui.popuwindow;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.grandream.dagt.R;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjing on 2018/5/3.
 */

public class TPPasswordWindow extends PopupWindow {
    private Context context;
    private Activity activity;
    private View mMenuView;
    int width, height;
    RecyclerView grid_password;
    String[] str = {"1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", ""};
    PublicFundTPAdapter adapter;
    List<String> list = new ArrayList<String>();
    ImageView img_psd1, img_psd2, img_psd3, img_psd4, img_psd5, img_psd6;
//    TextView forget_pw;

    public TPPasswordWindow(Activity context, final OnItemClickListener itemsOnClick) {
        super(context);
        this.activity = context;
        this.context = context;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.trade_password_pop, null);
        grid_password = (RecyclerView) mMenuView.findViewById(R.id.key_password);

        img_psd1 = (ImageView) mMenuView.findViewById(R.id.img_psd1);
        img_psd2 = (ImageView) mMenuView.findViewById(R.id.img_psd2);
        img_psd3 = (ImageView) mMenuView.findViewById(R.id.img_psd3);
        img_psd4 = (ImageView) mMenuView.findViewById(R.id.img_psd4);
        img_psd5 = (ImageView) mMenuView.findViewById(R.id.img_psd5);
        img_psd6 = (ImageView) mMenuView.findViewById(R.id.img_psd6);

//        forget_pw = (TextView) mMenuView.findViewById(R.id.forget_pw);

        height = context.getWindowManager().getDefaultDisplay().getHeight();
        width = context.getWindowManager().getDefaultDisplay().getWidth();

        //CommonUtil.showToast("高度:"+height+"宽度:"+width);
        // 设置按钮监听
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(width);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.dkstyle);
        // 实例化一个ColorDrawable颜色为半透明
        // ColorDrawable dw = new ColorDrawable(22222222);
        // //设置SelectPicPopupWindow弹出窗体的背景
        // this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.trade_password).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (y != height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

        adapter = new PublicFundTPAdapter(context, str);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        grid_password.setLayoutManager(manager);
        grid_password.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                itemsOnClick.onItemClick(view,position);
            }
        });
    }


    class PublicFundTPAdapter extends RecyclerView.Adapter<PublicFundTPAdapter.Holder> {
        Context context;
        String[] str;
        LayoutInflater inflater;
        private OnItemClickListener mOnItemClickListener;

        public PublicFundTPAdapter(Context context, String[] str) {
            this.context = context;
            this.str = str;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public PublicFundTPAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.trade_password_item, parent, false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final PublicFundTPAdapter.Holder holder, int position) {
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
                            int position = holder.getAdapterPosition();
                            mOnItemClickListener.onItemClick(v, position);
                        }
                    });
                }
            }
            if (position==11){
                holder.key.setVisibility(View.GONE);
                holder.delete.setVisibility(View.VISIBLE);
            }else{
                holder.key.setVisibility(View.VISIBLE);
                holder.delete.setVisibility(View.GONE);
            }
            holder.key.setText(str[position]);
        }

        @Override
        public int getItemCount() {
            return str.length;
        }

        public class Holder extends RecyclerView.ViewHolder {
            TextView key;
            ImageView delete;

            public Holder(View itemView) {
                super(itemView);
                key= (TextView) itemView.findViewById(R.id.key);
                delete= (ImageView) itemView.findViewById(R.id.delete);
            }
        }

        public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
            this.mOnItemClickListener = mOnItemClickListener;
        }
    }
}