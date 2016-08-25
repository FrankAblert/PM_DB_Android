package com.mzba.pokemon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 *  Created by 06peng on 16/8/19.
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected View mHeaderView = null;
    protected View mFooterView = null;

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    @Override
    public int getItemCount() {
        int headerOrFooter = 0;
        if (mHeaderView != null) {
            headerOrFooter++;
        }

        if (mFooterView != null) {
            headerOrFooter++;
        }
        return getAdapterItemCount() + headerOrFooter;
    }

    /**
     * 返回不包括header和footer的List大小
     *
     * @return
     */
    public abstract int getAdapterItemCount();

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        if (mHeaderView != null && position == 0) {
            onBindHeaderHolder(viewHolder, position);
        } else if (mFooterView != null && position == getItemCount() - 1) {
            onBindFooterHolder(viewHolder, position);
        } else {
            //getItemCount根据是否有header或footer来影响了position,这里需要消除header或footer对position的影响
            int realPosition = position;
            if (mHeaderView != null) {
                realPosition = position - 1;
            }
            final int pos = realPosition;
            onBindItemHolder(viewHolder, pos);
            if (mOnItemClickListener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(viewHolder.itemView, pos);
                    }
                });
            }

            if (mOnItemLongClickListener != null) {
                viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return mOnItemLongClickListener.onItemLongClick(viewHolder.itemView, pos);
                    }
                });
            }
        }

    }

    public abstract void onBindItemHolder(RecyclerView.ViewHolder viewHolder, int position);

    public abstract void onBindHeaderHolder(RecyclerView.ViewHolder viewHolder, int position);

    public abstract void onBindFooterHolder(RecyclerView.ViewHolder viewHolder, int position);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {

        if (type == RecyclerViewType.TYPE_RECYCLER_HEADER && mHeaderView != null) {
            return new SimpleViewHolder(mHeaderView);
        }

        if (type == RecyclerViewType.TYPE_RECYCLER_FOOTER && mFooterView != null) {
            return new SimpleViewHolder(mFooterView);
        }

        return onCreateItemHolder(viewGroup, type);
    }


    public abstract RecyclerView.ViewHolder onCreateItemHolder(ViewGroup viewGroup, int type);

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) {
            return RecyclerViewType.TYPE_RECYCLER_HEADER;
        }

        if (mFooterView != null && position == getItemCount() - 1) {
            return RecyclerViewType.TYPE_RECYCLER_FOOTER;
        }

        if (mHeaderView != null) {
            //getItemCount根据是否有header或footer来影响了position,这里需要消除header或footer对position的影响
            return getAdapterItemType(position - 1);
        }

        return getAdapterItemType(position);
    }

    /**
     * 根据位置得到view的类型
     */
    public abstract int getAdapterItemType(int position);



    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void addHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyDataSetChanged();
    }

    public void addFooterView(View footerView) {
        mFooterView = footerView;
        notifyDataSetChanged();
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public View getFooterView() {
        return mFooterView;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface  OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }
}
