package com.egee.communallib.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Date: 2020/4/18 14:36
 * @Author: LWK
 * @Description:
 * @Version:
 */
public abstract class BaseFragment extends Fragment {
    protected final String TAG = getClass().getSimpleName();
    protected Activity mActivity;
    private Unbinder mUnbinder;
    //封装Toast对象
    private static Toast toast;
    public Context context;

    /**
     * 在Fragment与Activity窗口关联后回调
     */
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    /**
     * 在Bundle对象中可以获取在Activity中传过来的数据
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 创建Fragment显示的View
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (initLayout() != 0) {
            return inflater.inflate(initLayout(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }


    /**
     * @param view               onCreateView中返回的view
     * @param savedInstanceState 获取Fragment保存的转态，fragment没有onRestoreInstanceState方法
     */
    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view, savedInstanceState);
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 在onCreateView()中创建的视图将被移除
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    private void init(@NotNull View view, @Nullable Bundle savedInstanceState) {

        mUnbinder = ButterKnife.bind(this, view);
        initView(view);
        initData(savedInstanceState);
    }


    protected abstract int initLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

    /**
     * 设置数据
     */
    protected abstract void initData(@Nullable Bundle savedInstanceState);


    /**
     * 显示提示  toast
     *
     * @param msg 提示信息
     */
    @SuppressLint("ShowToast")
    public void showToast(String msg) {
        try {
            if (null == toast) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }
}
