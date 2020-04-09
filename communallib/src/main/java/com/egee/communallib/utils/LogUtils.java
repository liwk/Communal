package com.egee.communallib.utils;

import com.orhanobut.logger.Logger;

/**
 * @Date: 2019/10/10 10:20
 * @Author: YGZ
 * @Description: 基于Logger封装的日志打印工具类
 * @Version:
 */
public class LogUtils {

    /**
     * 是否打印日志
     */
    private static boolean sIsDebug;

    private LogUtils() {
        throw new UnsupportedOperationException("LogUtils cannot be instantiated!");
    }

    /**
     * VERBOSE级别，可添加占位符
     *
     * @param msg
     */
    public static void v(String msg) {
        if (sIsDebug)
            Logger.v(msg);
    }

    /**
     * DEBUG级别，打印对象
     *
     * @param obj
     */
    public static void d(Object obj) {
        if (sIsDebug)
            Logger.d(obj);
    }

    /**
     * DEBUG级别，可添加占位符
     *
     * @param msg
     */
    public static void d(String msg) {
        if (sIsDebug)
            Logger.d(msg);
    }

    /**
     * INFO级别，可添加占位符
     *
     * @param msg
     */
    public static void i(String msg) {
        if (sIsDebug)
            Logger.i(msg);
    }

    /**
     * WARN级别，可添加占位符
     *
     * @param msg
     */
    public static void w(String msg) {
        if (sIsDebug)
            Logger.w(msg);
    }


    /**
     * ERROR级别，可添加占位符
     *
     * @param msg
     */
    public static void e(String msg) {
        if (sIsDebug)
            Logger.e(msg);
    }

    /**
     * ERROR级别，可添加占位符
     *
     * @param throwable
     * @param msg
     */
    public static void e(Throwable throwable, String msg) {
        if (sIsDebug)
            Logger.e(throwable, msg);
    }

    /**
     * ASSERT级别，可添加占位符
     *
     * @param msg
     */
    public static void a(String msg) {
        if (sIsDebug)
            Logger.wtf(msg);
    }

    /**
     * 打印xml
     *
     * @param xml
     */
    public static void xml(String xml) {
        if (sIsDebug)
            Logger.xml(xml);
    }

    /**
     * 打印json
     *
     * @param json
     */
    public static void json(String json) {
        if (sIsDebug)
            Logger.json(json);
    }

}