package com.mzba.pokemon;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by 06peng on 16/8/19.
 */
public class PokemonApplication extends Application {

    private static PokemonApplication mInstance = null;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static PokemonApplication getInstance() {
        return mInstance;
    }

    public static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }
}
