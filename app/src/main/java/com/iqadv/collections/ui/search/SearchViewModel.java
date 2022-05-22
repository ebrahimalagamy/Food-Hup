package com.iqadv.collections.ui.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iqadv.collections.model.RestaurantsResponse;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.model.restaurantDetails.RestaurantModel;
import com.iqadv.collections.repository.Repository;

import java.util.List;

public class SearchViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<List<FoodDetailsModel>> mldFoodSearchModel;
    private MutableLiveData<List<RestaurantModel>> mldRestaurantSearchModel;
    private MutableLiveData<RestaurantsResponse> malCategoriesItem;

    public SearchViewModel() {
        repository = new Repository();
    }

    public MutableLiveData<List<FoodDetailsModel>> getFoodSearch(String method, String keyword) {
        if (mldFoodSearchModel == null) {
            mldFoodSearchModel = repository.requestFoodSearch(method, keyword);
        }
        return mldFoodSearchModel;

    }

    public MutableLiveData<List<RestaurantModel>> getRestaurantSearch(String method, String keyword) {
        if (mldRestaurantSearchModel == null) {
            mldRestaurantSearchModel = repository.requestRestaurantSearch(method, keyword);
        }
        return mldRestaurantSearchModel;

    }

    public MutableLiveData<RestaurantsResponse> getHomeCategories(String method) {
        if (malCategoriesItem == null) {
            malCategoriesItem = repository.getHomeFeatures(method);
        }
        return malCategoriesItem;
    }

}
