package com.iqadv.collections.ui.map;

import androidx.lifecycle.MutableLiveData;

import com.iqadv.collections.model.RestaurantsResponse;
import com.iqadv.collections.repository.Repository;

public class MapViewModel {

    private Repository repository;
    private MutableLiveData<RestaurantsResponse> malCategoriesItem;

    public MapViewModel() {
        repository = new Repository();
    }

//    public MutableLiveData<ArrayList<RestaurantModel>> getRestaurant() {
//        if (mutableLiveData == null) {
//            mutableLiveData = repository.getRestaurant();
//        }
//        return mutableLiveData;
//    }

    public MutableLiveData<RestaurantsResponse> getRestaurants(String method) {
        if (malCategoriesItem == null) {
            malCategoriesItem = repository.getHomeFeatures(method);
        }
        return malCategoriesItem;
    }


}
