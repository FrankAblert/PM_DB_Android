package com.mzba.pokemon.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

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

    private MainPresenter mPresenter;
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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_refreshlayout);
        mRecyclerView =  (HotFixRecyclerView) findViewById(R.id.main_recyclerview);
        mAdapter = new PokemonAdapter(this, mPokemonEntities);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpaceGridItemDecoration(30));

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected boolean initMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.load(IPresenter.INIT_DATA);
            }
        });
        mRecyclerView.addOnScrollListener(new CustomRecyclerScrollListener(mRecyclerView, new CustomRecyclerScrollListener.onLoadListener() {
            @Override
            public void onload() {
                mPresenter.loadMore();
            }
        }));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter = new MainPresenter(this, new MainModel());
        WorkProxy.proxy(mPresenter);
        mPresenter.load(IPresenter.INIT_DATA);
    }


    @Override
    public void updateUI(String action, Object object) {
        if (action.equals(IPresenter.INIT_DATA)) {
            if (object != null) {
                ArrayList<PokemonEntity> pokemonEntities = (ArrayList<PokemonEntity>) object;
                mPokemonEntities.clear();
                mPokemonEntities.addAll(pokemonEntities);
                mAdapter.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);
            }
        } else if (action.equals(MainPresenter.GETDATA_BYPAGE)) {
            if (object != null) {
                ArrayList<PokemonEntity> pokemonEntities = (ArrayList<PokemonEntity>) object;
                mPokemonEntities.addAll(pokemonEntities);
                mAdapter.notifyDataSetChanged();
            }
        } else if (action.equals(MainPresenter.SEARCH)) {

        }
        mPresenter.setIsLoading(false);
    }
}
