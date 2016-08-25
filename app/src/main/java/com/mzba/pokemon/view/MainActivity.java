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
import com.mzba.pokemon.widget.GridSpanSizeLookup;
import com.mzba.pokemon.widget.HotFixRecyclerView;
import com.mzba.pokemon.widget.SpaceGridItemDecoration;

import java.util.ArrayList;

import cn.tobeing.pxandroid.proxy.WorkProxy;

/**
 * 主界面
 */
public class MainActivity extends BasicActivity {

    private MainPresenter mPresenter;
    private PokemonAdapter mAdapter;
    private ArrayAdapter<String> mQuickSearchArrayAdapter;

    private SwipeRefreshLayout mRefreshLayout;
    private HotFixRecyclerView mRecyclerView;
    private CustomRecyclerScrollListener mRecyclerScrollListener;

    private ArrayList<PokemonEntity> mPokemonEntities = new ArrayList<>();
    private ArrayList<String> mQuickSearchEntities = new ArrayList<>();

    private InitiateSearch mInitiateSearch;

    private View mLineDivider;
    private RelativeLayout mBackgroundRL;
    private CardView mCardSearchView;
    private ImageView mSearchBackIV, mSearchClearIV;
    private EditText mSearchKeywordET;
    private ListView mRecentListView;
    private ProgressBar mLoadProgressBar;

    /**
     * 是否有搜索操作，结束搜索之后要重新获取搜索之前的数据
     */
    private boolean mIsListChanged;
    /**
     * 搜索之前要记录当前的位置，以便恢复数据使用
     */
    private int mCurrentPosition;

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
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup(mRecyclerView, 2));
        mRecyclerView.setLayoutManager(layoutManager);

        mInitiateSearch = new InitiateSearch();
        mBackgroundRL = (RelativeLayout) findViewById(R.id.view_search);
        mLineDivider = findViewById(R.id.line_divider);
        mSearchKeywordET = (EditText) findViewById(R.id.edit_text_search);
        mCardSearchView = (CardView) findViewById(R.id.card_search);
        mSearchBackIV = (ImageView) findViewById(R.id.image_search_back);
        mSearchClearIV = (ImageView) findViewById(R.id.clearSearch);
        mRecentListView = (ListView) findViewById(R.id.listView);
        mLoadProgressBar = (ProgressBar) findViewById(R.id.marker_progress);
        mLoadProgressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FFFFFF"),//Pink color
                android.graphics.PorterDuff.Mode.MULTIPLY);
        mQuickSearchArrayAdapter = new ArrayAdapter<>(this, R.layout.recent_search_item,
                R.id.tv_rs_keword, mQuickSearchEntities);
        mRecentListView.setAdapter(mQuickSearchArrayAdapter);
        setListViewLineDividerVisibile();
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
                setListViewLineDividerVisibile();
                mInitiateSearch.handleToolBar(MainActivity.this, mCardSearchView, mToolbar, mBackgroundRL,
                        mRecentListView, mSearchKeywordET);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrentPosition = 0;
                mPresenter.load(IPresenter.INIT_DATA);
            }
        });
        mRecyclerScrollListener = new CustomRecyclerScrollListener(mRecyclerView, new CustomRecyclerScrollListener.onLoadListener() {
            @Override
            public void onload() {
                mPresenter.loadMore();
            }
        });
        mRecyclerView.addOnScrollListener(mRecyclerScrollListener);
        //清除搜索
        mSearchClearIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchKeywordET.getText().toString().length() == 0) {

                } else {
                    mSearchKeywordET.setText("");
                    mRecentListView.setVisibility(View.VISIBLE);
                    ((InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).
                            toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    setListViewLineDividerVisibile();
                }
            }
        });
        //返回
        mSearchBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recoveryData();
            }
        });
        mSearchKeywordET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (mSearchKeywordET.getText().toString().trim().length() > 0) {
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                                hideSoftInputFromWindow(mSearchKeywordET.getWindowToken(), 0);
                        mRecentListView.setVisibility(View.GONE);
                        mLoadProgressBar.setVisibility(View.VISIBLE);
                        search(mSearchKeywordET.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
        mRecentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String keyword = mQuickSearchArrayAdapter.getItem(position);
                mSearchKeywordET.setText(keyword);
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(mSearchKeywordET.getWindowToken(), 0);
                mLoadProgressBar.setVisibility(View.VISIBLE);
                mRecentListView.setVisibility(View.GONE);
                search(mSearchKeywordET.getText().toString());
            }
        });
        mSearchKeywordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mSearchKeywordET.getText().toString().length() == 0) {
                    mQuickSearchEntities.clear();
                    mQuickSearchArrayAdapter.notifyDataSetChanged();
                    mSearchClearIV.setImageResource(R.drawable.abc_ic_voice_search_api_mtrl_alpha);
                    setListViewLineDividerVisibile();
                } else {
                    mRecentListView.setVisibility(View.VISIBLE);
                    ArrayList<String> recentValues = mPresenter.getRecentKeywords();
                    mQuickSearchEntities.clear();
                    mQuickSearchEntities.addAll(recentValues);
                    mQuickSearchArrayAdapter.notifyDataSetChanged();
                    mSearchClearIV.setImageResource(R.mipmap.ic_cancel_dark);
                    setListViewLineDividerVisibile();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBackgroundRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recoveryData();
            }
        });
//        final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
//        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (isClickedBackKey && !Utils.isKeyboardShown(rootView)) {
//                    mInitiateSearch.handleToolBar(MainActivity.this, mCardSearchView, mToolbar, mBackgroundRL,
//                            mRecentListView, mSearchKeywordET, mLineDivider);
//                    mPresenter.getCurrentList();
//                }
//            }
//        });
    }


    @Override
    public void onBackPressed() {
        if (mCardSearchView.getVisibility() == View.VISIBLE) {
            recoveryData();
        } else {
            super.onBackPressed();
        }
    }

    private void setListViewLineDividerVisibile() {
        if (mQuickSearchArrayAdapter.getCount() == 0) {
            mLineDivider.setVisibility(View.GONE);
        } else {
            mLineDivider.setVisibility(View.VISIBLE);
        }
    }

    private void recoveryData() {
        mInitiateSearch.handleToolBar(MainActivity.this, mCardSearchView, mToolbar, mBackgroundRL,
                mRecentListView, mSearchKeywordET);
        if (mIsListChanged) {
            mPresenter.getCurrentList();
        }
    }

    private void search(String keyword) {
        mCurrentPosition = mRecyclerScrollListener.getLastVisibleItemPosition(mRecyclerView);
        mPresenter.search(keyword);
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
                if (mCurrentPosition > 0) {
                    mRecyclerView.scrollToPosition(mCurrentPosition);
                }
                if (pokemonEntities.size() < 20) {
                    mRecyclerScrollListener.setOnLoadEnable(false);
                }
            }
            mIsListChanged = false;
        } else if (action.equals(MainPresenter.GETDATA_BYPAGE)) {
            if (object != null) {
                ArrayList<PokemonEntity> pokemonEntities = (ArrayList<PokemonEntity>) object;
                mPokemonEntities.addAll(pokemonEntities);
                mAdapter.notifyDataSetChanged();
                if (pokemonEntities.size() < 20) {
                    mRecyclerScrollListener.setOnLoadEnable(false);
                }
            }
            mIsListChanged = false;
            mRecyclerScrollListener.setOnLoadComplete();
        } else if (action.equals(MainPresenter.SEARCH)) {
            mIsListChanged = true;
            mLoadProgressBar.setVisibility(View.GONE);
            ArrayList<PokemonEntity> pokemonEntities = (ArrayList<PokemonEntity>) object;
            mPokemonEntities.clear();
            mPokemonEntities.addAll(pokemonEntities);
            mAdapter.notifyDataSetChanged();
        }
        mPresenter.setIsLoading(false);
    }
}
