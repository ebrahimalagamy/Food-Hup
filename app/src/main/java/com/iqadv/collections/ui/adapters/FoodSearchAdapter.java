package com.iqadv.collections.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iqadv.collections.R;
import com.iqadv.collections.databinding.ItemFoodSearchBinding;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.ui.search.SearchFragmentDirections;
import com.iqadv.collections.utlils.Constants;

import java.util.ArrayList;
import java.util.List;

public class FoodSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<FoodDetailsModel> foodDetailsModels;
    Context context;
    int first = 0;
    int second = 1;
    private NavController navController;


    public FoodSearchAdapter(List<FoodDetailsModel> foodDetailsModels, Context context) {
        this.foodDetailsModels = foodDetailsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view1 = inflater.inflate(R.layout.first_search_item, parent, false);

        if (viewType == first) {
            return new FoodSearchAdapter.firstViewHolder(view1);
        } else {
            return new FoodSearchAdapter.secondViewHolder(ItemFoodSearchBinding.inflate
                    (LayoutInflater.from(parent.getContext()), parent, false));

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FoodDetailsModel foodDetailsModel = foodDetailsModels.get(position);
        if (holder.getItemViewType() == 0) {
            firstViewHolder firstViewHolder = (firstViewHolder) holder;
            int size = foodDetailsModels.size() -1;
            firstViewHolder.textView.setText("Found " + size + " Results");
        } else {
            secondViewHolder secondViewHolder = (secondViewHolder) holder;
            secondViewHolder.binding.tvRestaurantName.setText(foodDetailsModel.getName());
            secondViewHolder.binding.tvFoodDescription.setText(foodDetailsModel.getDescription());
            secondViewHolder.binding.tvPrise.setText(foodDetailsModel.getPrice());
            secondViewHolder.binding.tvRate.setText(foodDetailsModel.getRating());
            secondViewHolder.binding.tvNumberOfRate.setText(foodDetailsModel.getNumberOfRatings());

            Glide.with(context).load(Constants.IMAGE_URL +
                    foodDetailsModel.getPic()).into(secondViewHolder.binding.rivRestaurant);

            secondViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navFoodDetails(view, foodDetailsModel);

                }
            });
        }


    }

    private void navFoodDetails(View view, FoodDetailsModel foodDetailsModel) {
        navController = Navigation.findNavController(view);
        NavDirections action = SearchFragmentDirections.actionSearchFragmentToFoodDetailsFragment(foodDetailsModel);
        navController.navigate(action);
    }

    @Override
    public int getItemCount() {
        return foodDetailsModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return first;
        } else
            return second;
    }
//    public void filterList(ArrayList<FoodDetailsModel> filterList){
//        foodDetailsModels = filterList;
//        notifyDataSetChanged();
//    }

    public class secondViewHolder extends RecyclerView.ViewHolder {
        ItemFoodSearchBinding binding;

        public secondViewHolder(@NonNull ItemFoodSearchBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

    }

    public class firstViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public firstViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvFireSearch);
        }
    }
}
