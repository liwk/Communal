package com.egee.communallib.retrofit.base;



import com.egee.communallib.retrofit.code.ServerCode;
import com.egee.communallib.retrofit.exception.ExceptionHandler;
import com.egee.communallib.retrofit.exception.ResponseException;
import com.egee.communallib.utils.ContextUtils;
import com.egee.communallib.utils.ToastUtil;

import io.reactivex.observers.DisposableObserver;


/**
 * @Date: 2019/10/18 11:52
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public abstract class BaseObserver<T extends BaseResponse> extends DisposableObserver<T> implements BaseObserverListener<T> {

    /**
     * 事件处理成功
     */
    @Override
    public void onNext(T response) {
        if (response.getCode() == ServerCode.SERVER_CODE_SUCCESS) {
            onSuccess(response);
        } else {
            ResponseException exception = ExceptionHandler.handleServerException(response.getCode(), response.getMessage());
            NetErrorBean errorBean = new NetErrorBean(exception.getCode(), exception.getMessage());
            onServerError(errorBean);
        }
    }

    /**
     * 在事件处理过程中发生异常时，onError(Throwable e)会被触发，onError(Throwable e)和onComplete是互斥的
     */
    @Override
    public void onError(Throwable e) {
        ResponseException exception = ExceptionHandler.handleException(e);
        NetErrorBean errorBean = new NetErrorBean(exception.getCode(), exception.getMessage());
        onError(errorBean);
    }

    /**
     * 当不会再有新的onNext()发出时，会触发onComplete()方法作为标志
     */
    @Override
    public void onComplete() {
        onRComplete();
    }

    @Override
    public void onSuccess(T t) {
    }

    /**
     * 服务器返回的错误，与onError(NetErrorBean errorBean)互斥，不会被同时触发
     *
     * @param errorBean
     */
    @Override
    public void onServerError(NetErrorBean errorBean) {
        onError(errorBean);
    }

    /**
     * retrofit返回的错误，与onServerError(NetErrorBean errorBean)互斥，不会被同时触发
     *
     * @param errorBean
     */
    @Override
    public void onError(NetErrorBean errorBean) {
        // 这里直接showToast，不用再在每一个页面处理
        ToastUtil.showToast(ContextUtils.getContext(), errorBean.getMessage());
    }

    @Override
    public void onRComplete() {
    }

}
