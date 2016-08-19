package com.mzba.pokemon.util;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.mzba.pokemon.PokemonApplication;

/**
 *
 * Created by 06peng on 16/4/20.
 */
public class LocalBroadcasts {

    private LocalBroadcasts() {
    }

    private static LocalBroadcastManager sLocalBroadcastManager;

    private synchronized static void checkLocalBroadcastManagerInstance() {
        if (null == sLocalBroadcastManager) {
            sLocalBroadcastManager = LocalBroadcastManager.getInstance(PokemonApplication.getInstance());
        }
    }

    public static void registerLocalReceiver(BroadcastReceiver receiver, String... actions) {
        if (null == receiver) {
            return;
        }
        if (null == actions || actions.length == 0) {
            return;
        }
        IntentFilter filter = new IntentFilter();
        for (int i = 0; i < actions.length; i++) {
            filter.addAction(actions[i]);
        }
        registerLocalReceiver(receiver, filter);
    }

    public static void registerLocalReceiver(BroadcastReceiver receiver, IntentFilter... filters) {
        if (null == receiver) {
            return;
        }
        if (null == filters || filters.length == 0) {
            return;
        }
        checkLocalBroadcastManagerInstance();
        for (int i = 0; i < filters.length; i++) {
            sLocalBroadcastManager.registerReceiver(receiver, filters[i]);
        }
    }

    public static void unregisterLocalReceiver(BroadcastReceiver receiver) {
        checkLocalBroadcastManagerInstance();
        try {
            sLocalBroadcastManager.unregisterReceiver(receiver);
        } catch (Exception ignore) {
        }
    }

    public static void sendLocalBroadcast(String action) {
        if (TextUtils.isEmpty(action)) {
            return;
        }
        checkLocalBroadcastManagerInstance();
        sLocalBroadcastManager.sendBroadcast(new Intent(action));
    }

    public static void sendLocalBroadcast(Intent intent) {
        if (null == intent) {
            return;
        }
        checkLocalBroadcastManagerInstance();
        sLocalBroadcastManager.sendBroadcast(intent);
    }


}
