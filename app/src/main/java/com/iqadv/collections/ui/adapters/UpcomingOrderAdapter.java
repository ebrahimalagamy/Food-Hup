package com.iqadv.collections.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iqadv.collections.databinding.ItemUpcomingOrderBinding;
import com.iqadv.collections.models.UpcomingOderModel;

import java.util.ArrayList;
import java.util.List;


public class UpcomingOrderAdapter extends RecyclerView.Adapter<UpcomingOrderAdapter.viewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<UpcomingOderModel> list;

    public UpcomingOrderAdapter(Context context, ArrayList<UpcomingOderModel> list) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new viewHolder(ItemUpcomingOrderBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        UpcomingOderModel upcomingOderModel = list.get(position);
        holder.itemUpcomingOrderBinding.tvNumberOfOrder.setText(upcomingOderModel.getOrderNumber());
        holder.itemUpcomingOrderBinding.tvNumberOfItems.setText(upcomingOderModel.getNumberOfItem());
        holder.itemUpcomingOrderBinding.tvRestaurantUpcoming.setText(upcomingOderModel.getRestName());
        holder.itemUpcomingOrderBinding.tvTimeToArrive.setText(upcomingOderModel.getArrivalTime());
        holder.itemUpcomingOrderBinding.tvOrderStatus.setText(upcomingOderModel.getOrderStatus());
        holder.itemUpcomingOrderBinding.rivUpcoming.setImageResource(upcomingOderModel.getRestUpcomingImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        private ItemUpcomingOrderBinding itemUpcomingOrderBinding;

        public viewHolder(@NonNull ItemUpcomingOrderBinding itemView) {
            super(itemView.getRoot());
            this.itemUpcomingOrderBinding = itemView;
        }
    }
}
