package com.iqadv.collections.webService;

import com.iqadv.collections.model.LoginModel;
import com.iqadv.collections.model.RestaurantsResponse;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.model.restaurantDetails.RestaurantModel;
import com.iqadv.collections.model.restaurantDetails.RestaurantResponse;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EndPoints {

    @FormUrlEncoded
    @POST("/food/serv.php")
    Call<LoginModel> loginRequest(@Field("email") String email,
                                  @Field("pass") String password,
                                  @Field("type") String type,
                                  @Field("method") String method);

    @FormUrlEncoded
    @POST("/food/serv.php")
    Call<ResponseBody> registerRequest(@Field("method") String method,
                                       @Field("type") String type,
                                       @Field("email") String email,
                                       @Field("pass") String password,
                                       @Field("name") String name,
                                       @Field("mobile") String mobile);


    @FormUrlEncoded
    @POST("/food/serv.php")
    Call<RestaurantsResponse> homeFeaturesRequest(@Field("method") String method);

    @FormUrlEncoded
    @POST("/food/serv.php")
    Call<RestaurantResponse> restaurantDetailsRequest(@Field("method") String method,
                                                      @Field("restaurant_id") String food_id);

    @FormUrlEncoded
    @POST("/food/serv.php")
    Call<List<FoodDetailsModel>> foodSearchRequest(@Field("method") String method,
                                                   @Field("keyword") String searchKeyWord);

    @FormUrlEncoded
    @POST("/food/serv.php")
    Call<List<RestaurantModel>> restaurantSearchRequest(@Field("method") String method,
                                                        @Field("keyword") String searchKeyWord);

    @FormUrlEncoded
    @POST("/food/serv.php")
    Call<List<FoodDetailsModel>> categoryMealsRequest(@Field("method") String method,
                                                      @Field("cat_id") String cat_id);

}
