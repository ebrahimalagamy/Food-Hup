package com.iqadv.collections.model.restaurantDetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantResponse {

    @SerializedName("restaurant")
    private RestaurantModel restaurant;

    @SerializedName("items")
    private List<FoodDetailsModel> items;

    public RestaurantModel getRestaurant() {
        return restaurant;
    }

    public List<FoodDetailsModel> getItems() {
        return items;
    }
}