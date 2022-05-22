package com.iqadv.collections.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.iqadv.collections.databinding.FragmentCategoryBinding;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.ui.adapters.homeAdapters.CategoryAdapter;
import com.iqadv.collections.utlils.Constants;

import java.util.List;

public class CategoryFragment extends Fragment {
    private FragmentCategoryBinding binding;
    private CategoryViewModel categoryViewModel;
    private CategoryFragmentArgs args;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        categoryViewModel = new CategoryViewModel();
//        restaurantCategoryModelArrayList = new ArrayList<>();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        args = CategoryFragmentArgs.fromBundle(getArguments());
        bindCategoryRecycler();
        Glide.with(requireActivity()).load(
                Constants.IMAGE_URL + args.getCategoryModel().getCatPic()).into(binding.ivCategory);
        binding.tvCateName.setText(args.getCategoryModel().getCatName());


    }

    private void bindCategoryRecycler() {

        categoryViewModel.getCategoryMeals("foods",args.getCategoryModel().getId()).observe(requireActivity(), new Observer<List<FoodDetailsModel>>() {
            @Override
            public void onChanged(List<FoodDetailsModel> foodDetailsModelList) {
                binding.tvNumberOfTypes.setText(foodDetailsModelList.size()+" types of "+args.getCategoryModel().getCatName());
                CategoryAdapter categoryAdapter = new CategoryAdapter(requireActivity(), foodDetailsModelList);
                binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL, false));
                binding.recyclerViewCategory.setAdapter(categoryAdapter);

            }
        });
//        categoryViewModel.getRestaurantCategory().observe(requireActivity(), new Observer<ArrayList<RestaurantCategoryModel>>() {
//            @Override
//            public void onChanged(ArrayList<RestaurantCategoryModel> restaurantCategoryModels) {
//                restaurantCategoryModelArrayList.addAll(restaurantCategoryModels);
//
//
//            }
//        });
//        CategoryAdapter categoryAdapter = new CategoryAdapter(requireActivity(), restaurantCategoryModelArrayList);
//        binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getActivity(),
//                LinearLayoutManager.VERTICAL, false));
//        binding.recyclerViewCategory.setAdapter(categoryAdapter);
//
//    }
    }
}