package com.iqadv.collections.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentHomeBinding;
import com.iqadv.collections.db.RoomDB;
import com.iqadv.collections.ui.adapters.homeAdapters.CategoryHomeAdapter;
import com.iqadv.collections.ui.adapters.homeAdapters.PopularItemsAdapter;
import com.iqadv.collections.ui.adapters.resturant.RestaurantsAdapter;
import com.iqadv.collections.utlils.Constants;
import com.iqadv.collections.utlils.LoadingDialog;
import com.iqadv.collections.utlils.RecyclerViewClick;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RecyclerViewClick {
    private ArrayList<String> arrayListSpinner;
    private FragmentHomeBinding binding;
    private SlidingRootNav slidingRootNav;

    private NavController navController;
    private HomeViewModel homeViewModel;
    private LoadingDialog loadingDialog;
    RestaurantsAdapter restaurantsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        navController = Navigation.findNavController(view);
        homeViewModel = new HomeViewModel();
        arrayListSpinner = new ArrayList<>();
        loadingDialog = new LoadingDialog(requireActivity());
        loadingDialog.startLoading();


        bindToolBar();
        bindView();
        bindMainCategoryRecycler();
        bindRestaurantRecycler();
        bindPopularItemRecycler();
        bindSpinner();
        bindSliding();
        bindUserInfo();

    }

    private void bindUserInfo() {

        TextView name = slidingRootNav.getLayout().findViewById(R.id.tv_name);
        name.setText(RoomDB.getDatabase(requireActivity()).getUserDao().getUserInfo().getName());

        TextView email = slidingRootNav.getLayout().findViewById(R.id.tv_email);
        email.setText(RoomDB.getDatabase(requireActivity()).getUserDao().getUserInfo().getEmail());
    }

    // get popular items data
    private void bindPopularItemRecycler() {
        homeViewModel.getHomeCategories(Constants.HomeFeatures).observe(requireActivity(), restaurantsResponse -> {

            PopularItemsAdapter restCategoryAdapter = new PopularItemsAdapter(restaurantsResponse.getFoods(), getActivity());
            binding.recyclerViewRestCategory.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL, false));
            binding.recyclerViewRestCategory.setAdapter(restCategoryAdapter);
        });
    }

    // get restaurant data
    private void bindRestaurantRecycler() {
        homeViewModel.getHomeCategories(Constants.HomeFeatures).observe(requireActivity(), restaurantsResponse -> {
            restaurantsAdapter = new RestaurantsAdapter(restaurantsResponse.getRestaurants(), requireActivity());
            binding.recyclerViewRestaurant.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL, false));
            binding.recyclerViewRestaurant.setAdapter(restaurantsAdapter);
            loadingDialog.stopLoading();

        });
//        restaurantsAdapter.setWhenClickListener(new RestaurantsAdapter.OnItemsClickListener() {
//            @Override
//            public void onItemClick() {
//                Toast.makeText(requireActivity(), "gggg", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

    // get category data
    private void bindMainCategoryRecycler() {
        homeViewModel.getHomeCategories(Constants.HomeFeatures).observe(requireActivity(), restaurantsResponse -> {
            CategoryHomeAdapter mainCategoryAdapter = new CategoryHomeAdapter(restaurantsResponse.getCategories(), getActivity());
            binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL, false));
            binding.recyclerViewCategory.setAdapter(mainCategoryAdapter);
        });


    }

    private void bindSpinner() {
        homeViewModel.getAddresses().observe(requireActivity(), addressModels -> {
            for (int i = 0; i < addressModels.size(); i++) {
                arrayListSpinner.add(addressModels.get(i).getStreet());
                binding.include2.tvSpinner.setText(addressModels.get(0).getStreet());
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, arrayListSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.include2.spinner.setAdapter(arrayAdapter);
        binding.include2.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tutorialsName = adapterView.getItemAtPosition(i).toString();
                binding.include2.tvSpinner.setText(tutorialsName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void bindSliding() {

        slidingRootNav = new SlidingRootNavBuilder(requireActivity())
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withMenuLayout(R.layout.sliding_drawer)
                .withGravity(SlideGravity.LEFT)
                .inject();

//        TextView name = slidingRootNav.getLayout().findViewById(R.id.tv_name);
//        name.setText(obj.getName());

        slidingRootNav.getLayout().findViewById(R.id.tvMyOrderDrawer).setOnClickListener(view -> {
            navController.navigate(R.id.myOrderFragment);
            slidingRootNav.closeMenu();
        });
        slidingRootNav.getLayout().findViewById(R.id.tvMyProfile).setOnClickListener(view -> {
            navController.navigate(R.id.profileFragment);
            slidingRootNav.closeMenu();
        });
        slidingRootNav.getLayout().findViewById(R.id.tvDeliveryAddress).setOnClickListener(view -> {
            navController.navigate(R.id.selectAddressFragment);
            slidingRootNav.closeMenu();
        });
        slidingRootNav.getLayout().findViewById(R.id.tvPaymentMethod).setOnClickListener(view -> {
            navController.navigate(R.id.paymentFragment);
            slidingRootNav.closeMenu();
        });

        slidingRootNav.getLayout().findViewById(R.id.tvDeliveryAddress).setOnClickListener(view -> {
            navController.navigate(R.id.deliveryAddressFragment);
            slidingRootNav.closeMenu();
        });

    }

    private void bindView() {
        binding.ivFilter.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_filterFragment));
        binding.tvSearch.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_searchFragment));
    }

    private void bindToolBar() {
        binding.include2.ivIconBack.setVisibility(View.GONE);
        binding.include2.ivDrawer.setVisibility(View.VISIBLE);
        binding.include2.tvScreenName.setVisibility(View.VISIBLE);
        binding.include2.tvScreenName.setText(R.string.delivery_to);
        binding.include2.rivUser.setVisibility(View.VISIBLE);
        binding.include2.spinner.setVisibility(View.VISIBLE);
        binding.include2.tvSpinner.setVisibility(View.VISIBLE);
        binding.include2.ivDrawer.setOnClickListener(view -> slidingRootNav.openMenu());
        binding.include2.rivUser.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_profileFragment));
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLongItemClick(int position) {

    }
}