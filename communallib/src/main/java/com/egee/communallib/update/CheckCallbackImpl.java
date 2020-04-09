package com.egee.communallib.update;

import com.egee.communallib.utils.ContextUtils;
import com.egee.communallib.utils.ToastUtil;


import org.lzh.framework.updatepluginlib.base.CheckCallback;
import org.lzh.framework.updatepluginlib.model.Update;

/**
 * @Date: 2019/10/19 17:01
 * @Author: YGZ
 * @Description: 执行更新检查时的回调通知实现类
 * @Version:
 */
public class CheckCallbackImpl implements CheckCallback {

    @Override
    public void onCheckStart() {
        // 此方法的回调所处线程异于其他回调。其他回调所处线程为UI线程。
        // 此方法所处线程为你启动更新任务是所在线程
    }

    @Override
    public void hasUpdate(Update update) {
        // 检查到有更新时通知到此
        ToastUtil.showToast(ContextUtils.getContext(),"检测到有版本更新");
    }

    @Override
    public void noUpdate() {
        // 检查到无更新时通知到此
    }

    @Override
    public void onCheckError(Throwable t) {
        // 当更新检查错误的时候通知到此。
    }

    @Override
    public void onUserCancel() {
        // 用户点击取消更新的时候通知到此
        ToastUtil.showToast(ContextUtils.getContext(),"已取消更新");
    }

    @Override
    public void onCheckIgnore(Update update) {
        // 用户点击忽略此版本更新时通知到此
        ToastUtil.showToast(ContextUtils.getContext(),"已忽略此版本更新");
    }

}