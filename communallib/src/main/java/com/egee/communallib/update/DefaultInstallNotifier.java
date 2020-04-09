package com.egee.communallib.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import org.lzh.framework.updatepluginlib.base.InstallNotifier;
import org.lzh.framework.updatepluginlib.util.SafeDialogHandle;

import java.lang.reflect.Field;

/**
 * @Date: 2019/10/19 17:42
 * @Author: YGZ
 * @Description: 默认使用的下载完成后的通知创建器：创建一个弹窗提示用户已下载完成。可直接安装。
 * @Version:
 */
public class DefaultInstallNotifier extends InstallNotifier {

    @Override
    public Dialog create(Activity activity) {
        String updateContent = String.format("版本号：%s\n\n%s", update.getVersionName(), update.getUpdateContent());
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle("安装包已下载，是否安装？")
                .setMessage(updateContent)
                .setPositiveButton("立即安装", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (update.isForced()) {
                            preventDismissDialog(dialog);
                        } else {
                            SafeDialogHandle.safeDismissDialog((Dialog) dialog);
                        }
                        sendToInstall();
                    }
                });

        if (!update.isForced() && update.isIgnore()) {
            builder.setNeutralButton("忽略此版本", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendCheckIgnore();
                    SafeDialogHandle.safeDismissDialog((Dialog) dialog);
                }
            });
        }

        if (!update.isForced()) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendUserCancel();
                    SafeDialogHandle.safeDismissDialog((Dialog) dialog);
                }
            });
        }
        AlertDialog installDialog = builder.create();
        installDialog.setCancelable(false);
        installDialog.setCanceledOnTouchOutside(false);
        return installDialog;
    }

    /**
     * 通过反射 阻止自动关闭对话框
     */
    private void preventDismissDialog(DialogInterface dialog) {
        try {
            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            //设置mShowing值，欺骗android系统
            field.set(dialog, false);
        } catch (Exception e) {
            // ignore
        }
    }

}