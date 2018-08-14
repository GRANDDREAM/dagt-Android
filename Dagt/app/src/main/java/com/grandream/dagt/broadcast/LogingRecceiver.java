package com.grandream.dagt.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.grandream.dagt.common.AppContext;


/**
 * Created by chenjing on 2018/6/28.
 */

public class LogingRecceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("INFO=======", context.getPackageName());
    }
}
