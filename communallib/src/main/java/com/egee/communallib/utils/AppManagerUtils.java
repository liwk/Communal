package com.egee.communallib.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.Stack;

/**
 * @Date: 2019/10/19 18:51
 * @Author: YGZ
 * @Description: Activity堆栈式管理
 * @Version:
 */
public class AppManagerUtils {

    private static AppManagerUtils sAppManager;

    private static volatile Stack<Activity> sActivityStack;

    private AppManagerUtils() {
        if (sActivityStack == null) {
            sActivityStack = new Stack<Activity>();
        }
    }

    /**
     * 单一实例
     */
    public static AppManagerUtils getAppManager() {
        if (sAppManager == null) {
            synchronized (AppManagerUtils.class) {
                if (sAppManager == null) {
                    sAppManager = new AppManagerUtils();
                }
            }
        }
        return sAppManager;
    }

    /**
     * 添加Activity到堆栈
     *
     * @param activity Activity
     */
    public static void addActivity(Activity activity) {
        sActivityStack.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            sActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < sActivityStack.size(); i++) {
            if (null != sActivityStack.get(i)) {
                sActivityStack.get(i).finish();
            }
        }
        sActivityStack.clear();
    }


    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
     * @param cls activity类名
     */
    public void finishOthersActivity(Class<?> cls) {
        Iterator<Activity> iterator = sActivityStack.iterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            if (!cls.equals(next.getClass())) {
                iterator.remove();
                next.finish();
                next=null;
            }
        }
    }
    /**
     * 退出应用程序
     *
     * @param context Context
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Activity堆栈
     *
     * @return sActivityStack
     */
    public static Stack<Activity> getActivityStack() {
        return sActivityStack;
    }

    /**
     * 返回当前Activity栈中Activity的数量
     *
     * @return ActivityCount
     */
    public int getActivityCount() {
        return sActivityStack.size();
    }

    /**
     * 获取正在运行的Activity名称
     *
     * @param context Context
     * @return RunningActivityName
     */
    public static String getRunningActivityName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null)
            return null;
        return activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
    }

}