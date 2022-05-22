package com.iqadv.collections.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.iqadv.collections.databinding.ItemAddressBinding;
import com.iqadv.collections.models.AddressModel;
import com.iqadv.collections.ui.addresses.selectAddress.SelectAddressFragmentDirections;

import java.util.List;


public class SelectAddressAdapter extends RecyclerView.Adapter<SelectAddressAdapter.viewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<AddressModel> list;
    private NavController navController;


    public SelectAddressAdapter(Context context, List<AddressModel> list) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.list = list;

    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new SelectAddressAdapter.viewHolder(ItemAddressBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        AddressModel restaurantModel = list.get(position);
        holder.binding.tvAddressName.setText(restaurantModel.getFullName());
        holder.binding.tvAddressNumber.setText(restaurantModel.getMobileNumber());
        holder.binding.tvStreet.setText(restaurantModel.getStreet());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nitCategoryFragment(view, restaurantModel);
            }
        });

    }

    private void nitCategoryFragment(View view, AddressModel restaurantModel) {
        navController = Navigation.findNavController(view);
        NavDirections action = SelectAddressFragmentDirections.actionDeliveryAddressFragmentToPaymentFragment(restaurantModel);
        navController.navigate(action);
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
