package com.iqadv.collections.ui.resturant.foodDetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iqadv.collections.model.restaurantDetails.RestaurantResponse;
import com.iqadv.collections.repository.Repository;

public class FoodDetailsViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<RestaurantResponse> malRestaurantDetailsModel;


    public FoodDetailsViewModel() {
        repository = new Repository();
    }

}
