package com.iqadv.collections.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iqadv.collections.R;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.models.CartModel;
import com.iqadv.collections.utlils.Constants;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<FoodDetailsModel> list;

    public CartAdapter(Context context, List<FoodDetailsModel> list) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.list = list;

    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_cart, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        FoodDetailsModel cartModel = list.get(position);
        holder.cartName.setText(cartModel.getName());
        holder.cartDescription.setText(cartModel.getDescription());
        holder.cartPrise.setText(cartModel.getPrice());
        Glide.with(context).load(Constants.IMAGE_URL +
                cartModel.getPic()).into(holder.cartRiv);
//        holder.cartRiv.setImageResource(cartModel.getCart_image());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class viewHolder extends RecyclerView.ViewHolder {
        TextView cartName, cartDescription, cartPrise;
        RoundedImageView cartRiv;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cartName = itemView.findViewById(R.id.tv_cart_name);
            cartDescription = itemView.findViewById(R.id.tv_cart_description);
            cartPrise = itemView.findViewById(R.id.tv_cart_prise);
            cartRiv = itemView.findViewById(R.id.riv_cart);

        }
    }
}
