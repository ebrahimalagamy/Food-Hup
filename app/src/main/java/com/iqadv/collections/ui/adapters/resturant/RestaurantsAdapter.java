package com.iqadv.collections.ui.adapters.resturant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iqadv.collections.databinding.ItemRestaurantBinding;
import com.iqadv.collections.db.RoomDB;
import com.iqadv.collections.model.restaurantDetails.RestaurantModel;
import com.iqadv.collections.ui.adapters.TagAdapter;
import com.iqadv.collections.ui.home.HomeFragmentDirections;
import com.iqadv.collections.utlils.Constants;

import java.util.Arrays;
import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.viewHolder> {
    private List<RestaurantModel> restaurantModels;
    private NavController navController;
    private Context context;
//    private OnItemsClickListener listener;


    public RestaurantsAdapter(List<RestaurantModel> restaurantModels, Context context) {
        this.restaurantModels = restaurantModels;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(ItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        RestaurantModel restaurantModel = restaurantModels.get(position);

        if (restaurantModel.getVerified().equals("1")) {
            holder.binding.ivVerified.setVisibility(View.VISIBLE);
        }
        String[] separated = restaurantModel.getTags().split("\\$");
        Log.e("sep", separated[0]);
        TagAdapter tagAdapter = new TagAdapter(context, Arrays.asList(separated));
        holder.binding.recyclerViewTags.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));
        holder.binding.recyclerViewTags.setAdapter(tagAdapter);

        holder.binding.tvRate.setText(restaurantModel.getRating());
        holder.binding.tvNumberRate.setText("(" + restaurantModel.getNumberOfRatings() + "+)");
        holder.binding.tvRestaurantName.setText(restaurantModel.getName());
        holder.binding.tvDeliveryCost.setText(restaurantModel.getDelivery());
        holder.binding.tvDeliverTime.setText(restaurantModel.getDeliveryTime());
        Glide.with(context).load(Constants.IMAGE_URL +
                restaurantModel.getCoverPhoto()).into(holder.binding.civUserName);

        for (int i = 0 ; i<RoomDB.getDatabase(context).getRestaurantDao().getAllFavouriteRestaurant().size(); i++){
            if (RoomDB.getDatabase(context).getRestaurantDao().getAllFavouriteRestaurant().get(i).getId().equals(restaurantModel.getId())){
                holder.binding.icFavourite.ivFavourite.setVisibility(View.VISIBLE);
                holder.binding.icFavourite.ivUnFavourite.setVisibility(View.GONE);

            }else {
                holder.binding.icFavourite.ivFavourite.setVisibility(View.GONE);
                holder.binding.icFavourite.ivUnFavourite.setVisibility(View.VISIBLE);

            }
        }

        holder.binding.icFavourite.frameFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(listener != null){
//                    listener.onItemClick();
//                }
//                RoomDB.getDatabase(context).getRestaurantDao().insertRestaurantModel(restaurantModel);
            }
        });

        // sent restaurant id to profileFragment to display details
        holder.itemView.setOnClickListener(view -> {
            navRestaurantDetails(view, restaurantModel);
        });


    }

    private void navRestaurantDetails(View view, RestaurantModel restaurantModel) {

        navController = Navigation.findNavController(view);
        NavDirections action = HomeFragmentDirections.actionHomeFragmentToResturantProfileFragment(restaurantModel);
        navController.navigate(action);
    }

    @Override
    public int getItemCount() {
        return restaurantModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ItemRestaurantBinding binding;

        public viewHolder(@NonNull ItemRestaurantBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }

//    public interface OnItemsClickListener{
//        void onItemClick();
//    }
//    public void setWhenClickListener(OnItemsClickListener listener){
//        this.listener = listener;
//    }
}
