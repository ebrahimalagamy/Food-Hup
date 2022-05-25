package com.iqadv.collections.ui.order.myOrder;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentMyOrderBinding;
import com.iqadv.collections.models.HistoryOrderModel;
import com.iqadv.collections.ui.adapters.HistoryOrderAdapter;
import com.iqadv.collections.ui.adapters.UpcomingOrderAdapter;

import java.util.ArrayList;

public class MyOrderFragment extends Fragment {

    private FragmentMyOrderBinding binding;
    private UpcomingOrderAdapter upcomingOrderAdapter;
    private HistoryOrderAdapter historyOrderAdapter;
    private OrderViewModel orderViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyOrderBinding.inflate(inflater, container, false);
        orderViewModel = new OrderViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindToggle();
        bindToolBar();
        binding.toggleMyOrder.item1.setText("Upcoming");
        binding.toggleMyOrder.item2.setText("History");

        bindRecycler();


    }

    private void bindRecycler() {
        orderViewModel.getUpcomingOder().observe(requireActivity(), upcomingOderModels -> {
            upcomingOrderAdapter = new UpcomingOrderAdapter(getActivity(), upcomingOderModels);
            binding.recyclerViewMyOrder.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.VERTICAL, false));
            binding.recyclerViewMyOrder.setAdapter(upcomingOrderAdapter);

        });


        ArrayList<HistoryOrderModel> historyOrderModels = new ArrayList<>();
        historyOrderModels.add(new HistoryOrderModel(1, "20 Jun, 10:30", "6 items", "Starbucks", "Delivered",
                "12.5$", R.drawable.profile_image_rest));

        historyOrderModels.add(new HistoryOrderModel(2, "15 Jun, 12:00", "2 items", "Mc", "Delivered",
                "5$", R.drawable.profile_image_rest));
        historyOrderModels.add(new HistoryOrderModel(1, "20 Jun, 10:30", "6 items", "Starbucks", "Delivered",
                "12.5$", R.drawable.profile_image_rest));

        historyOrderModels.add(new HistoryOrderModel(2, "15 Jun, 12:00", "2 items", "Mc", "Delivered",
                "5$", R.drawable.profile_image_rest));


        historyOrderAdapter = new HistoryOrderAdapter(getActivity(), historyOrderModels);
        binding.recyclerViewMyOrder.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        binding.recyclerViewMyOrder.setAdapter(upcomingOrderAdapter);


    }

    private void bindToolBar() {
        binding.toolBarMyOrder.tvScreenName.setVisibility(View.VISIBLE);
        binding.toolBarMyOrder.tvScreenName.setText("My Orders");
        binding.toolBarMyOrder.rivUser.setVisibility(View.VISIBLE);
    }

    private void bindToggle() {
        ColorStateList colorStateList = binding.toggleMyOrder.item2.getTextColors();
        binding.toggleMyOrder.select.setOnClickListener(view13 -> {

        });
        binding.toggleMyOrder.item1.setOnClickListener(view1 -> {
            binding.recyclerViewMyOrder.setAdapter(upcomingOrderAdapter);
            binding.toggleMyOrder.select.animate().x(0F).setDuration(100);
            binding.toggleMyOrder.item1.setTextColor(Color.WHITE);
            binding.toggleMyOrder.item2.setTextColor(colorStateList);

        });

        binding.toggleMyOrder.item2.setOnClickListener(view12 -> {
            binding.recyclerViewMyOrder.setAdapter(historyOrderAdapter);

            binding.toggleMyOrder.item1.setTextColor(colorStateList);
            binding.toggleMyOrder.item2.setTextColor(Color.WHITE);
            int size = binding.toggleMyOrder.item2.getWidth();
            binding.toggleMyOrder.select.animate().x(size).setDuration(100);
        });
    }
}