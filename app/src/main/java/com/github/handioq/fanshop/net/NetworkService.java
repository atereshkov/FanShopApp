package com.github.handioq.fanshop.net;

import com.github.handioq.fanshop.util.NetworkConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkService {

    private AuthService authService;

    public NetworkService() {

        // Define the interceptor, add authentication headers
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Retrofit-FanShop-App").build();
                return chain.proceed(newRequest);
            }
        };


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient okHttpClient = builder.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        authService = retrofit.create(AuthService.class);
    }

    public AuthService getLoginService() {
        return authService;
    }

    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable) {
        return unPreparedObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
