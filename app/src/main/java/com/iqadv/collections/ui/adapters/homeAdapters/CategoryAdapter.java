package com.iqadv.collections.ui.adapters.homeAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iqadv.collections.databinding.ItemRestaurantCategoryFavoriteBinding;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.utlils.Constants;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<FoodDetailsModel> list;

    public CategoryAdapter(Context context, List<FoodDetailsModel> list) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.list = list;

    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CategoryAdapter.viewHolder(ItemRestaurantCategoryFavoriteBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        FoodDetailsModel restaurantCategoryModel = list.get(position);

        holder.binding.tvCategoryNameFav.setText(restaurantCategoryModel.getName());
        holder.binding.tvCategoryDescriptionFav.setText(restaurantCategoryModel.getDescription());
        holder.binding.tvCategoryPriseFav.setText(restaurantCategoryModel.getPrice());
        holder.binding.tvCategoryRate.setText(restaurantCategoryModel.getRating());
        holder.binding.tvCategoryRateNumber.setText(restaurantCategoryModel.getNumberOfRatings());
        Glide.with(context).load(Constants.IMAGE_URL +
                restaurantCategoryModel.getPic()).into(holder.binding.rivCategoryFav);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        holder.binding.icFavourite.frameFavourite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        ItemRestaurantCategoryFavoriteBinding binding;

        public viewHolder(@NonNull ItemRestaurantCategoryFavoriteBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }
}