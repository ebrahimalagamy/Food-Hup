package com.iqadv.collections.ui.reviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iqadv.collections.databinding.ItemReviewsBinding;
import com.iqadv.collections.model.ReviewModel;

import java.util.ArrayList;
import java.util.List;


public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.viewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ReviewModel> list;

    public ReviewsAdapter(Context context, ArrayList<ReviewModel> list) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new viewHolder(ItemReviewsBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        ReviewModel upcomingOderModel = list.get(position);
        holder.binding.tvName.setText(upcomingOderModel.getUserName());
        holder.binding.tvDate.setText(upcomingOderModel.getDate());
        holder.binding.tvDescription.setText(upcomingOderModel.getDesc());
        holder.binding.civUser.setImageResource(upcomingOderModel.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        private ItemReviewsBinding binding;

        public viewHolder(@NonNull ItemReviewsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
