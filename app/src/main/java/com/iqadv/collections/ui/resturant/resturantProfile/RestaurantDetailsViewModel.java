package com.iqadv.collections.ui.resturant.resturantProfile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iqadv.collections.model.restaurantDetails.RestaurantResponse;
import com.iqadv.collections.repository.Repository;

public class RestaurantDetailsViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<RestaurantResponse> malRestaurantDetailsModel;

    public RestaurantDetailsViewModel() {
        repository = new Repository();
    }

    public MutableLiveData<RestaurantResponse> getRestaurantDetails(String method, String id) {
        if (malRestaurantDetailsModel == null) {
            malRestaurantDetailsModel = repository.getRestaurantDetails(method, id);
        }
        return malRestaurantDetailsModel;
    }

}
