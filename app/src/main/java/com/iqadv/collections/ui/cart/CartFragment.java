package com.iqadv.collections.ui.cart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentCartBinding;
import com.iqadv.collections.db.RoomDB;
import com.iqadv.collections.ui.adapters.CartAdapter;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    private NavController navController;
    private FragmentCartBinding binding;
    private CartAdapter cartAdapter;
    int priseCounter;
    Double prise;
    ArrayList<String> priseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbarViews();
        priseList = new ArrayList<>();

        CartAdapter cartAdapter = new CartAdapter(getActivity(), RoomDB.getDatabase(requireActivity()).getDao().getAllFavouriteFood());
        binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));

        binding.recyclerViewCart.setAdapter(cartAdapter);

        for (int i = 0; i < RoomDB.getDatabase(requireActivity()).getDao().getAllFavouriteFood().size(); i++) {
            priseList.add(RoomDB.getDatabase(requireActivity()).getDao().getAllFavouriteFood().get(i).getPrice());
        }

        for (int i = 0; i < priseList.size(); i++) {
            prise = Double.valueOf(priseList.get(i));
            Log.e("prise",prise.toString());


        }
    }


    private void toolbarViews() {
        binding.include3.tvScreenName.setVisibility(View.VISIBLE);
        binding.include3.tvScreenName.setText("Cart");
        binding.include3.ivCart.setVisibility(View.VISIBLE);

        binding.include3.ivIconBack.setOnClickListener(view -> {
//                navController.navigate(R.id.action_cartFragment_to_homeFragment);
        });
        binding.btnCheckout.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_cartFragment_to_selectAddressFragment));
    }
}