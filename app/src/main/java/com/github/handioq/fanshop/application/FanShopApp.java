package com.github.handioq.fanshop.application;

import android.app.Application;
import android.util.Log;

import com.github.handioq.BuildConfig;
import com.github.handioq.fanshop.net.NetworkService;

/**
 * Singleton
 */
public class FanShopApp extends Application {

    private static FanShopApp instance;
    private NetworkService networkService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        onAppCreated();
        checkBuild();
    }

    public static FanShopApp getInstance() {
        return instance;
    }

    private void onAppCreated() {
        networkService = new NetworkService();
    }

    private void checkBuild() {
        if (BuildConfig.DEBUG) {
            Log.d("BUILD", BuildConfig.BUILD_TYPE);
        }
    }

    public NetworkService getNetworkService() {
        return networkService;
    }
}