package com.egee.communallib.widget.recyclerview.brvah;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.liwk.retrofitlib.R;

/**
 * @Date: 2019/8/9 16:36
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public final class BrvahLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.view_brvah_loadmore;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }

}
