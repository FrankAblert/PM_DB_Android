package com.mzba.pokemon.presenter;

import com.mzba.pokemon.model.IModel;
import com.mzba.pokemon.view.IView;

/**
 * Presenter of MainActivity
 * Created by 06peng on 16/8/19.
 */
public class MainPresenter implements IPresenter {

    private IView mView;
    private IModel mModel;

    public MainPresenter(IView view, IModel model) {
        this.mView = view;
        this.mModel = model;
    }

    @Override
    public void load() {
        Object object = mModel.get(null);
        mView.updateUI(object);
    }
}
