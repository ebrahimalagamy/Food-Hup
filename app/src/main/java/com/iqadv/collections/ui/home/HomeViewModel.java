package com.iqadv.collections.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iqadv.collections.model.RestaurantsResponse;
import com.iqadv.collections.models.AddressModel;
import com.iqadv.collections.repository.Repository;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {


    private final Repository repository;
    private MutableLiveData<ArrayList<AddressModel>> malAddress;

    private MutableLiveData<RestaurantsResponse> malCategoriesItem;


    public HomeViewModel() {
        repository = new Repository();
    }

    public MutableLiveData<RestaurantsResponse> getHomeCategories(String method) {
        if (malCategoriesItem == null) {
            malCategoriesItem = repository.getHomeFeatures(method);
        }
        return malCategoriesItem;
    }

    public MutableLiveData<ArrayList<AddressModel>> getAddresses() {
        if (malAddress == null) {
            malAddress = repository.getAddresses();
        }
        return malAddress;
    }

}
