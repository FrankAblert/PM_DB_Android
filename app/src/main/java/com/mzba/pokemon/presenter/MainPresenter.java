package com.mzba.pokemon.presenter;

import com.mzba.pokemon.entity.PokemonEntity;
import com.mzba.pokemon.model.IModel;
import com.mzba.pokemon.model.MainModel;
import com.mzba.pokemon.param.MainParam;
import com.mzba.pokemon.util.SqlUtil;
import com.mzba.pokemon.view.IView;

import java.util.ArrayList;

/**
 * Presenter of MainActivity
 * Created by 06peng on 16/8/19.
 */
public class MainPresenter implements IPresenter {

    private IView mView;
    private MainModel mModel;

    private static final int COUNT = SqlUtil.SIZECOUNT;
    private int mPage = 1;
    public static String GETDATA_BYPAGE = PREFIX + "GETDATA_BYPAGE";
    public static String SEARCH = PREFIX + "SEARCH";

    private boolean mIsLoading;

    public MainPresenter(IView view, IModel model) {
        this.mView = view;
        this.mModel = (MainModel) model;
    }

    @Override
    public void load(String action) {
        mPage = 1;
        Object object = mModel.get(new MainParam());
        mView.updateUI(action, object);
    }

    /**
     * 分页加载更多
     */
    public void loadMore() {
        if (!mIsLoading) {
            mIsLoading = true;
            mPage++;
            ArrayList<PokemonEntity> pokemonEntities = mModel.getPokemons(mPage, COUNT);
            mView.updateUI(GETDATA_BYPAGE, pokemonEntities);
        }
    }

    /**
     * 获取当前的数据列表
     */
    public void getCurrentList() {
        if (!mIsLoading) {
            mIsLoading = true;
            ArrayList<PokemonEntity> pokemonEntities = mModel.getPokemons(mPage, COUNT);
            mView.updateUI(INIT_DATA, pokemonEntities);
        }
    }

    public void search(String keyword) {
        if (!mIsLoading) {
            mIsLoading = true;
            ArrayList<PokemonEntity> pokemonEntities = mModel.searchPokemons(keyword);
            mView.updateUI(SEARCH, pokemonEntities);
        }
    }

    public ArrayList<String> getRecentKeywords() {
        return mModel.getRecentKeywords();
    }

    public void setIsLoading(boolean isLoading) {
        this.mIsLoading = isLoading;
    }
}
