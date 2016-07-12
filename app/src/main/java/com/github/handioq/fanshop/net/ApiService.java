package com.github.handioq.fanshop.net;

import com.github.handioq.fanshop.login.UserAuthState;
import com.github.handioq.fanshop.model.User;
import com.github.handioq.fanshop.util.NetworkConstants;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET(NetworkConstants.LOGIN_URL)
    Observable<UserAuthState> login(@Query("username") String login, // email? login?
                                           @Query("password") String password);

    /*@POST(NetworkConstants.SIGNUP_URL)
    Observable<UserAuthState> signupRequest(@Query("username") String login,
                                            @Query("password") String password);*/

    @POST(NetworkConstants.SIGNUP_URL)
    Observable<User> signup(@Body User user);

}
