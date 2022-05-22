package com.iqadv.collections.ui.adapters.homeAdapters;

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
import com.iqadv.collections.db.RoomDB;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.ui.home.HomeFragmentDirections;
import com.iqadv.collections.utlils.Constants;

import java.util.List;


public class PopularItemsAdapter extends RecyclerView.Adapter<PopularItemsAdapter.viewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<FoodDetailsModel> list;
    private NavController navController;


    public PopularItemsAdapter(List<FoodDetailsModel> list, Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.list = list;

    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularItemsAdapter.viewHolder(ItemRestaurantCategoryBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        FoodDetailsModel restaurantCategoryModel = list.get(position);

        holder.binding.tvRestCategoryName.setText(restaurantCategoryModel.getName());
        holder.binding.tvRestCategoryDescription.setText(restaurantCategoryModel.getDescription());
        holder.binding.tvRestCategoryPrise.setText(restaurantCategoryModel.getPrice());
        holder.binding.tvRestCategoryRate.setText(restaurantCategoryModel.getRating());
        holder.binding.tvRestCategoryRateNumber.setText("(" + restaurantCategoryModel.getNumberOfRatings() + "+)");
        Glide.with(context).load(Constants.IMAGE_URL + restaurantCategoryModel.getPic()).into(holder.binding.rivRestCategory);

        holder.binding.icFavourite.frameFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomDB.getDatabase(context).getDao().insertFoodModel(restaurantCategoryModel);
                holder.binding.icFavourite.ivUnFavourite.setVisibility(View.GONE);
                holder.binding.icFavourite.ivFavourite.setVisibility(View.VISIBLE);
            }
        });

        holder.itemView.setOnClickListener(view -> {
            navFoodDetails(view, restaurantCategoryModel);
        });

    }

    private void navFoodDetails(View view, FoodDetailsModel restaurantCategoryModel) {
        navController = Navigation.findNavController(view);
        NavDirections action = HomeFragmentDirections.actionHomeFragmentToFoodDetailsFragment(restaurantCategoryModel);
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
