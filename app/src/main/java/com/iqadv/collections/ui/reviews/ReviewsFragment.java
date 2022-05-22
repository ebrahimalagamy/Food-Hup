package com.iqadv.collections.ui.reviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentReviewsBinding;
import com.iqadv.collections.model.ReviewModel;
import com.iqadv.collections.models.HistoryOrderModel;
import com.iqadv.collections.ui.adapters.HistoryOrderAdapter;

import java.util.ArrayList;

public class ReviewsFragment extends Fragment {
    private FragmentReviewsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReviewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindReview();
        bindToolBar();
    }

    private void bindToolBar() {
        binding.ToolBarReviews.tvScreenName.setVisibility(View.VISIBLE);
        binding.ToolBarReviews.tvScreenName.setText("Reviews");
    }

    private void bindReview() {
        ArrayList<ReviewModel> modelArrayList = new ArrayList<>();

        modelArrayList.add(new ReviewModel("Alyce Lambo","25/06/2020","Really convenient and the points system helps benefit loyalty. Some mild glitches here and there, but nothing too egregious. Obviously needs to roll out to more remote.",
                R.drawable.review1));
        modelArrayList.add(new ReviewModel("Gonela Solom","25/06/2020","Been a life saver for keeping our sanity during the pandemic, although they could improve some of their ui and how they handle specials as it often is unclear how to use them or everything is sold out so fast it feels a bit bait and switch. Still I'd be stir crazy and losing track of days without so...",
                R.drawable.review2));
        modelArrayList.add(new ReviewModel("Alyce Lambo","25/06/2020","Got an intro offer of 50% off first order that did not work..... I have scaled the app to find a contact us button but only a spend with us button available.",
                R.drawable.review3));

        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(getActivity(), modelArrayList);
        binding.recyclerViewReview.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        binding.recyclerViewReview.setAdapter(reviewsAdapter);
    }
}