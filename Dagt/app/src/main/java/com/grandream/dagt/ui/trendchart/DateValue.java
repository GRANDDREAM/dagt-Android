package com.grandream.dagt.ui.trendchart;

/**
 * 数据源
 * Created by chenjing on 2018/3/20.
 */

public class DateValue {
    private double mValue;
    private String mDate;

    public DateValue(double value, String date) {
        mValue = value;
        mDate = date;
    }

    public double getValue() {
        return mValue;
    }

    public String getDate() {
        return mDate;
    }
}
