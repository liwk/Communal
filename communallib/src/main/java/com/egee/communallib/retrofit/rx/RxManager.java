package com.egee.communallib.retrofit.rx;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Date: 2020/4/6 10:54
 * @Author: LWK
 * @Description:
 * @Version:
 */
public class RxManager {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * 添加observer
     *
     */
    public void add(Disposable disposable) {
        if (disposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    public void dispose() {
        // 取消订阅
        mCompositeDisposable.dispose();
    }

}
