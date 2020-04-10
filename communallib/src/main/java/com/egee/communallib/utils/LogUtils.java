package com.egee.communallib.utils;

import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @Date: 2019/10/10 10:20
 * @Author: YGZ
 * @Description: 基于Logger封装的日志打印工具类
 * @Version:
 */
public class LogUtils {


    private LogUtils() {
        throw new UnsupportedOperationException("LogUtils cannot be instantiated!");
    }

    public static void init(String tag, boolean isLoggable) {
        Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder().tag(tag).build()) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return isLoggable;
            }
        });
    }

    /**
     * VERBOSE级别，可添加占位符
     *
     * @param msg
     */
    public static void v(String msg) {
        Logger.v(msg);
    }

    /**
     * DEBUG级别，打印对象
     *
     * @param obj
     */
    public static void d(Object obj) {
        Logger.d(obj);
    }

    /**
     * DEBUG级别，可添加占位符
     *
     * @param msg
     */
    public static void d(String msg) {
        Logger.d(msg);
    }

    /**
     * INFO级别，可添加占位符
     *
     * @param msg
     */
    public static void i(String msg) {
        Logger.i(msg);
    }

    /**
     * WARN级别，可添加占位符
     *
     * @param msg
     */
    public static void w(String msg) {
        Logger.w(msg);
    }


    /**
     * ERROR级别，可添加占位符
     *
     * @param msg
     */
    public static void e(String msg) {
        Logger.e(msg);
    }

    /**
     * ERROR级别，可添加占位符
     *
     * @param throwable
     * @param msg
     */
    public static void e(Throwable throwable, String msg) {
        Logger.e(throwable, msg);
    }

    /**
     * ASSERT级别，可添加占位符
     *
     * @param msg
     */
    public static void a(String msg) {
        Logger.wtf(msg);
    }

    /**
     * 打印xml
     *
     * @param xml
     */
    public static void xml(String xml) {
        Logger.xml(xml);
    }

    /**
     * 打印json
     *
     * @param json
     */
    public static void json(String json) {
        Logger.json(json);
    }

}