package com.grandream.dagt.ui.trendchart;

import java.util.List;

/**
 * 数据颜色 宽度设置
 * Created by chenjing on 2018/3/20.
 */

public class Line {

    //线条数据源集合
    private List<DateValue> mLineData;
    //线条颜色
    private int mLineColor = 0xFF688FDB;
    //线条宽度
    private float mLineWidth = 3f;

    public Line(List<DateValue> lineData) {
        mLineData = lineData;
    }

    public List<DateValue> getLineData() {
        return mLineData;
    }

    public int getLineColor() {
        return mLineColor;
    }

    public float getLineWidth() {
        return mLineWidth;
    }
}