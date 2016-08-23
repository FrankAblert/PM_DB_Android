package com.mzba.pokemon.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mzba.pokemon.R;
import com.mzba.pokemon.adapter.PokemonAdapter;
import com.mzba.pokemon.entity.PokemonEntity;
import com.mzba.pokemon.model.MainModel;
import com.mzba.pokemon.presenter.IPresenter;
import com.mzba.pokemon.presenter.MainPresenter;
import com.mzba.pokemon.util.InitiateSearch;
import com.mzba.pokemon.widget.CustomRecyclerScrollListener;
import com.mzba.pokemon.widget.HotFixRecyclerView;
import com.mzba.pokemon.widget.SpaceGridItemDecoration;

import java.util.ArrayList;

import cn.tobeing.pxandroid.proxy.WorkProxy;

public class MainActivity extends BasicActivity {

    private MainPresenter mPresenter;
    private PokemonAdapter mAdapter;
    private ArrayAdapter<String> mQuickSearchArrayAdapter;


    private SwipeRefreshLayout mRefreshLayout;
    private HotFixRecyclerView mRecyclerView;

    private ArrayList<PokemonEntity> mPokemonEntities = new ArrayList<>();
    private ArrayList<String> mQuickSearchEntities = new ArrayList<>();

    private InitiateSearch initiateSearch;

    private View line_divider;
    private RelativeLayout view_search;
    private CardView card_search;
    private ImageView image_search_back, clearSearch;
    private EditText edit_text_search;
    private ListView listView;
    private ProgressBar marker_progress;

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

        initiateSearch = new InitiateSearch();
        view_search = (RelativeLayout) findViewById(R.id.view_search);
        line_divider = findViewById(R.id.line_divider);
        edit_text_search = (EditText) findViewById(R.id.edit_text_search);
        card_search = (CardView) findViewById(R.id.card_search);
        image_search_back = (ImageView) findViewById(R.id.image_search_back);
        clearSearch = (ImageView) findViewById(R.id.clearSearch);
        listView = (ListView) findViewById(R.id.listView);
        marker_progress = (ProgressBar) findViewById(R.id.marker_progress);
        marker_progress.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FFFFFF"),//Pink color
                android.graphics.PorterDuff.Mode.MULTIPLY);
        mQuickSearchArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, android.R.id.text1, mQuickSearchEntities);
        listView.setAdapter(mQuickSearchArrayAdapter);
        IsAdapterEmpty();
    }

    @Override
    protected boolean initMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                IsAdapterEmpty();
                initiateSearch.handleToolBar(MainActivity.this, card_search, mToolbar, view_search,
                        listView, edit_text_search, line_divider);
                break;
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
        //清除搜索
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_text_search.getText().toString().length() == 0) {

                } else {
                    edit_text_search.setText("");
                    listView.setVisibility(View.VISIBLE);
                    clearItems();
                    ((InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    IsAdapterEmpty();
                }
            }
        });
        //返回
        image_search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiateSearch.handleToolBar(MainActivity.this, card_search, mToolbar, view_search, listView, edit_text_search, line_divider);
                mPresenter.load(IPresenter.INIT_DATA);
            }
        });
        edit_text_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (edit_text_search.getText().toString().trim().length() > 0) {
                        clearItems();
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(edit_text_search.getWindowToken(), 0);
                        listView.setVisibility(View.GONE);
                        mPresenter.search(edit_text_search.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String keyword = mQuickSearchArrayAdapter.getItem(position);
                edit_text_search.setText(keyword);
                listView.setVisibility(View.GONE);
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(edit_text_search.getWindowToken(), 0);
                mPresenter.search(edit_text_search.getText().toString());
            }
        });
        edit_text_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edit_text_search.getText().toString().length() == 0) {
                    mQuickSearchEntities.clear();
                    mQuickSearchArrayAdapter.notifyDataSetChanged();
                    clearSearch.setImageResource(R.drawable.abc_ic_voice_search_api_mtrl_alpha);
                    IsAdapterEmpty();
                } else {
                    ArrayList<String> recentValues = mPresenter.getRecentKeywords();
                    mQuickSearchEntities.clear();
                    mQuickSearchEntities.addAll(recentValues);
                    mQuickSearchArrayAdapter.notifyDataSetChanged();
                    clearSearch.setImageResource(R.mipmap.ic_cancel_dark);
                    IsAdapterEmpty();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        view_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiateSearch.handleToolBar(MainActivity.this, card_search, mToolbar, view_search, listView, edit_text_search, line_divider);
                mPresenter.load(IPresenter.INIT_DATA);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void IsAdapterEmpty() {
        if (mQuickSearchArrayAdapter.getCount() == 0) {
            line_divider.setVisibility(View.GONE);
        } else {
            line_divider.setVisibility(View.VISIBLE);
        }
    }

    private void clearItems() {
        mPokemonEntities.clear();
        mAdapter.notifyDataSetChanged();
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
            marker_progress.setVisibility(View.GONE);
            ArrayList<PokemonEntity> pokemonEntities = (ArrayList<PokemonEntity>) object;
            mPokemonEntities.clear();
            mPokemonEntities.addAll(pokemonEntities);
            mAdapter.notifyDataSetChanged();
        }
        mPresenter.setIsLoading(false);
    }
}
