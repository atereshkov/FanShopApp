package com.github.handioq.fanshop.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    Realm providesRealm() { // TODO: provide context
        //RealmConfiguration config = new RealmConfiguration.Builder(context).build();
        //Realm.setDefaultConfiguration(config);

        return Realm.getDefaultInstance();
    }

}
