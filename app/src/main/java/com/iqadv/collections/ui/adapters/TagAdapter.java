package com.iqadv.collections.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iqadv.collections.databinding.ItemTagBinding;

import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.viewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<String> list;

    public TagAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TagAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(ItemTagBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TagAdapter.viewHolder holder, int position) {

        String tage = list.get(position);
        holder.binding.tvTage.setText(tage);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ItemTagBinding binding;

        public viewHolder(@NonNull ItemTagBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }

}
