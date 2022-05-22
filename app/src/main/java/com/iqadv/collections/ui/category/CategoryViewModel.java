package com.iqadv.collections.ui.category;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.repository.Repository;

import java.util.List;

public class CategoryViewModel extends ViewModel {


    private final Repository repository;
    private MutableLiveData<List<FoodDetailsModel>> malFoodDetailsModel;

    public CategoryViewModel() {
        repository = new Repository();
    }

    public MutableLiveData<List<FoodDetailsModel>> getCategoryMeals(String method, String id) {
        if (malFoodDetailsModel == null) {
            malFoodDetailsModel = repository.requestCategoryMeals(method, id);
        }
        return malFoodDetailsModel;
    }

}
