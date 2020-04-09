package com.egee.communallib.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

import java.util.Locale;

/**
 * @Date: 2019/10/11 10:34
 * @Author: YGZ
 * @Description: 系统相关工具类
 * @Version:
 */
public class DeviceUtils {
    private static final String KEY_IMEI = "imei";
    private static final String TAG = "DeviceUtils";


    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统的API级别，数字表示
     *
     * @return
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 判断当前手机系统的API级别是否23及以上
     *
     * @return
     */
    public static boolean isSDKVersion23AndAbove() {
        return getSDKVersion() >= Build.VERSION_CODES.M;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机IMEI（需要“android.permission.READ_PHONE_STATE”权限）
     *
     * @return 手机IMEI
     */
    /*public static String getIMEI(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            return tm.getDeviceId();
        }
        return null;
    }*/


    /**
     * 获取手机IMEI，需要
     * 1.android.permission.READ_PHONE_STATE 权限；
     * 2.系统版本api28及以下；
     *
     * @return 手机IMEI
     */
    public static String getIMEI(Context context) {
        String imei = SpUtils.getString(context, KEY_IMEI);
        if (StringUtils.isEmpty(imei)) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                // 检测权限
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                if (telephonyManager != null) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        // 8.0以下
                        imei = telephonyManager.getDeviceId();
                        SpUtils.saveString(context, KEY_IMEI, imei);
                    } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                        // 9.0及以下
                        imei = telephonyManager.getImei();
                        SpUtils.saveString(context, KEY_IMEI, imei);
                    }
                }
            }
        }
        LogUtils.d("系统版本：api" + getSDKVersion() + "，imei：" + imei);
        return imei;
    }


}