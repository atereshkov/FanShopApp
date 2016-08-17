package com.github.handioq.fanshop.di.module;

import android.content.Context;

import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.AuthPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule {

    private final Context context;

    public NetModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    NetworkService providesNetworkService() {
        return new NetworkService();
    }

    @Provides
    @Singleton
    AuthPreferences providesAuthPreferences() {
        return new AuthPreferences(context);
    }

}
