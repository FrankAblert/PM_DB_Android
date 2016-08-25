package com.mzba.pokemon.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.mzba.pokemon.R;
import com.mzba.pokemon.adapter.BaseRecyclerViewAdapter;
import com.mzba.pokemon.util.SqlUtil;

/**
 * @author 06peng
 */
public class CustomRecyclerScrollListener extends RecyclerView.OnScrollListener {

    private onLoadListener listener;

    private boolean isOnLoadEnable = true;
    private RecyclerView.OnScrollListener onScrollListener;
    private RecyclerView mRecyclerView;
    private int mScrollPosition;

    private int[] lastPositions;
    private int lastVisibleItemPosition;

    private int firstVisibleItemPosition = 0;
    private int[] firstPositions = null;

    public CustomRecyclerScrollListener(RecyclerView mRecyclerView, onLoadListener listener) {
        this.listener = listener;
        this.mRecyclerView = mRecyclerView;
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (visibleItemCount > 0  && lastVisibleItemPosition >= totalItemCount - 1 &&
                    totalItemCount >= SqlUtil.SIZECOUNT) {
                if (listener != null && isOnLoadEnable && mScrollPosition <= lastVisibleItemPosition) {
                    startLoad();
                    listener.onload();
                }
            }
        }
        if (!isOnLoadEnable) {
            setOnLoadComplete();
        }
        mScrollPosition = lastVisibleItemPosition;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(recyclerView, newState);
        }

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (onScrollListener != null) {
            onScrollListener.onScrolled(recyclerView, dx, dy);
        }
        lastVisibleItemPosition = getLastVisibleItemPosition(recyclerView);
    }

    public int getFirstVisibleItemPosition(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            firstVisibleItemPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof LinearLayoutManager) {
            firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            if (firstPositions == null) {
                firstPositions = new int[staggeredGridLayoutManager.getSpanCount()];
            }
            firstPositions = staggeredGridLayoutManager.findFirstVisibleItemPositions(firstPositions);
            int max = firstPositions[0];
            for (int value : firstPositions) {
                if (value < max) {
                    firstVisibleItemPosition = value;
                }
            }
        }
        return firstVisibleItemPosition;
    }

    public int getLastVisibleItemPosition(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            if (lastPositions == null) {
                lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
            }
            lastPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
            int max = lastPositions[0];
            for (int value : lastPositions) {
                if (value > max) {
                    lastVisibleItemPosition = value;
                }
            }
        }
        return lastVisibleItemPosition;
    }

    public interface onLoadListener {
        void onload();
    }

    public void setOnLoadComplete() {
        if (mRecyclerView.getAdapter() instanceof BaseRecyclerViewAdapter) {
            BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) mRecyclerView.getAdapter();
            View footView = adapter.getFooterView();
            if (footView != null) {
                View pb = footView.findViewById(R.id.load_more_progressbar);
                if (pb != null) {
                    pb.setVisibility(View.GONE);
                }
                View more = footView.findViewById(R.id.load_more_textview);
                if (more != null && more instanceof TextView) {
                    more.setVisibility(View.VISIBLE);
                    ((TextView) more).setText(mRecyclerView.getContext().getString(R.string.load_complete));
                }
                if (adapter.getHeaderView() != null && adapter.getItemCount() == 2) {
                    footView.setVisibility(View.GONE);
                } else if (adapter.getItemCount() == 1) {
                    footView.setVisibility(View.GONE);
                }
            }
        }
    }

    public void startLoad() {
        if (mRecyclerView.getAdapter() instanceof BaseRecyclerViewAdapter) {
            View footView = ((BaseRecyclerViewAdapter) mRecyclerView.getAdapter()).getFooterView();
            if (footView != null) {
                View pb = footView.findViewById(R.id.load_more_progressbar);
                if (pb != null) {
                    pb.setVisibility(View.VISIBLE);
                }
                View more = footView.findViewById(R.id.load_more_textview);
                if (more != null) {
                    more.setVisibility(View.GONE);
                }
            }
        }
    }

    public void setOnLoadEnable(boolean isOnLoadEnable) {
        this.isOnLoadEnable = isOnLoadEnable;
        setOnLoadComplete();
    }
}
