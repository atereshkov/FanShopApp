package com.github.handioq.fanshop.application;

import android.app.Application;
import android.util.Log;

import com.github.handioq.BuildConfig;
import com.github.handioq.fanshop.net.NetworkService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Singleton
 */
public class FanShopApp extends Application {

    private static FanShopApp instance;
    private NetworkService networkService;
    private Realm realm;

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

        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
    }

    private void checkBuild() {
        if (BuildConfig.DEBUG) {
            Log.d("BUILD", BuildConfig.BUILD_TYPE);
        }
    }

    public NetworkService getNetworkService() {
        return networkService;
    }

    public Realm getRealm() {
        return realm;
    }
}