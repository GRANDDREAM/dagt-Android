package com.grandream.dagt.ui.adbanner.slidertypes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.home.ADSBean;
import com.grandream.dagt.common.ViewConstant;
import com.grandream.dagt.fragment.h5JsFargment.CommWebFragment;
import com.grandream.dagt.fragment.my.gesture.GestureLoginActivity;
import com.grandream.dagt.utils.CommonUtil;

import java.util.List;


/**
 * This is a slider with a description TextView.
 */
public class TextSliderView extends BaseSliderView {
    List<String> img;
    List<String> imgurl;
    //    List<String> title;
    Context context;
    List<ADSBean.DataBean> data;

    public TextSliderView(Context context, List<String> img, List<String> imgurl) {
        super(context);
        this.context = context;
        this.img = img;
        this.imgurl = imgurl;
//        this.title = title;
    }

    public TextSliderView(Context context, List<ADSBean.DataBean> data) {
        super(context);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.render_type_text, null);
        ImageView target = (ImageView) v.findViewById(R.id.daimajia_slider_image);
        TextView description = (TextView) v.findViewById(R.id.description);
        description.setText(getDescription());
        Glide.with(context).load(data.get(position % data.size()).getContent()
                .toString()).priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(target);
//        loadImage(target);


        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtil.isFastDoubleClick()) {
                    return;
                }
                toH5_ad(data.get(position % data.size()).getUrl().toString(), "");
            }
        });
        bindClickEvent(v);
        return v;
    }

    private void toH5_ad(String url, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(ViewConstant.PAGE_URL, url);
        bundle.putBoolean(ViewConstant.IS_SHOW_HEAD, true);
        GenericFragmentActivity.start((Activity) context, CommWebFragment.class, bundle);
    }
}
