package com.iqadv.collections.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.iqadv.collections.R;
import com.iqadv.collections.model.LoginModel;
import com.iqadv.collections.model.RestaurantsResponse;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.model.restaurantDetails.RestaurantModel;
import com.iqadv.collections.model.restaurantDetails.RestaurantResponse;
import com.iqadv.collections.models.AddressModel;
import com.iqadv.collections.models.OrderDetailsModel;
import com.iqadv.collections.models.RestaurantCategoryModel;
import com.iqadv.collections.models.UpcomingOderModel;
import com.iqadv.collections.webService.EndPoints;
import com.iqadv.collections.webService.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    EndPoints endPoints;

    public MutableLiveData<LoginModel> signIn(String email, String pass, String type, String method) {
        final MutableLiveData<LoginModel> mutableLiveData = new MutableLiveData<>();
        endPoints = RetrofitClient.getRetrofitClient().create(EndPoints.class);
        endPoints.loginRequest(email, pass, type, method).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.postValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.e("name", t.getMessage());
            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<ResponseBody> signUp(String method, String type,
                                                String email, String pass, String name, String mobile) {
        final MutableLiveData<ResponseBody> mutableLiveData = new MutableLiveData<>();
        endPoints = RetrofitClient.getRetrofitClient().create(EndPoints.class);
        endPoints.registerRequest(method, type, email, pass, name, mobile).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.e("res", response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("error", call.request().body().toString());
                Log.e("error", t.getLocalizedMessage());

            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<RestaurantsResponse> getHomeFeatures(String method) {
        final MutableLiveData<RestaurantsResponse> mutableLiveData = new MutableLiveData<>();
        endPoints = RetrofitClient.getRetrofitClient().create(EndPoints.class);
        endPoints.homeFeaturesRequest(method).enqueue(new Callback<RestaurantsResponse>() {
            @Override
            public void onResponse(Call<RestaurantsResponse> call, Response<RestaurantsResponse> response) {
                Log.e("respone", response.body().getCategories().get(1).getCatName());
                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<RestaurantsResponse> call, Throwable t) {
                Log.e("respone", t.getMessage());

            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<RestaurantResponse> getRestaurantDetails(String method, String id) {
        final MutableLiveData<RestaurantResponse> mutableLiveData = new MutableLiveData<>();
        endPoints = RetrofitClient.getRetrofitClient().create(EndPoints.class);
        endPoints.restaurantDetailsRequest(method, id).enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                    Log.e("details", response.body().getRestaurant().getName());
                }
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                Log.e("details", t.getMessage());


            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<List<FoodDetailsModel>> requestFoodSearch(String method, String keyword) {
        final MutableLiveData<List<FoodDetailsModel>> mutableLiveData = new MutableLiveData<>();

        endPoints = RetrofitClient.getRetrofitClient().create(EndPoints.class);
        endPoints.foodSearchRequest(method, keyword).enqueue(new Callback<List<FoodDetailsModel>>() {
            @Override
            public void onResponse(Call<List<FoodDetailsModel>> call, Response<List<FoodDetailsModel>> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<FoodDetailsModel>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<RestaurantModel>> requestRestaurantSearch(String method, String keyword) {
        final MutableLiveData<List<RestaurantModel>> mutableLiveData = new MutableLiveData<>();

        endPoints = RetrofitClient.getRetrofitClient().create(EndPoints.class);
        endPoints.restaurantSearchRequest(method, keyword).enqueue(new Callback<List<RestaurantModel>>() {
            @Override
            public void onResponse(Call<List<RestaurantModel>> call, Response<List<RestaurantModel>> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RestaurantModel>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<FoodDetailsModel>> requestCategoryMeals(String method, String id) {
        final MutableLiveData<List<FoodDetailsModel>> mutableLiveData = new MutableLiveData<>();

        endPoints = RetrofitClient.getRetrofitClient().create(EndPoints.class);
        endPoints.categoryMealsRequest(method, id).enqueue(new Callback<List<FoodDetailsModel>>() {
            @Override
            public void onResponse(Call<List<FoodDetailsModel>> call, Response<List<FoodDetailsModel>> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<FoodDetailsModel>> call, Throwable t) {
                Log.e("requestCategoryMeals", t.getMessage());

            }
        });
        return mutableLiveData;
    }


    //////

    public MutableLiveData<ArrayList<RestaurantCategoryModel>> getRestaurantCategory() {

        final MutableLiveData<ArrayList<RestaurantCategoryModel>> mutableLiveData = new MutableLiveData<>();
        ArrayList<RestaurantCategoryModel> restaurantCategoryModels = new ArrayList<>();

        restaurantCategoryModels.add(new RestaurantCategoryModel(1, "Chicken Hawaiian", "Chicken Hawaiian", "$12.20", "4.5",
                "20", R.drawable.mc2));
        restaurantCategoryModels.add(new RestaurantCategoryModel(2, "Red N Hot Pizza", "Chicken, Chili", "$10.35", "3.5",
                "12 ", R.drawable.mc2));
        restaurantCategoryModels.add(new RestaurantCategoryModel(1, "Chicken Hawaiian", "Chicken Hawaiian", "$12.20", "4.5",
                "20", R.drawable.mc2));
        restaurantCategoryModels.add(new RestaurantCategoryModel(2, "Red N Hot Pizza", "Chicken, Chili", "$10.35", "3.5",
                "12 ", R.drawable.mc2));

        mutableLiveData.setValue(restaurantCategoryModels);

        return mutableLiveData;
    }

    public MutableLiveData<ArrayList<AddressModel>> getAddresses() {

        final MutableLiveData<ArrayList<AddressModel>> mutableLiveData = new MutableLiveData<>();
        ArrayList<AddressModel> alAddresses = new ArrayList<>();

        alAddresses.add(new AddressModel(1, "Home", "01026241542", "cairo", "nasr city", "Deliver to 4102 Pretty View Lane"));

        alAddresses.add(new AddressModel(1, "office", "01026241542", "cairo", "nasr city", " cairo to 4102 Pretty View Lane"));

        alAddresses.add(new AddressModel(1, "house", "01026241542", "cairo", "nasr city", " zag to 4102 Pretty View Lane"));
        mutableLiveData.setValue(alAddresses);

        return mutableLiveData;

    }

    public MutableLiveData<ArrayList<UpcomingOderModel>> getMyOrders() {

        final MutableLiveData<ArrayList<UpcomingOderModel>> mutableLiveData = new MutableLiveData<>();
        ArrayList<UpcomingOderModel> alUpcomingOrder = new ArrayList<>();
        OrderDetailsModel orderDetailsModel = new OrderDetailsModel("30.300954", "31.755047", "12.5 km", "nasr city", "true", "12.15",
                "true", "12.30", "false", "12.35", "false", "12.45", "ibrahim", "12365DJFL", "01026241542", R.drawable.user2);

        alUpcomingOrder.add(new UpcomingOderModel(1, "20 Jun, 10:30", "6 items", "Starbucks", "35 min",
                "12.5$", R.drawable.rest_search_image_4, orderDetailsModel));


        mutableLiveData.setValue(alUpcomingOrder);

        return mutableLiveData;

    }


}
