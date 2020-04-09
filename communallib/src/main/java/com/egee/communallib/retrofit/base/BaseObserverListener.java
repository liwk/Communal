package com.egee.communallib.retrofit.base;


/**
 * @Date: 2019/10/18 11:30
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public interface BaseObserverListener<T> {

    void onSuccess(T t);

    void onServerError(NetErrorBean errorBean);

    void onError(NetErrorBean errorBean);

    void onRComplete();

}
