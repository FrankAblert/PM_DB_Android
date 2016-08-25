package com.mzba.pokemon.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 自定义Recyclerview
 * Created by 06peng on 2015/2/6 0006.
 */
public class HotFixRecyclerView extends RecyclerView {

    public HotFixRecyclerView(Context context) {
        super(context);
    }

    public HotFixRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HotFixRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void stopScroll() {
        try {
            super.stopScroll();
        } catch (NullPointerException exception) {
            /**
             *  The mLayout has been disposed of before the
             *  RecyclerView and this stops the application
             *  from crashing.
             */
        }
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
    }
}
