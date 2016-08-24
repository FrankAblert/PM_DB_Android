package com.mzba.pokemon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mzba.pokemon.entity.BasicEntity;
import com.mzba.pokemon.util.SqlUtil;
import com.mzba.pokemon.widget.CustomFooterView;

import java.util.List;

/**
 *
 * @param <T>
 */
public abstract class CommonRecyclerViewAdapter<T extends BasicEntity> extends BaseRecyclerViewAdapter {

    protected Context mContext;
    private List<T> mData;

    public CommonRecyclerViewAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getAdapterItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getAdapterItemType(int position) {
        return RecyclerViewType.TYPE_RECYCLER_NORMAL;
    }

    @Override
    public void onBindItemHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerViewAdapterItem item = (RecyclerViewAdapterItem) viewHolder;
        if (mData != null && !mData.isEmpty()) {
            item.initViews(mData.get(position), position);
        }
    }

    @Override
    public void onBindHeaderHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public void onBindFooterHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder.itemView instanceof CustomFooterView) {
            if (position >= SqlUtil.SIZECOUNT) {
                ((CustomFooterView) viewHolder.itemView).startLoad();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemHolder(ViewGroup viewGroup, int type) {
        return initAdapterItem(viewGroup, type);
    }

    public abstract RecyclerViewAdapterItem initAdapterItem(ViewGroup viewGroup, int type);

}
