package com.grandream.dagt.ui.navigationLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grandream.dagt.R;

/**
 * Created by chenjing on 2018/3/23.
 */

public class NavigationLayout extends RelativeLayout implements
        View.OnClickListener {
    private TextView mTitle;
    private String mTitleStr;
    private ImageView mRightImg;
    private Drawable mRightImgDb;
    private TextView mRightText;
    private String mRightStr;
    private View mRootView;
    private ImageView mLeftView;
    private TextView mLeftText;
    private String mLeftStr;

    public NavigationLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public NavigationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NavigationLayout(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        mRootView = LayoutInflater.from(context).inflate(
                R.layout.navigation_layout, this, true);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs,
                    R.styleable.NavigationLayout);
            if (ta != null) {
                mTitleStr = ta.getString(R.styleable.NavigationLayout_na_title);
                mRightImgDb = ta
                        .getDrawable(R.styleable.NavigationLayout_rightImg);
                mRightStr = ta.getString(R.styleable.NavigationLayout_rightTxt);
                mLeftStr = ta.getString(R.styleable.NavigationLayout_leftTxt);
                ta.recycle();
            }

        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode())
            return;
        mLeftView = (ImageView) mRootView.findViewById(R.id.left);
        mLeftView.setOnClickListener(this);

        View rightBtn = mRootView.findViewById(R.id.right_btn);
        rightBtn.setOnClickListener(this);

        View leftBtn = mRootView.findViewById(R.id.left_btn);
        leftBtn.setOnClickListener(this);
        mTitle = (TextView) mRootView.findViewById(R.id.name);
        mTitle.setText(mTitleStr);
        mRightText = (TextView) mRootView.findViewById(R.id.right_txt);
        mRightText.setText(mRightStr);
        mRightImg = (ImageView) mRootView.findViewById(R.id.right);
        mRightImg.setImageDrawable(mRightImgDb);

        mLeftText = (TextView) mRootView.findViewById(R.id.left_txt);
        mLeftText.setText(mLeftStr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left:
                if (mLeftClick != null) {
                    mLeftClick.onClick(v);
                }
                break;
            case R.id.right_btn:
                if (mRightClick != null) {
                    mRightClick.onClick(v);
                }
                break;
            case R.id.left_btn:
                if (mLeftClick != null) {
                    mLeftClick.onClick(v);
                }
        }

    }

    public void setLeftImg(int id) {
        if (mLeftView != null) {
            mLeftText.setVisibility(View.GONE);
            mLeftView.setVisibility(View.VISIBLE);
            mLeftView.setImageResource(id);
        }
    }

    public void setTitle(String title) {
        if (mTitle != null) {
            mTitle.setText(title);
        }
    }

    public void setTitleColor(int color) {
        if (mTitle != null) {
            mTitle.setTextColor(this.getResources().getColor(color));
        }
    }

    public void setTitleAndScroll(String title) {
        if (mTitle != null) {
            mTitle.setText(title);
            mTitle.setSingleLine(true);
            mTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            mTitle.setMarqueeRepeatLimit(-1);
            mTitle.setHorizontallyScrolling(true);
            mTitle.setFocusable(true);
            mTitle.setFocusableInTouchMode(true);
        }
    }

    private OnClickListener mLeftClick;
    private OnClickListener mRightClick;

    public void setLeftClick(OnClickListener leftClick) {
        mLeftClick = leftClick;
    }

    public void setRightClick(OnClickListener rightClick) {
        mRightClick = rightClick;
    }

    public void hideLeft(boolean flag) {
        if (flag) {
            mLeftView.setVisibility(View.GONE);
        } else {
            mLeftView.setVisibility(View.VISIBLE);
        }
    }

    public void hideRight(boolean flag) {
        if (flag) {
            mRightText.setVisibility(View.GONE);
        } else {
            mRightText.setVisibility(View.VISIBLE);
        }
    }

    public void showRightText(boolean flag) {
        if (flag) {
            mRightText.setVisibility(View.VISIBLE);
        } else {
            mRightText.setVisibility(View.GONE);
        }
    }

    public void showLeftText(boolean flag) {
        if (flag) {
            mLeftText.setVisibility(View.VISIBLE);
            mLeftView.setVisibility(View.GONE);
        } else {
            mLeftText.setVisibility(View.GONE);
            mLeftView.setVisibility(View.VISIBLE);

        }
    }

    public void hideRightImgBackground() {
        mRightImg.setBackgroundResource(0);
    }


    public void setLeftText(String leftStr) {
        mLeftView.setVisibility(View.GONE);
        mLeftText.setVisibility(View.VISIBLE);
        mLeftText.setText(leftStr);

    }

    public void setmTitleTextColor(int color){
        mTitle.setTextColor(color);
    }

    public void setRightText(String rightStr) {
        mRightText.setText(rightStr);

    }

    public void setRightBackground(int rightStr) {
        mRightImg.setBackgroundResource(rightStr);

    }

    public void setrRightImg(int resId) {
        if (mRightImg != null) {

        }
        mRightImg.setImageResource(resId);
    }

    public void hideRightImage(boolean isshow) {
        if (mRightImg != null) {

        }
        if (isshow)
            mRightImg.setVisibility(GONE);
        else
            mRightImg.setVisibility(VISIBLE);
    }

    private NavigationClickListener clickListener;

    public void setNavigationClickLister(NavigationClickListener listener) {
        clickListener = listener;
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction,
                                  Rect previouslyFocusedRect) {
        // TODO Auto-generated method stub
        if (gainFocus) {
            super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        // TODO Auto-generated method stub
        if (hasWindowFocus) {
            super.onWindowFocusChanged(hasWindowFocus);
        }
    }

    @Override
    public boolean isFocused() {
        // TODO Auto-generated method stub
        return true;
    }

    public interface NavigationClickListener {
        public void leftClick();

        public void rightClick();
    }

}