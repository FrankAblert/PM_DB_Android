package com.mzba.pokemon.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 设置行距
 * Created by 06peng on 15/12/8.
 */
public class SpaceGridItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceGridItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space / 2;
        outRect.right = space / 2;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }
}
