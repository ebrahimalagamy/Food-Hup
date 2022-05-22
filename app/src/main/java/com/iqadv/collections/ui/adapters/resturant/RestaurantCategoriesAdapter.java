package com.iqadv.collections.ui.adapters.resturant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iqadv.collections.databinding.ItemRestaurantCategoryBinding;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.ui.resturant.resturantProfile.ResturantProfileFragmentDirections;
import com.iqadv.collections.utlils.Constants;

import java.util.List;


public class RestaurantCategoriesAdapter extends RecyclerView.Adapter<RestaurantCategoriesAdapter.viewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<FoodDetailsModel> list;
    private NavController navController;


    public RestaurantCategoriesAdapter(List<FoodDetailsModel> list, Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.list = list;

    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestaurantCategoriesAdapter.viewHolder(ItemRestaurantCategoryBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        FoodDetailsModel foodDetailsModel = list.get(position);

        holder.binding.tvRestCategoryName.setText(foodDetailsModel.getName());
        holder.binding.tvRestCategoryDescription.setText(foodDetailsModel.getDescription());
        holder.binding.tvRestCategoryPrise.setText(foodDetailsModel.getPrice());
        holder.binding.tvRestCategoryRate.setText(foodDetailsModel.getRating());
        holder.binding.tvRestCategoryRateNumber.setText(foodDetailsModel.getNumberOfRatings());

        Glide.with(context).load(Constants.IMAGE_URL +
                foodDetailsModel.getPic()).into(holder.binding.rivRestCategory);

        holder.itemView.setOnClickListener(view -> {
            NavFoodDetails(view, foodDetailsModel);
        });

    }

    private void NavFoodDetails(View view, FoodDetailsModel foodDetailsModel) {
        navController = Navigation.findNavController(view);
        NavDirections action = ResturantProfileFragmentDirections.actionResturantProfileFragmentToFoodDetailsFragment(foodDetailsModel);
        navController.navigate(action);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        ItemRestaurantCategoryBinding binding;

        public viewHolder(@NonNull ItemRestaurantCategoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;


        }
    }
}
