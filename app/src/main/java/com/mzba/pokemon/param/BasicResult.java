package com.mzba.pokemon.param;

/**
 * Basic Result
 *
 * @param <T> API中返回的data 字段
 */
public class BasicResult<T> implements java.io.Serializable {
    public int code;
    public String msg;
    public T data;
}
