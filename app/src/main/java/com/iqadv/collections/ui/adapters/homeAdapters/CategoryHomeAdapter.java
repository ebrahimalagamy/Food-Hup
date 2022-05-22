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
import com.iqadv.collections.databinding.ItemCategoryBinding;
import com.iqadv.collections.model.CategoryModel;
import com.iqadv.collections.models.MainCategoryModel;
import com.iqadv.collections.ui.home.HomeFragmentDirections;

import java.util.List;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.cateViewHolder> {

    private List<CategoryModel> items;
    private Context context;
    private NavController navController;


    public CategoryHomeAdapter(List<CategoryModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public cateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new cateViewHolder(ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(cateViewHolder holder, int position) {
        CategoryModel categoryModel = items.get(position);
        holder.binding.tvCategoryName.setText(categoryModel.getCatName());
        Glide.with(context).load("https://direct-app.net/food/" + categoryModel.getCatPic()).into(holder.binding.civCategory);


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.binding.linearCategory.setBackgroundResource(R.drawable.category_shape_orange);
//                holder.binding.tvCategoryName.setTextColor(Color.WHITE);
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nitCategoryFragment(view, categoryModel);

            }
        });

    }

    private void nitCategoryFragment(View view, CategoryModel mainCategoryModel) {
        navController = Navigation.findNavController(view);
        NavDirections action = HomeFragmentDirections.actionHomeFragmentToCategoryFragment(mainCategoryModel);
        navController.navigate(action);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class cateViewHolder extends RecyclerView.ViewHolder {
        private ItemCategoryBinding binding;

        public cateViewHolder(@NonNull ItemCategoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
