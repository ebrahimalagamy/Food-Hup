package com.iqadv.collections.ui.adapters.homeAdapters;

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
import com.iqadv.collections.databinding.ItemResturentSearchBinding;
import com.iqadv.collections.model.restaurantDetails.RestaurantModel;
import com.iqadv.collections.ui.home.HomeFragmentDirections;
import com.iqadv.collections.ui.search.SearchFragmentDirections;
import com.iqadv.collections.utlils.Constants;

import java.util.List;

public class RestaurantSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RestaurantModel> restaurantItemSearchModels;
    private Context context;
    int first = 0;
    int second = 1;
    private NavController navController;



    public RestaurantSearchAdapter(List<RestaurantModel> restaurantItemSearchModels, Context context) {
        this.restaurantItemSearchModels = restaurantItemSearchModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view1 = inflater.inflate(R.layout.first_search_item, parent, false);

        if (viewType == first) {
            return new RestaurantSearchAdapter.firstViewHolder(view1);
        } else {
            return new RestaurantSearchAdapter.secondViewHolder(ItemResturentSearchBinding.inflate
                    (LayoutInflater.from(parent.getContext()), parent, false));

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RestaurantModel restaurantModel = restaurantItemSearchModels.get(position);
        if (holder.getItemViewType() == 0) {
            RestaurantSearchAdapter.firstViewHolder firstViewHolder = (RestaurantSearchAdapter.firstViewHolder) holder;
            int size = restaurantItemSearchModels.size() -1;
            firstViewHolder.textView.setText("Found " + size+ " Results");
        } else {
            RestaurantSearchAdapter.secondViewHolder secondViewHolder = (RestaurantSearchAdapter.secondViewHolder) holder;
            secondViewHolder.binding.tvRestaurantName.setText(restaurantModel.getName());
            secondViewHolder.binding.tvDeliveryCost.setText(restaurantModel.getDelivery());
            secondViewHolder.binding.tvDeliverTime.setText(restaurantModel.getDeliveryTime());

            Glide.with(context).load(Constants.IMAGE_URL +
                    restaurantModel.getPic()).into(secondViewHolder.binding.rivRestaurant);
            
            secondViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navRestaurant(view, restaurantModel);

                }
            });

        }

    }

    private void navRestaurant(View view, RestaurantModel restaurantModel) {
        navController = Navigation.findNavController(view);
        NavDirections action = SearchFragmentDirections.actionSearchFragmentToResturantProfileFragment(restaurantModel);
        navController.navigate(action);
    }

    @Override
    public int getItemCount() {
        return restaurantItemSearchModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return first;
        } else
            return second;
    }

    public class secondViewHolder extends RecyclerView.ViewHolder {
        ItemResturentSearchBinding binding;

        public secondViewHolder(@NonNull ItemResturentSearchBinding itemView) {
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
