package com.iqadv.collections.ui.favourite;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iqadv.collections.databinding.FragmentFavouriteFragemntBinding;
import com.iqadv.collections.db.RoomDB;
import com.iqadv.collections.ui.adapters.RestaurantFavouriteAdapter;
import com.iqadv.collections.ui.adapters.FoodFavouriteAdapter;

public class FavouriteFragment extends Fragment {
    RestaurantFavouriteAdapter restaurantFavouriteAdapter;
    FoodFavouriteAdapter foodFavouriteAdapter;
    private FragmentFavouriteFragemntBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteFragemntBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindToggle();
        toolBar();

        restaurantFavouriteAdapter = new RestaurantFavouriteAdapter(getActivity(), RoomDB.getDatabase(requireActivity()).getRestaurantDao().getAllFavouriteRestaurant());
        foodFavouriteAdapter = new FoodFavouriteAdapter(getActivity(),RoomDB.getDatabase(requireActivity()).getDao().getAllFavouriteFood());
        binding.recyclerViewFavourite.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        binding.recyclerViewFavourite.setAdapter(foodFavouriteAdapter);


    }

    private void toolBar() {
        binding.toolBarFavourite.tvScreenName.setVisibility(View.VISIBLE);
        binding.toolBarFavourite.tvScreenName.setText("Favorites");
        binding.toolBarFavourite.rivUser.setVisibility(View.VISIBLE);
    }

    private void bindToggle() {
        ColorStateList colorStateList = binding.toggleFavourite.item2.getTextColors();

        binding.toggleFavourite.select.setOnClickListener(view13 -> {

        });
        binding.toggleFavourite.item1.setOnClickListener(view1 -> {
            binding.toggleFavourite.select.animate().x(0F).setDuration(100);
            binding.toggleFavourite.item1.setTextColor(Color.WHITE);
            binding.toggleFavourite.item2.setTextColor(colorStateList);
            binding.recyclerViewFavourite.setAdapter(foodFavouriteAdapter);

        });
        binding.toggleFavourite.item2.setOnClickListener(view12 -> {
            binding.toggleFavourite.item1.setTextColor(colorStateList);
            binding.toggleFavourite.item2.setTextColor(Color.WHITE);
            int size = binding.toggleFavourite.item2.getWidth();
            binding.toggleFavourite.select.animate().x(size).setDuration(100);
            binding.recyclerViewFavourite.setAdapter(restaurantFavouriteAdapter);

        });
    }
}