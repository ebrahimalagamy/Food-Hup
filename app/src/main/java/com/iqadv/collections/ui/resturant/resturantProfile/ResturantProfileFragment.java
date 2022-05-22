package com.iqadv.collections.ui.resturant.resturantProfile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentResturantProfileBinding;
import com.iqadv.collections.ui.adapters.TagAdapter;
import com.iqadv.collections.ui.adapters.resturant.RestaurantCategoriesAdapter;
import com.iqadv.collections.utlils.Constants;
import com.iqadv.collections.utlils.LoadingDialog;

import java.util.Arrays;

public class ResturantProfileFragment extends Fragment {
    private FragmentResturantProfileBinding binding;
    private ResturantProfileFragmentArgs args;
    private RestaurantDetailsViewModel restaurantDetailsViewModel;
    private LoadingDialog loadingDialog;
    private int numberOfItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResturantProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restaurantDetailsViewModel = new RestaurantDetailsViewModel();
        loadingDialog = new LoadingDialog(requireActivity());
//        loadingDialog.startLoading();

        args = ResturantProfileFragmentArgs.fromBundle(getArguments());
        bindViews();

    }

    private void bindViews() {
        binding.tvRestName.setText(args.getRestaurantDetails().getName());
        binding.tvRate.setText(args.getRestaurantDetails().getRating());
        binding.tvNumberRate.setText("("+args.getRestaurantDetails().getNumberOfRatings()+"+)");
        binding.tvDeliverTime.setText(args.getRestaurantDetails().getDeliveryTime());
        binding.tvDeliveryCost.setText(args.getRestaurantDetails().getDelivery());
        Glide.with(requireActivity()).load(
                Constants.IMAGE_URL + args.getRestaurantDetails().getPic()).into(binding.civRestProfile);
        Glide.with(requireActivity()).load(
                Constants.IMAGE_URL + args.getRestaurantDetails().getCoverPhoto()).into(binding.rivRestaurantCover);

        String[] separated = args.getRestaurantDetails().getTags().split("\\$");
        Log.e("sep", separated[0]);
        TagAdapter tagAdapter = new TagAdapter(requireActivity(), Arrays.asList(separated));
        binding.recyclerViewTags.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewTags.setAdapter(tagAdapter);

        restaurantDetailsViewModel.getRestaurantDetails("restaurant", args.getRestaurantDetails().getId()).observe(requireActivity(), restaurantResponse ->
        {
            binding.tvNumberOfItems.setText(restaurantResponse.getItems().size()+"");

            RestaurantCategoriesAdapter restaurantCategoriesAdapter = new RestaurantCategoriesAdapter(restaurantResponse.getItems(), requireActivity());
            binding.recyclerViewRestaurantItems.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL, false));
            binding.recyclerViewRestaurantItems.setAdapter(restaurantCategoriesAdapter);
        });


        binding.tvReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_resturantProfileFragment_to_reviewsFragment);
            }
        });

//            loadingDialog.stopLoading();
    }


}