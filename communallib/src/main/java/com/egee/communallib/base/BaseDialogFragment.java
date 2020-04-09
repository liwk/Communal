package com.egee.communallib.base;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.egee.communallib.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

/**
 * @Date: 2019/10/16 14:02
 * @Author: YGZ
 * @Description: DialogFragment基类
 * @Version:
 */
public class BaseDialogFragment extends DialogFragment {

    public Activity mActivity;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void show(@NotNull FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag).addToBackStack(null);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            LogUtils.e(e, e.getMessage());
        }
    }

}