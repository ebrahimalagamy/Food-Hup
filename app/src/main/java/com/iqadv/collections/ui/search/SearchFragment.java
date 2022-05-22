package com.iqadv.collections.ui.search;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentSearchBinding;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.model.restaurantDetails.RestaurantModel;
import com.iqadv.collections.ui.adapters.FoodSearchAdapter;
import com.iqadv.collections.ui.adapters.homeAdapters.RestaurantSearchAdapter;
import com.iqadv.collections.utlils.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private FoodSearchAdapter foodSearchAdapter;
    private RestaurantSearchAdapter restaurantSearchAdapter;
    private SearchViewModel searchViewModel;
    private String method;
    private ArrayList<RestaurantModel> restaurantModelArrayList;
    private ArrayList<FoodDetailsModel> foodDetailsModelArrayList;
    private LoadingDialog loadingDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchViewModel = new SearchViewModel();
        restaurantModelArrayList = new ArrayList<>();
        foodDetailsModelArrayList = new ArrayList<>();
        loadingDialog = new LoadingDialog(requireActivity());
        loadingDialog.startLoading();
        method = "search_food";

        toolBar();
        bindView();
        bindSearch();
        bindToggle();

        searchViewModel.getFoodSearch("search_food", "").observe(requireActivity(), new Observer<List<FoodDetailsModel>>() {
            @Override
            public void onChanged(List<FoodDetailsModel> foodDetailsModelList) {
                foodDetailsModelArrayList.clear();
                for (int i = 0; i < foodDetailsModelList.size(); i++) {
                    foodDetailsModelArrayList.add(foodDetailsModelList.get(i));
                    if (i == 0)
                        foodDetailsModelArrayList.add(foodDetailsModelList.get(i));
                }
                binding.recyclerViewSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                foodSearchAdapter = new FoodSearchAdapter(foodDetailsModelArrayList, getActivity());
                binding.recyclerViewSearch.setAdapter(foodSearchAdapter);
                loadingDialog.stopLoading();
            }
        });
    }

    private void bindToggle() {
        ColorStateList colorStateList = binding.toggle.item2.getTextColors();
        binding.toggle.item1.setOnClickListener(view1 -> {
            method = "search_food";
            loadingDialog.startLoading();
            searchViewModel.getFoodSearch("search_food", "").observe(requireActivity(), new Observer<List<FoodDetailsModel>>() {
                @Override
                public void onChanged(List<FoodDetailsModel> foodDetailsModelList) {
                    foodDetailsModelArrayList.clear();
                    for (int i = 0; i < foodDetailsModelList.size(); i++) {
                        foodDetailsModelArrayList.add(foodDetailsModelList.get(i));
                        if (i == 0)
                            foodDetailsModelArrayList.add(foodDetailsModelList.get(i));
                    }
                    binding.recyclerViewSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    foodSearchAdapter = new FoodSearchAdapter(foodDetailsModelArrayList, getActivity());
                    binding.recyclerViewSearch.setAdapter(foodSearchAdapter);
                    loadingDialog.stopLoading();
                }
            });

            binding.toggle.select.animate().x(0F).setDuration(100);
            binding.toggle.item1.setTextColor(Color.WHITE);
            binding.toggle.item2.setTextColor(colorStateList);
        });

        binding.toggle.item2.setOnClickListener(view12 -> {
            method = "search_restaurant";
            loadingDialog.startLoading();

            searchViewModel.getRestaurantSearch("search_restaurant", "").observe(requireActivity(), new Observer<List<RestaurantModel>>() {
                @Override
                public void onChanged(List<RestaurantModel> restaurantModels) {
                    restaurantModelArrayList.clear();

                    for (int i = 0; i < restaurantModels.size(); i++) {
                        restaurantModelArrayList.add(restaurantModels.get(i));
                        if (i == 0)
                            restaurantModelArrayList.add(restaurantModels.get(i));
                    }
                    binding.recyclerViewSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    restaurantSearchAdapter = new RestaurantSearchAdapter(restaurantModelArrayList, getActivity());
                    binding.recyclerViewSearch.setAdapter(restaurantSearchAdapter);
                    loadingDialog.stopLoading();

                }
            });
            binding.toggle.item1.setTextColor(colorStateList);
            binding.toggle.item2.setTextColor(Color.WHITE);
            int size = binding.toggle.item2.getWidth();
            binding.toggle.select.animate().x(size).setDuration(100);
        });
    }

    private void bindSearch() {

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("editable",editable.toString());
                if (method.equals("search_food"))
                {
                    searchViewModel.getFoodSearch(method, editable.toString()).observe(requireActivity(), new Observer<List<FoodDetailsModel>>() {
                        @Override
                        public void onChanged(List<FoodDetailsModel> foodDetailsModelList) {
                            foodDetailsModelArrayList.clear();
                            for (int i = 0; i < foodDetailsModelList.size(); i++) {
                                foodDetailsModelArrayList.add(foodDetailsModelList.get(i));
                                if (i == 0)
                                    foodDetailsModelArrayList.add(foodDetailsModelList.get(i));
                            }
                            binding.recyclerViewSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                            foodSearchAdapter = new FoodSearchAdapter(foodDetailsModelList, getActivity());
                            binding.recyclerViewSearch.setAdapter(foodSearchAdapter);
                        }
                    });
                } else if (method.equals("search_restaurant")) {
                    searchViewModel.getRestaurantSearch(method, editable.toString()).observe(requireActivity(), new Observer<List<RestaurantModel>>() {
                        @Override
                        public void onChanged(List<RestaurantModel> restaurantModels) {
                            restaurantModelArrayList.clear();
                            for (int i = 0; i < restaurantModels.size(); i++) {
                                restaurantModelArrayList.add(restaurantModels.get(i));
                                if (i == 0)
                                    restaurantModelArrayList.add(restaurantModels.get(i));
                            }
                            binding.recyclerViewSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                            restaurantSearchAdapter = new RestaurantSearchAdapter(restaurantModels, getActivity());
                            binding.recyclerViewSearch.setAdapter(restaurantSearchAdapter);

                        }
                    });
                }
            }
        });
    }

    private void filter(String toString) {
        if (method.equals("search_food")) {
            searchViewModel.getFoodSearch(method, toString).observe(requireActivity(), new Observer<List<FoodDetailsModel>>() {
                @Override
                public void onChanged(List<FoodDetailsModel> foodDetailsModelList) {
                    for (int i = 0; i < foodDetailsModelList.size(); i++) {
                        foodDetailsModelArrayList.add(foodDetailsModelList.get(i));
                        if (i == 0)
                            foodDetailsModelArrayList.add(foodDetailsModelList.get(i));

                        binding.recyclerViewSearch.setLayoutManager(new StaggeredGridLayoutManager(2,
                                StaggeredGridLayoutManager.VERTICAL));
                        foodSearchAdapter = new FoodSearchAdapter(foodDetailsModelArrayList, getActivity());
                        binding.recyclerViewSearch.setAdapter(foodSearchAdapter);

                    }
                }
            });
        }
    }

    private void bindView() {
        binding.ivSearchFilter.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.filterFragment));
    }

    private void toolBar() {
        binding.include2.tvScreenName.setVisibility(View.VISIBLE);
        binding.include2.tvScreenName.setText(R.string.search_food);
        binding.include2.rivUser.setVisibility(View.VISIBLE);
        binding.include2.rivUser.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.profileFragment));

    }
}