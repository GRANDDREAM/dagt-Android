package com.grandream.dagt.ui.ProgressWebView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by chenjing on 2018/7/5.
 */
@SuppressLint({"SetJavaScriptEnabled", "NewApi"})
public class ProgressWebView extends WebView {
    // private ProgressBar progressbar;
    public ProgressWebView(Context context) {
        super(context);
        init(context);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @SuppressWarnings("deprecation")
    private void init(Context context) {
        // progressbar = new ProgressBar(context, null,
        // android.R.attr.progressBarStyleHorizontal);
        // progressbar.setLayoutParams(new
        // LayoutParams(LayoutParams.FILL_PARENT,6, 0, 0));
        // progressbar.setIndeterminate(false);
        // progressbar.setIndeterminateDrawable(context.getResources().getDrawable(android.R.drawable.progress_indeterminate_horizontal));
        // progressbar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progressbar_mini));
        // addView(progressbar);
        // setBackgroundColor(Color.parseColor("#000000")); // ok 不会闪黑屏
        // resumeTimers();
        if (Build.VERSION.SDK_INT >= 19) {
            getSettings().setLoadsImagesAutomatically(true);
        } else {
            getSettings().setLoadsImagesAutomatically(false);
        }

        setVerticalScrollBarEnabled(false);// 取消Vertical ScrollBar显示
        setHorizontalScrollBarEnabled(false);// 取消Horizontal ScrollBar显示
        getSettings().setJavaScriptEnabled(true);// 设置可与JS交互
        getSettings().setUseWideViewPort(false);// WebView双击变大，再双击后变小，当手动放大后，双击可以恢复到原始大小
        getSettings().setBuiltInZoomControls(false);// 不可点击缩放
        getSettings().setDefaultTextEncodingName("utf-8");// 设置字符编码

        // 提高加载速度
        getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        // 关闭硬件加速
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        // 将图片下载阻塞
        getSettings().setBlockNetworkImage(true);

        // 清除缓存
        // CookieManager cm = CookieManager.getInstance();
        // cm.removeSessionCookie();
        // cm.removeAllCookie();

    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        // LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        // lp.x = l;
        // lp.y = t;
        // progressbar.setLayoutParams(lp);
        // super.onScrollChanged(l, t, oldl, oldt);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // invalidate();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}