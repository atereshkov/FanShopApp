package com.github.handioq.fanshop.net;

import com.github.handioq.fanshop.login.UserAuthState;
import com.github.handioq.fanshop.util.NetworkConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface LoginService {

    @GET(NetworkConstants.LOGIN_URL)
    Observable<UserAuthState> loginRequest(@Query("username") String login, // email? login?
                                           @Query("password") String password);

}
