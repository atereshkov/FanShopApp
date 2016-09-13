package com.github.handioq.fanshop.net;

import com.github.handioq.fanshop.util.AuthPreferences;
import com.github.handioq.fanshop.util.NetworkConstants;

import java.io.IOException;
import java.util.concurrent.Executors;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkService {

    private static final String USER_AGENT_HEADER = "Retrofit-FanShop-App";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final String HEADER_AUTHORIZATION = "Authorization";

    private ApiService apiService;
    private final static Scheduler NETWORK_SINGLE
            = Schedulers.from(Executors.newFixedThreadPool(3));

    public static <E> Observable.Transformer<E, E> applyScheduler() {
        return new Observable.Transformer<E, E>() {
            @Override
            public Observable<E> call(Observable<E> observable) {
                return observable
                        .subscribeOn(NETWORK_SINGLE)
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public NetworkService() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = chain.request()
                        .newBuilder()
                        .addHeader(HEADER_USER_AGENT, USER_AGENT_HEADER).build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);

        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder();
                if (AuthPreferences.getUserToken() != null) {
                    requestBuilder = original.newBuilder()
                            .header(HEADER_AUTHORIZATION, AuthPreferences.getUserToken());
                }

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }

}
