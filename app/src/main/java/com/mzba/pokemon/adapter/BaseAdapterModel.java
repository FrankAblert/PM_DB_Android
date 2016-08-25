package com.mzba.pokemon.adapter;

/**
 *  Created by 06peng on 16/8/19.
 */
public abstract class BaseAdapterModel {
    /**
     * 有多少种不同类型的数据
     *
     * @return
     */
    public abstract int getDataTypeCount();

    /**
     * 返回这个数据的类型
     *
     * @return
     */
    public abstract int getDataType();

    /**
     * 数据的类型
     */
    protected int type;
    /**
     * 真正的数据
     */
    protected Object data;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
