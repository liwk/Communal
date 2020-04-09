package com.egee.communallib.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.StringRes;

/**
 * @Date: 2019/10/10 10:46
 * @Author: YGZ
 * @Description: 吐司工具类
 * @Version:
 */
public class ToastUtil {

    private static Toast sToast;

    /**
     * 显示短时吐司
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void showToast(Context context, String text) {
        if (StringUtils.notEmpty(text)) {
            if (sToast != null) {
                sToast.cancel();
                sToast = null;
            }
            /**
             * 1、setView()方式：避免miui带有应用名称前缀
             */
            /*View v = Toast.makeText(context.getApplicationContext(), null, Toast.LENGTH_SHORT)
                    .getView();
            sToast = new Toast(context.getApplicationContext());
            sToast.setView(v);
            sToast.setText(text);
            sToast.setDuration(Toast.LENGTH_SHORT);*/
            /**
             * 2、默认方式
             */
            sToast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.CENTER, 0, 0);
            sToast.show();
        }
    }

    /**
     * 显示短时吐司
     *
     * @param context 上下文
     * @param resId   文本资源id
     */
    public static void showToast(Context context, @StringRes int resId) {
        try {
            String text = context.getApplicationContext()
                    .getResources()
                    .getText(resId)
                    .toString();
            showToast(context, text);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2020/2/21
     *
     * @param toast
     * @param context
     */
    public static void showToast(final String toast, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }

}
