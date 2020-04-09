package com.egee.communallib.base;

import android.app.Application;
import android.content.Context;

/**
 * @Date: 2020/4/6 11:49
 * @Author: LWK
 * @Description:
 * @Version:
 */
public class BaseApplication extends Application {
    /**
     * 系统上下文
     */
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();

    }

    /**
     * 获取系统上下文：用于ToastUtil类
     */
    public static Context getAppContext() {
        return mAppContext;
    }
}
