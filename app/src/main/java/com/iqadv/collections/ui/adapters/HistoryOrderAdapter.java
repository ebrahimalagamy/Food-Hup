package com.iqadv.collections.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iqadv.collections.databinding.ItemHistoryOrderBinding;
import com.iqadv.collections.models.HistoryOrderModel;

import java.util.ArrayList;
import java.util.List;


public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.viewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<HistoryOrderModel> list;

    public HistoryOrderAdapter(Context context, ArrayList<HistoryOrderModel> list) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new viewHolder(ItemHistoryOrderBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        HistoryOrderModel historyOrderModel = list.get(position);
        holder.itemHistoryOrderBinding.tvDateHistory.setText(historyOrderModel.getDate());
        holder.itemHistoryOrderBinding.tvNumberOfItemsHistory.setText(historyOrderModel.getNumberOfItem());
        holder.itemHistoryOrderBinding.tvHistoryPrise.setText(historyOrderModel.getHistoryPrise());
        holder.itemHistoryOrderBinding.tvRestNameHistory.setText(historyOrderModel.getRestName());
        holder.itemHistoryOrderBinding.tvOrderHistoryStatus.setText(historyOrderModel.getHistoryStatus());
        holder.itemHistoryOrderBinding.rivHistory.setImageResource(historyOrderModel.getRestHistoryImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        private ItemHistoryOrderBinding itemHistoryOrderBinding;

        public viewHolder(@NonNull ItemHistoryOrderBinding itemView) {
            super(itemView.getRoot());
            this.itemHistoryOrderBinding = itemView;
        }
    }
}
