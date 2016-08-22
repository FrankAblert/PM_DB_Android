package com.mzba.pokemon.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mzba.pokemon.R;
import com.mzba.pokemon.util.LocalBroadcasts;

/**
 * Basic Activity
 * Created by 06peng on 16/8/19.
 */
public abstract class BasicActivity extends AppCompatActivity implements IView {

    private BroadcastReceiver mPrReceiver;
    protected Toolbar mToolbar;
    protected View mRootView;
    protected int mLayout = 0;

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        mLayout = provideLayoutResId();
        if (mLayout > 0) {
            mRootView = getLayoutInflater().inflate(mLayout, null);
            setContentView(mRootView);
        }

        View contentView = provideContentView();
        if (null != contentView) {
            mRootView = contentView;
            setContentView(contentView);
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbar();
        initView(paramBundle);
        initListener();
        initBroadcast();
        initData(paramBundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return initMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected abstract int provideLayoutResId();

    protected View provideContentView() {
        return null;
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initListener();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract boolean initMenu(Menu menu);

    public void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void showMsg(String msg) {
        View rootView = findViewById(android.R.id.content);
        if (rootView != null) {
            Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show();
        }
    }


    /**
     * 系统信息选择提示 必须在handler或者控件触发 调用才有效
     *
     * @param message
     * @param what
     */
    public void showChoseMes(String message, final int what) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this, R.style.Base_AlertDialog_AppCompat_Light);;
        AlertDialog.Builder alert = new AlertDialog.Builder(contextThemeWrapper);
        alert.setMessage(message);
        alert.setPositiveButton(getString(R.string.confirm_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        sysMesPositiveButtonEvent(what);
                    }
                });
        alert.setNegativeButton(getString(R.string.confirm_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        sysMesNegativeButtonEvent(what);
                    }
                });
        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                sysMesOnCancelEvent(what);
            }
        });
        alert.show();
    }

    /**
     * 系统信息提示ok按钮回调方法 是 showChoseMes(String message,final int
     * what)，setSysMes(String message,final int what)方法回调
     */
    public void sysMesPositiveButtonEvent(int what) {

    }

    /**
     * 系统信息提示no按钮回调方法 是 showChoseMes(String message,final int what)方法回调
     *
     * @param what
     */
    public void sysMesNegativeButtonEvent(int what) {

    }

    /**
     * 系统信息提示no按钮回调方法 是 showChoseMes(String message,final int what)方法回调
     *
     * @param what
     */
    public void sysMesOnCancelEvent(int what) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterLocalReceiver(mPrReceiver);
    }

    protected void initBroadcast() {
        String[] actions = provideBroadcastActions();
        if (null == actions || actions.length == 0) {
            return ;
        }
        registerLocalBroadcastReceiver(mPrReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onReceiveBroadcast(intent.getAction(), intent);
            }
        }, actions);
    }

    protected String[] provideBroadcastActions() {
        return null;
    }

    protected void onReceiveBroadcast(String action, Intent intent) {

    }

    protected void registerLocalBroadcastReceiver(BroadcastReceiver receiver, String... actions) {
        LocalBroadcasts.registerLocalReceiver(receiver, actions);
    }

    protected void registerLocalBroadcastReceiver(BroadcastReceiver receiver, IntentFilter...filters) {
        LocalBroadcasts.registerLocalReceiver(receiver, filters);
    }

    protected void unregisterLocalReceiver(BroadcastReceiver receiver) {
        LocalBroadcasts.unregisterLocalReceiver(receiver);
    }

    @Override
    public void updateUI(String action, Object object) {

    }
}
