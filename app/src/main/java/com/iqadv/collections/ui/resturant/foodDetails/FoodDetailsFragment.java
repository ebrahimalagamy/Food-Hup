package com.iqadv.collections.ui.resturant.foodDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentFoodDetailsBinding;
import com.iqadv.collections.db.RoomDB;
import com.iqadv.collections.utlils.Constants;


public class FoodDetailsFragment extends Fragment {
    int counterCart = 1;
    private FragmentFoodDetailsBinding binding;
    private FoodDetailsFragmentArgs args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFoodDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        args = FoodDetailsFragmentArgs.fromBundle(getArguments());

        bindToolBar();
        bindViews();
        bindButtonCart();

    }

    private void bindButtonCart() {
        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomDB.getDatabase(requireContext()).getCartFoodDao().insertCartFoodModel(args.getFoodDetails());
                Toast.makeText(requireActivity(), "Added To Cart", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void bindViews() {
        Glide.with(requireActivity()).load(
                Constants.IMAGE_URL + args.getFoodDetails().getPic()).into(binding.rivFoodCover);
        binding.tvFoodName.setText(args.getFoodDetails().getName());
        binding.tvFoodDescription.setText(args.getFoodDetails().getDescription());
        binding.tvFoodPrice.setText(args.getFoodDetails().getPrice());
        binding.tvRate.setText(args.getFoodDetails().getRating());
        binding.tvNumberRate.setText("(" + args.getFoodDetails().getNumberOfRatings() + "+)");

        binding.tvReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_foodDetailsFragment_to_reviewsFragment);
            }
        });

    }

    private void bindToolBar() {
        binding.fabMinus.setOnClickListener(view -> {
            if (!(counterCart == 1)) {
                counterCart--;
                binding.tvNumberOfItem.setText(counterCart + "");
            }

        });
        binding.fabPlus.setOnClickListener(view -> {
            counterCart++;
            binding.tvNumberOfItem.setText(counterCart + "");


        });
    }
}