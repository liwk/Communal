package com.egee.communallib.widget.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Date: 2020-03-17 13:43
 * @Author: LWK
 * @Description:
 * @Version:
 */
public class CustomRecyclerViewPullScrollListener extends RecyclerView.OnScrollListener {
    private PullScrollListener listener;

    public interface PullScrollListener {
        void onLoadMore();
    }

    public CustomRecyclerViewPullScrollListener(PullScrollListener listener) {
        this.listener = listener;
    }

    /**
     * 标记是否正在向上滑动
     */
    boolean isUpPull = false;
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //总数
            int itemCount = manager.getItemCount();
            //最后显示的位置
            int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();

            if (lastItemPosition == (itemCount - 1) && isUpPull) {
                listener.onLoadMore();
            }

        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        isUpPull = dy > 0;
    }

}
