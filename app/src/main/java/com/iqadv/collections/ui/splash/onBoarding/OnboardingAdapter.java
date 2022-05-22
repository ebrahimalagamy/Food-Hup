package com.iqadv.collections.ui.splash.onBoarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iqadv.collections.R;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {
    private List<OnboardingItem> onboardingItemList;

    public OnboardingAdapter(List<OnboardingItem> onboardingItemList) {
        this.onboardingItemList = onboardingItemList;
    }

    @Override
    public OnboardingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OnboardingViewHolder
                (LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.item_container_onboarding, parent, false));
    }

    @Override
    public void onBindViewHolder(OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItemList.get(position));

    }

    @Override
    public int getItemCount() {
        return onboardingItemList.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ImageView imageView;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
            imageView = itemView.findViewById(R.id.imageOnboarding);
        }

        void setOnboardingData(OnboardingItem onboardingData) {
            title.setText(onboardingData.getTitle());
            description.setText(onboardingData.getDescription());
            imageView.setImageResource(onboardingData.getImage());
        }
    }
}
