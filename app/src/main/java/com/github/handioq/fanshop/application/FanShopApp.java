package com.github.handioq.fanshop.application;

import android.app.Application;

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
    }

    private void onAppCreated() {
        networkService = new NetworkService();
    }

    public NetworkService getNetworkService() {
        return networkService;
    }
}