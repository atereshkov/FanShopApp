package com.github.handioq.fanshop.net;

import com.github.handioq.fanshop.login.UserAuthState;
import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.model.User;
import com.github.handioq.fanshop.util.NetworkConstants;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    String LOGIN_URL = "/auth";
    String SIGNUP_URL = "/auth";
    String CATALOG_URL = "/catalog";

    @GET(LOGIN_URL)
    Observable<UserAuthState> login(@Query("username") String login,
                                    @Query("password") String password);

    @POST(SIGNUP_URL)
    Observable<User> signup(@Body User user);

    @GET(CATALOG_URL)
    Observable<List<Product>> getProducts(@Query("page") int page,
                                          @Query("limit") int limit);

}
