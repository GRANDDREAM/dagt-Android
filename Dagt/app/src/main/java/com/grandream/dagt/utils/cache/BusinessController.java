package com.grandream.dagt.utils.cache;

import android.app.Application;

/**
 * Created by chenjing on 2018/3/9.
 */

public class BusinessController {
    private static Application mainApplication;

    public static Application getApplication() {
        return mainApplication;
    }

    public static void setApplication(Application application) {
        BusinessController.mainApplication = application;
    }
}

