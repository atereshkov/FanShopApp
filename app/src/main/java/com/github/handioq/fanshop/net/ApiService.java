package com.github.handioq.fanshop.net;

import com.github.handioq.fanshop.login.UserAuthState;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dto.UserDTO;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
    Observable<UserDTO> signup(@Body UserDTO userDTO);

    @GET(CATALOG_URL)
    Observable<List<ProductDTO>> getProducts(@Query("page") int page,
                                             @Query("limit") int limit);

    @GET(CATALOG_URL + "/{id}")
    Observable<ProductDTO> getProduct(@Path("id") int id);
}
