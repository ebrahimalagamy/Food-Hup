package com.iqadv.collections.ui.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentPaymentBinding;
import com.iqadv.collections.models.AddressModel;
import com.iqadv.collections.ui.home.HomeViewModel;

import java.util.ArrayList;

public class PaymentFragment extends Fragment {

    PaymentFragmentArgs paymentFragmentArgs;
    HomeViewModel homeViewModel;
    ArrayList<AddressModel> addressModelss;
    private FragmentPaymentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        homeViewModel = new HomeViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        bindToolBar();
//        homeViewModel.getAddresses().observe(requireActivity(), new Observer<ArrayList<AddressModel>>() {
//            @Override
//            public void onChanged(ArrayList<AddressModel> addressModels) {
//                addressModelss.addAll(addressModels);
//            }
//        });

        paymentFragmentArgs = paymentFragmentArgs.fromBundle(getArguments());

        if (!(paymentFragmentArgs.getAddressModel() == null)) {
            binding.tvLocationName.setText(paymentFragmentArgs.getAddressModel().getFullName());

            binding.tvMobileNumber.setText(paymentFragmentArgs.getAddressModel().getMobileNumber());
        }


    }

    private void bindView() {
        binding.ivSelectAddress.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.selectAddressFragment));
        binding.ivAddPayment.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_paymentFragment_to_addNewPaymentFragment));

        binding.btnSaveNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireActivity(), "Order Confirmed", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_paymentFragment_to_myOrderFragment);
            }
        });
    }

    private void bindToolBar(){
        binding.toolBarPayment.tvScreenName.setVisibility(View.VISIBLE);
        binding.toolBarPayment.tvScreenName.setText("Payment");
    }
}