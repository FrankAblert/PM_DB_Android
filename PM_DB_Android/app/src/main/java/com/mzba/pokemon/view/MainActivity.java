package com.mzba.pokemon.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.mzba.pokemon.R;
import com.mzba.pokemon.adapter.PokemonAdapter;
import com.mzba.pokemon.entity.PokemonEntity;
import com.mzba.pokemon.model.MainModel;
import com.mzba.pokemon.presenter.IPresenter;
import com.mzba.pokemon.presenter.MainPresenter;
import com.mzba.pokemon.widget.CustomRecyclerScrollListener;
import com.mzba.pokemon.widget.HotFixRecyclerView;
import com.mzba.pokemon.widget.SpaceGridItemDecoration;

import java.util.ArrayList;

import cn.tobeing.pxandroid.proxy.WorkProxy;

public class MainActivity extends BasicActivity {

    private IPresenter mPresenter;
    private PokemonAdapter mAdapter;

    private SwipeRefreshLayout mRefreshLayout;
    private HotFixRecyclerView mRecyclerView;

    private ArrayList<PokemonEntity> mPokemonEntities = new ArrayList<>();

    @Override
    protected int provideLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_refreshlayout);
        mRecyclerView =  (HotFixRecyclerView) findViewById(R.id.main_recyclerview);
        mAdapter = new PokemonAdapter(this, mPokemonEntities);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpaceGridItemDecoration(30));
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        mRecyclerView.addOnScrollListener(new CustomRecyclerScrollListener(mRecyclerView, new CustomRecyclerScrollListener.onLoadListener() {
            @Override
            public void onload() {

            }
        }));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter = (IPresenter) WorkProxy.proxy(new MainPresenter(this, new MainModel()));
        mPresenter.load(IPresenter.INIT_DATA);
    }


    @Override
    public void updateUI(String action, Object object) {

    }
}
