package com.iqadv.collections.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iqadv.collections.R;
import com.iqadv.collections.model.restaurantDetails.RestaurantModel;
import com.iqadv.collections.utlils.Constants;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;


public class RestaurantFavouriteAdapter extends RecyclerView.Adapter<RestaurantFavouriteAdapter.viewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<RestaurantModel> list;
    private NavController navController;

    public RestaurantFavouriteAdapter(Context context, List<RestaurantModel> list) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.list = list;

    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_restaurant_favourite, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        RestaurantModel restaurantModel = list.get(position);
        holder.rate.setText(restaurantModel.getRating());
        holder.rateNumber.setText(restaurantModel.getNumberOfRatings());
        holder.restaurantName.setText(restaurantModel.getName());
        holder.deliveryCost.setText(restaurantModel.getDelivery());
        holder.deliveryTime.setText(restaurantModel.getDeliveryTime());
        Glide.with(context).load(Constants.IMAGE_URL +
                restaurantModel.getCoverPhoto()).into(holder.rivRestaurant);

    }

//    private void nitCategoryFragment(View view, RestaurantModel restaurantModel) {
//        navController = Navigation.findNavController(view);
//        NavDirections action = HomeFragmentDirections.actionHomeFragmentToResturantProfileFragment(restaurantModel);
//        navController.navigate(action);
//
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView rate, rateNumber, restaurantName, deliveryCost, deliveryTime,
                restCategory1, restCategory2, restCategory3;

        RoundedImageView rivRestaurant;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            rate = itemView.findViewById(R.id.tv_rate);
            rateNumber = itemView.findViewById(R.id.tv_number_rate);
            restaurantName = itemView.findViewById(R.id.tv_restaurant_name);
            deliveryCost = itemView.findViewById(R.id.tv_delivery_cost);
            deliveryTime = itemView.findViewById(R.id.tv_deliver_time);
            restCategory1 = itemView.findViewById(R.id.tv_category_1);
            restCategory2 = itemView.findViewById(R.id.tv_category_2);
            restCategory3 = itemView.findViewById(R.id.tv_category_3);
            rivRestaurant = itemView.findViewById(R.id.riv_restaurant);

        }
    }
}
