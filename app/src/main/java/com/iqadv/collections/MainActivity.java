package com.iqadv.collections;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.iqadv.collections.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    BadgeDrawable badgeDrawable;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        badgeDrawable = BadgeDrawable.create(this);
        bindNav();
//        cartIconBadge();

    }

    private void cartIconBadge() {
        badgeDrawable = binding.bottomNavView.getOrCreateBadge(R.id.cartFragment);
        badgeDrawable.setBadgeTextColor(getColor(R.color.myRed));
        badgeDrawable.setMaxCharacterCount(5000);
        badgeDrawable.setBadgeTextColor(getColor(R.color.white));
        badgeDrawable.setNumber(5);
    }

    private void bindNav() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
        NavController navController = null;
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavView);
        assert navController != null;
        NavigationUI.setupWithNavController(bottomNav, navController);

        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
            if (!(navDestination.getId() == R.id.homeFragment || navDestination.getId() == R.id.mapViewFragment
                    || navDestination.getId() == R.id.cartFragment
                    || navDestination.getId() == R.id.favoriteFragment
                    || navDestination.getId() == R.id.notificationFragment))

                binding.bottomNavView.setVisibility(View.GONE);
            else
                binding.bottomNavView.setVisibility(View.VISIBLE);
        });

    }
}