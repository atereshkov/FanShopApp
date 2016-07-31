package com.github.handioq.fanshop.application;

import android.app.Application;
import android.util.Log;

import com.github.handioq.BuildConfig;
import com.github.handioq.fanshop.di.component.DaggerDatabaseComponent;
import com.github.handioq.fanshop.di.component.DaggerNetComponent;
import com.github.handioq.fanshop.di.component.DatabaseComponent;
import com.github.handioq.fanshop.di.component.NetComponent;
import com.github.handioq.fanshop.di.module.AppModule;
import com.github.handioq.fanshop.di.module.DatabaseModule;
import com.github.handioq.fanshop.di.module.NetModule;

public class FanShopApp extends Application {

    private NetComponent netComponent;
    private DatabaseComponent databaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        netComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule())
                .build();

        databaseComponent = DaggerDatabaseComponent.builder()
                .appModule(new AppModule(this))
                .databaseModule(new DatabaseModule())
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public DatabaseComponent getDatabaseComponent() {
        return databaseComponent;
    }

    /*private static FanShopApp instance;
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
    }*/
}