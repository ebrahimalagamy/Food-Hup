package com.iqadv.collections.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iqadv.collections.databinding.ItemAddressBinding;
import com.iqadv.collections.models.AddressModel;

import java.util.List;


public class DeliveryAddressAdapter extends RecyclerView.Adapter<DeliveryAddressAdapter.viewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<AddressModel> list;

    public DeliveryAddressAdapter(Context context, List<AddressModel> list) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.list = list;

    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new DeliveryAddressAdapter.viewHolder(ItemAddressBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        AddressModel restaurantModel = list.get(position);
        holder.binding.tvAddressName.setText(restaurantModel.getFullName());
        holder.binding.tvAddressNumber.setText(restaurantModel.getMobileNumber());
        holder.binding.tvStreet.setText(restaurantModel.getStreet());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        private ItemAddressBinding binding;

        public viewHolder(@NonNull ItemAddressBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
