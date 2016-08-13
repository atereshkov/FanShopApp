package com.github.handioq.fanshop.net;

import com.github.handioq.fanshop.net.model.LoginDTO;
import com.github.handioq.fanshop.net.model.RegisterDTO;
import com.github.handioq.fanshop.model.dto.CategoryDTO;
import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.model.dto.UserDTO;
import com.github.handioq.fanshop.net.model.AuthResponse;
import com.github.handioq.fanshop.net.model.Response;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    String LOGIN_URL = "/auth";
    String SIGNUP_URL = "/auth";
    String CATALOG_URL = "/catalog";
    String USER_URL = "/user";

    @POST(LOGIN_URL + "/signin")
    Observable<AuthResponse> login(@Body LoginDTO loginDTO);

    @POST(SIGNUP_URL + "/signup")
    Observable<Response> signup(@Body RegisterDTO registerDTO);

    @GET(CATALOG_URL)
    Observable<List<ProductDTO>> getProducts(@Query("offset") int offset,
                                             @Query("limit") int limit);

    @GET(CATALOG_URL + "/search")
    Observable<List<ProductDTO>> search(@Query("query") String query,
                                        @Query("offset") int offset,
                                        @Query("limit") int limit);

    @GET(CATALOG_URL + "/{id}")
    Observable<ProductDTO> getProduct(@Path("id") int id);

    @GET(CATALOG_URL + "/{id}/reviews")
    Observable<List<ReviewDTO>> getReviews(@Path("id") int id);

    @GET(USER_URL + "/{id}")
    Observable<UserDTO> getUser(@Path("id") int userId);

    @GET(USER_URL + "/{id}/cart")
    Observable<List<ProductDTO>> getCart(@Path("id") int userId);

    @POST(USER_URL + "/{id}/cart")
    Observable<Response> addProductToCart(@Path("id") int userId,
                                          @Body ProductDTO productDTO);

    @DELETE(USER_URL + "/{id}/cart/{product_id}")
    Observable<Response> removeProductFromCart(@Path("id") int userId,
                                               @Path("product_id") int productId); //

    @GET(USER_URL + "/{id}/orders")
    Observable<List<OrderDTO>> getOrders(@Path("id") int userId);

    @POST(USER_URL + "/{id}/orders")
    Observable<Response> createOrder(@Path("id") int userId,
                                     @Body OrderDTO orderDTO);

    @GET(CATALOG_URL + "/categories")
    Observable<List<CategoryDTO>> getCategories();

    @GET(CATALOG_URL + "/categories/{id}")
    Observable<CategoryDTO> getCategory(@Path("id") int catId);

}