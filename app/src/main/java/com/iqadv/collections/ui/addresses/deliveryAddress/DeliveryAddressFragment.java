package com.iqadv.collections.ui.addresses.deliveryAddress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentDeliveryAddressBinding;
import com.iqadv.collections.models.AddressModel;
import com.iqadv.collections.ui.adapters.DeliveryAddressAdapter;
import com.iqadv.collections.ui.home.HomeViewModel;

import java.util.ArrayList;

public class DeliveryAddressFragment extends Fragment {
    ArrayList<AddressModel> arrayList;
    HomeViewModel homeViewModel;
    private FragmentDeliveryAddressBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliveryAddressBinding.inflate(inflater, container, false);
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

        DeliveryAddressAdapter deliveryAddressAdapter = new DeliveryAddressAdapter(getActivity(), arrayList);
        binding.recyclerViewAddress.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        binding.recyclerViewAddress.setAdapter(deliveryAddressAdapter);
    }

    private void bindViews() {

        binding.btnAddNewAddress.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.addNewAddressFragment));

    }

    private void bindToolBar() {
        binding.toolBarDeliveryAddress.tvScreenName.setVisibility(View.VISIBLE);
        binding.toolBarDeliveryAddress.tvScreenName.setText("Delivery Address");
    }
}