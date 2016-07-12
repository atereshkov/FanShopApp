package com.github.handioq.fanshop.application;

import android.app.Application;
import android.util.Log;

import com.github.handioq.BuildConfig;
import com.github.handioq.fanshop.net.NetworkService;

/**
 * Singleton
 */
public class FanShopApp extends Application {

    private NetworkService networkService;

    @Override
    public void onCreate() {
        super.onCreate();
        onAppCreated();
        checkBuild();
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