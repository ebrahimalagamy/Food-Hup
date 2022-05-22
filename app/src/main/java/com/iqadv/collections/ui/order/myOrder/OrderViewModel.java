package com.iqadv.collections.ui.order.myOrder;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iqadv.collections.models.UpcomingOderModel;
import com.iqadv.collections.repository.Repository;

import java.util.ArrayList;

public class OrderViewModel extends ViewModel {
    private final Repository repository;
    private MutableLiveData<ArrayList<UpcomingOderModel>> malUpcomingOder;

    public OrderViewModel() {
        repository = new Repository();
    }

    public MutableLiveData<ArrayList<UpcomingOderModel>> getUpcomingOder() {
        if (malUpcomingOder == null) {
            malUpcomingOder = repository.getMyOrders();
        }
        return malUpcomingOder;
    }
}
