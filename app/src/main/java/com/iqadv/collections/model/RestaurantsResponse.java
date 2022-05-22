package com.iqadv.collections.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.model.restaurantDetails.RestaurantModel;

import java.util.List;

public class RestaurantsResponse implements Parcelable {

    @SerializedName("foods")
    private List<FoodDetailsModel> foods;

    @SerializedName("restaurants")
    private List<RestaurantModel> restaurants;

    @SerializedName("categories")
    private List<CategoryModel> categories;


    protected RestaurantsResponse(Parcel in) {
        foods = in.createTypedArrayList(FoodDetailsModel.CREATOR);
        restaurants = in.createTypedArrayList(RestaurantModel.CREATOR);
    }

    public static final Creator<RestaurantsResponse> CREATOR = new Creator<RestaurantsResponse>() {
        @Override
        public RestaurantsResponse createFromParcel(Parcel in) {
            return new RestaurantsResponse(in);
        }

        @Override
        public RestaurantsResponse[] newArray(int size) {
            return new RestaurantsResponse[size];
        }
    };

    public List<FoodDetailsModel> getFoods() {
        return foods;
    }

    public List<RestaurantModel> getRestaurants() {
        return restaurants;
    }

    public List<CategoryModel> getCategories() {
        return categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(foods);
        parcel.writeTypedList(restaurants);
    }
}