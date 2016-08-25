package com.mzba.pokemon.presenter;

/**
 * Created by 06peng on 16/8/19.
 */
public interface IPresenter {

    String PREFIX = IPresenter.class.getName() + ".";

    String INIT_DATA = PREFIX + "INIT_DATA";

    void load(String action);
}
