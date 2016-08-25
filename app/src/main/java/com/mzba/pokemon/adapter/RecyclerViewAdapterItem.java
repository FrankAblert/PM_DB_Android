package com.mzba.pokemon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mzba.pokemon.entity.BasicEntity;

/**
 * Created by 06peng on 16/8/19.
 * @param <T>
 */
public abstract class RecyclerViewAdapterItem<T extends BasicEntity> extends RecyclerView.ViewHolder {


    public RecyclerViewAdapterItem(Context context, ViewGroup viewGroup, int layoutResId) {
        super(LayoutInflater.from(context).inflate(layoutResId, viewGroup, false));
    }


    public abstract void initViews(T data, int position);


    protected <K extends View> K getView(int id) {
        return (K) itemView.findViewById(id);
    }
}
