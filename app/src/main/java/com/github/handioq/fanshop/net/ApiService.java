package com.github.handioq.fanshop.net;

import com.github.handioq.fanshop.model.dto.CategoryListDTO;
import com.github.handioq.fanshop.model.dto.OrderDetailsDTO;
import com.github.handioq.fanshop.model.dto.OrderListDTO;
import com.github.handioq.fanshop.model.dto.ProductIdDTO;
import com.github.handioq.fanshop.model.dto.ProductInfoDTO;
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
import java.util.Map;
import java.util.Set;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface ApiService {

    String LOGIN_URL = "/api/auth/login";
    String SIGNUP_URL = "/api/user/signup";
    String CATALOG_URL = "/api/catalog";
    String USER_URL = "/api/user";

    @POST(LOGIN_URL)
    Observable<AuthResponse> login(@Query("mail") String mail,
                                   @Query("password") String password);

    @POST(SIGNUP_URL)
    Observable<Response> signup(@Body RegisterDTO registerDTO);

    @GET(CATALOG_URL)
    Observable<ProductListDTO> getProducts(@Query("category") long categoryId,
                                           @Query("offset") int offset,
                                           @Query("limit") int limit);

    @FormUrlEncoded
    @POST("/api/search")
    Observable<ProductListDTO> search(@FieldMap Map<String, String> options,
                                      @Query("offset") int offset,
                                      @Query("limit") int limit);

    @GET(CATALOG_URL + "/{id}")
    Observable<ProductInfoDTO> getProduct(@Path("id") int id);

    @GET(CATALOG_URL + "/{id}/reviews")
    Observable<ReviewListDTO> getReviews(@Path("id") int id);

    @GET(CATALOG_URL + "/{id}/specification")
    Observable<SpecificationDTO> getSpecification(@Path("id") int id);

    @GET(USER_URL + "/{id}")
    Observable<UserDTO> getUser(@Path("id") int userId);

    @GET(USER_URL + "/{id}/cart")
    Observable<ProductListDTO> getCart(@Path("id") int userId);

    @PUT(USER_URL + "/{id}/cart/add/{product_id}")                   // change me on apiary!!!
    Observable<Response> addProductToCart(@Path("id") int userId,
                                          @Path("product_id") int productId);

    @PUT(USER_URL + "/{id}/cart/delete/{product_id}")                // change me on apiary!
    Observable<Response> removeProductFromCart(@Path("id") int userId,
                                               @Path("product_id") int productId);

    @GET(USER_URL + "/{id}/orders")
    Observable<OrderListDTO> getOrders(@Path("id") int userId);

    @GET(USER_URL + "/{id}/orders/{order_id}")
    Observable<OrderDetailsDTO> getOrderDetails(@Path("id") int userId,
                                                @Path("order_id") int orderId);

    @POST(USER_URL + "/{id}/orders")
    Observable<Response> createOrder(@Path("id") int userId,
                                     @Body List<ProductIdDTO> products); // change me on apiary!

    @PUT(USER_URL + "/{user_id}/orders/{order_id}")
    Observable<Response> paymentForOrder(@Path("user_id") int userId,
                                         @Path("order_id") int orderId);

    @POST(USER_URL + "/{id}/reviews/{product_id}")
    Observable<Response> addReview(@Path("id") int userId,
                                   @Path("product_id") int productId,
                                   @Body ReviewDTO reviewDTO);

    @GET("/api/categories")
    Observable<CategoryListDTO> getCategories();

    @GET("/api/categories/{id}")
    Observable<CategoryListDTO> getCategory(@Path("id") int catId);

    @GET(USER_URL + "/{id}/wishlist")
    Observable<ProductListDTO> getWishlist(@Path("id") int userId);

    @PUT(USER_URL + "/{id}/wishlist/add/{product_id}")                       // change me on apiary!
    Observable<Response> addProductToWishlist(@Path("id") int userId,
                                              @Path("product_id") int productId);

    @PUT(USER_URL + "/{id}/wishlist/delete/{product_id}")                    // change me on apiary!
    Observable<Response> removeProductFromWishlist(@Path("id") int userId,
                                                   @Path("product_id") int productId);
}