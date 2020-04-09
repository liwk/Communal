package com.egee.communallib.utils;

import android.content.Context;

import com.egee.communallib.base.BaseApplication;


/**
 * @Date: 2019/10/11 13:38
 * @Author: YGZ
 * @Description: 获取applicationContext工具类
 * @Version:
 */
public class ContextUtils {

    private ContextUtils() {
        throw new UnsupportedOperationException("ContextUtil can't be instantiated!");
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        return BaseApplication.getAppContext();
    }

}