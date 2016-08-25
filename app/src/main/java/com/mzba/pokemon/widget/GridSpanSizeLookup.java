package com.mzba.pokemon.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mzba.pokemon.adapter.BaseRecyclerViewAdapter;
import com.mzba.pokemon.adapter.RecyclerViewType;

/**
 * Created by 06peng on 16/8/19.
 */
public class GridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private int mSpanSize = 1;
    private RecyclerView mRecyclerView;

    public GridSpanSizeLookup(RecyclerView recyclerView, int spanSize) {
        mSpanSize = spanSize;
        mRecyclerView = recyclerView;
    }

    @Override
    public int getSpanSize(int position) {
        if (mRecyclerView.getAdapter() instanceof BaseRecyclerViewAdapter) {
            BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) mRecyclerView.getAdapter();
            if (adapter.getItemViewType(position) == RecyclerViewType.TYPE_RECYCLER_HEADER
                    || adapter.getItemViewType(position) == RecyclerViewType.TYPE_RECYCLER_FOOTER) {
                return mSpanSize;
            }
        }
        return 1;
    }
}
