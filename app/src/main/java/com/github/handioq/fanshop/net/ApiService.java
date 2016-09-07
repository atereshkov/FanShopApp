package com.github.handioq.fanshop.net;

import com.github.handioq.fanshop.model.dto.CategoryListDTO;
import com.github.handioq.fanshop.model.dto.OrderDetailsDTO;
import com.github.handioq.fanshop.model.dto.OrderListDTO;
import com.github.handioq.fanshop.model.dto.ProductListDTO;
import com.github.handioq.fanshop.model.dto.ReviewListDTO;
import com.github.handioq.fanshop.model.dto.SpecificationDTO;
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
    Observable<AuthResponse> login(@Query("mail") String mail,
                                   @Query("password") String password);

    @POST(SIGNUP_URL + "/signup")
    Observable<Response> signup(@Body RegisterDTO registerDTO);

    @GET(CATALOG_URL)
    Observable<ProductListDTO> getProducts(@Query("category") int categoryId,
                                           @Query("offset") int offset,
                                           @Query("limit") int limit);

    @GET(CATALOG_URL + "/search")
    Observable<ProductListDTO> search(@Query("query") String query,
                                      @Query("offset") int offset,
                                      @Query("limit") int limit);

    @GET(CATALOG_URL + "/{id}")
    Observable<ProductDTO> getProduct(@Path("id") int id);

    @GET(CATALOG_URL + "/{id}/reviews")
    Observable<ReviewListDTO> getReviews(@Path("id") int id);

    @GET(CATALOG_URL + "/{id}/specification")
    Observable<SpecificationDTO> getSpecification(@Path("id") int id);

    @GET(USER_URL + "/{id}")
    Observable<UserDTO> getUser(@Path("id") int userId);

    @GET(USER_URL + "/{id}/cart")
    Observable<ProductListDTO> getCart(@Path("id") int userId);

    @POST(USER_URL + "/{id}/cart/{product_id}")
    Observable<Response> addProductToCart(@Path("id") int userId,
                                          @Path("product_id") int productId);

    @DELETE(USER_URL + "/{id}/cart/{product_id}")
    Observable<Response> removeProductFromCart(@Path("id") int userId,
                                               @Path("product_id") int productId);

    @GET(USER_URL + "/{id}/orders")
    Observable<OrderListDTO> getOrders(@Path("id") int userId);

    @GET(USER_URL + "/{id}/orders/{order_id}")
    Observable<OrderDetailsDTO> getOrderDetails(@Path("id") int userId,
                                                @Path("order_id") int orderId);

    @POST(USER_URL + "/{id}/orders")
    Observable<Response> createOrder(@Path("id") int userId,
                                     @Body OrderDTO orderDTO);

    @GET(CATALOG_URL + "/categories")
    Observable<CategoryListDTO> getCategories();

    @GET(CATALOG_URL + "/categories/{id}")
    Observable<CategoryListDTO> getCategory(@Path("id") int catId);

    @GET(USER_URL + "/{id}/wishlist")
    Observable<ProductListDTO> getWishlist(@Path("id") int userId);

    @POST(USER_URL + "/{id}/wishlist/{product_id}")
    Observable<Response> addProductToWishlist(@Path("id") int userId,
                                              @Path("product_id") int productId);

    @DELETE(USER_URL + "/{id}/wishlist/{product_id}")
    Observable<Response> removeProductFromWishlist(@Path("id") int userId,
                                                   @Path("product_id") int productId);
}