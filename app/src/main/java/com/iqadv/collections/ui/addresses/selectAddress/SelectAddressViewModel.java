package com.iqadv.collections.ui.addresses.selectAddress;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iqadv.collections.models.AddressModel;
import com.iqadv.collections.repository.Repository;

import java.util.ArrayList;

public class SelectAddressViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<ArrayList<AddressModel>> malAddress;

    public MutableLiveData<ArrayList<AddressModel>> getAddresses() {
        if (malAddress == null) {
            malAddress = repository.getAddresses();
        }
        return malAddress;
    }
}
