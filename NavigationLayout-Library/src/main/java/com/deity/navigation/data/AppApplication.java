package com.deity.navigation.data;

import android.app.Application;

/**
 * Application
 * Created by fengwenhua on 2017/6/1.
 */

public class AppApplication extends Application {

    public static AppApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
