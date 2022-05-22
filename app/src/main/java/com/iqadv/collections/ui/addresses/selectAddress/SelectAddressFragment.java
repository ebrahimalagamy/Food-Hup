package com.iqadv.collections.ui.addresses.selectAddress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iqadv.collections.databinding.FragmentSelectedAddressBinding;
import com.iqadv.collections.models.AddressModel;
import com.iqadv.collections.ui.adapters.SelectAddressAdapter;
import com.iqadv.collections.ui.home.HomeViewModel;

import java.util.ArrayList;

public class SelectAddressFragment extends Fragment {
    ArrayList<AddressModel> arrayList;
    HomeViewModel homeViewModel;
    private FragmentSelectedAddressBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectedAddressBinding.inflate(inflater, container, false);
        homeViewModel = new HomeViewModel();
        arrayList = new ArrayList<>();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindToolBar();
        bindViews();

        homeViewModel.getAddresses().observe(requireActivity(), addressModels ->
                arrayList.addAll(addressModels));

        SelectAddressAdapter selectAddressAdapter = new SelectAddressAdapter(getActivity(), arrayList);
        binding.recyclerViewAddress.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        binding.recyclerViewAddress.setAdapter(selectAddressAdapter);


    }

    private void bindViews() {

    }

    private void bindToolBar() {
        binding.toolBarDeliveryAddress.tvScreenName.setVisibility(View.VISIBLE);
        binding.toolBarDeliveryAddress.tvScreenName.setText("Select Address");
    }
}