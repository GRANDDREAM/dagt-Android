package com.grandream.dagt.fragment.h5JsFargment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.grandream.dagt.R;
import com.grandream.dagt.common.ViewConstant;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.h5JsFargment.h5jump.AndroidForJsFragment;
import com.grandream.dagt.ui.ProgressWebView.ProgressWebView;
import com.grandream.dagt.ui.navigationLayout.NavigationLayout;

/**
 * Created by chenjing on 2018/7/5.
 */

public class CommWebFragment extends BaseFragment {
    private static final String APP_CACAHE_DIRNAME = "/webcache";
    private static final String TAG = "CommonFragment-------->";

    private ProgressWebView mCommonWebView;

    private String pageUrl;
    private boolean isShowHead = false;

    public static final String KEY_COMMON_TYPE = "KEY_COMMON_TYPE";

    private LinearLayout loadLayout;
    private boolean isShowGestUre = false;
    String path = "";

    public static CommWebFragment newInstances(String pageUrl, boolean isShowHead) {
        CommWebFragment detailFrag = new CommWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ViewConstant.PAGE_URL, pageUrl);
        bundle.putBoolean(ViewConstant.IS_SHOW_HEAD, isShowHead);
        detailFrag.setArguments(bundle);
        return detailFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        path = ViewConstant.WEB_URL;
        Bundle bundle = getArguments();
        if (bundle != null) {
            pageUrl = (String) bundle.getString(ViewConstant.PAGE_URL);
            isShowHead = (Boolean) bundle.getBoolean(ViewConstant.IS_SHOW_HEAD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = null;
        rootView = inflater.inflate(R.layout.common_fragment_layout, null);
        NavigationLayout navigationLayout = (NavigationLayout) rootView
                .findViewById(R.id.navigation);
        navigationLayout.setLeftClick(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                sendKeyBackEvent();
            }
        });
        if (!isShowHead)
            navigationLayout.setVisibility(View.VISIBLE);

        mCommonWebView = (ProgressWebView) rootView
                .findViewById(R.id.common_webview);
//        loadLayout = (LinearLayout) rootView.findViewById(R.id.async_begin);

        initWebView();
        mCommonWebView.loadUrl(pageUrl);
        return rootView;
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        mCommonWebView.addJavascriptInterface(new AndroidForJsFragment(
                CommWebFragment.this), "JavaScriptInterface");
        // mCommonWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // // 设置 缓存模式
        // 开启 DOM storage API 功能
        mCommonWebView.getSettings().setDomStorageEnabled(true);
        // 开启 database storage API 功能
        mCommonWebView.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getActivity().getFilesDir().getAbsolutePath()
                + APP_CACAHE_DIRNAME;
        // 设置数据库缓存路径
        mCommonWebView.getSettings().setDatabasePath(cacheDirPath);
        // 设置 Application Caches 缓存目录
        mCommonWebView.getSettings().setAppCachePath(cacheDirPath);
        // 开启 Application Caches 功能
        mCommonWebView.getSettings().setAppCacheEnabled(true);
        mCommonWebView.setWebViewClient(getWebClient());
        mCommonWebView.setWebChromeClient(new WebChromeClient());
        mCommonWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress==100){

            }
        }

    }

    private WebViewClient getWebClient() {
        return new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view,
                                                    final String url) {
                Uri uri = Uri.parse(Uri.decode(url));
                // 这边做一些匹配工作
                boolean isHandle = false;
                if (uri != null && uri.getHost() != null
                        ) {
                    isHandle = true;
                } else {
                    isHandle = shouldOverrideUrlLoadingForSubClass(url);
                }

                if (isHandle) {
                    return true;
                } else {
                    return super.shouldOverrideUrlLoading(view, url);
                }
            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                handler.proceed();
                super.onReceivedSslError(view, handler, error);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // loadLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, final int errorCode,
                                        final String description, final String failingUrl) {

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 将图片延迟加载
                if (!isShowGestUre) {
                    isShowGestUre = true;
                    mCommonWebView.loadUrl("javascript:startUpGest()");
                }

                mCommonWebView.getSettings().setBlockNetworkImage(false);
                if (!mCommonWebView.getSettings().getLoadsImagesAutomatically()) {
                    mCommonWebView.getSettings().setLoadsImagesAutomatically(
                            true);
                }
            }
        };
    }

    public boolean shouldOverrideUrlLoadingForSubClass(String url) {
        // TODO:
        return false;
    }
}