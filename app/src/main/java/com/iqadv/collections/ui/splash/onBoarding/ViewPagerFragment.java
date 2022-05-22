package com.iqadv.collections.ui.splash.onBoarding;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentViewPagerBinding;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragment extends Fragment {
    NavController navController;
    private FragmentViewPagerBinding binding;
    private OnboardingAdapter onboardingAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        setupOnboardingItems();
        ViewPager2 onBoardingViewPager = view.findViewById(R.id.onBoardingViewPager);
        onBoardingViewPager.setAdapter(onboardingAdapter);
        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);
        onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });
        binding.btnNextScreen.setOnClickListener(view1 -> {

            if (onBoardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                onBoardingViewPager.setCurrentItem(onBoardingViewPager.getCurrentItem() + 1);
                YoYo.with(Techniques.Bounce)
                        .duration(700)
                        .playOn(view.findViewById(R.id.btnNextScreen));
            } else {
                onBoardFinished();
                navController.navigate(R.id.action_viewPagerFragment_to_signUpFragment);

            }
        });
    }

    private void onBoardFinished() {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Finished",true);
        editor.apply();
    }

    private void setupOnboardingItems() {
        List<OnboardingItem> onboardingItemList = new ArrayList<>();

        OnboardingItem itemPlayOnline1 = new OnboardingItem();
        itemPlayOnline1.setTitle("Browse your menu\nand order directly");
        itemPlayOnline1.setDescription("Our app can send you everywhere, even\nspace. For only $2.99 per month");
        itemPlayOnline1.setImage(R.drawable.onboarding_screen_2);

        OnboardingItem itemPlayOnline2 = new OnboardingItem();
        itemPlayOnline2.setTitle("Even to space with\nus! Together");
        itemPlayOnline2.setDescription("Our app can send you everywhere, even\nspace. For only $2.99 per month");
        itemPlayOnline2.setImage(R.drawable.onboarding_screen_3);

        OnboardingItem itemPlayOnline3 = new OnboardingItem();
        itemPlayOnline3.setTitle("Pickup delivery at\nyour door");
        itemPlayOnline3.setDescription("Our app can send you everywhere, even\nspace. For only $2.99 per month");
        itemPlayOnline3.setImage(R.drawable.onboarding_screen_4);

        onboardingItemList.add(itemPlayOnline1);
        onboardingItemList.add(itemPlayOnline2);
        onboardingItemList.add(itemPlayOnline3);

        onboardingAdapter = new OnboardingAdapter(onboardingItemList);
    }

    private void setupOnboardingIndicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getActivity());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getActivity(),
                    R.drawable.onboarding_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            binding.layoutOnboardingIndicator.addView(indicators[i]);

        }
    }

    private void setCurrentOnboardingIndicator(int index) {
        int childCount = binding.layoutOnboardingIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) binding.layoutOnboardingIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(),
                        R.drawable.onboarding_indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(),
                        R.drawable.onboarding_indicator_inactive));
            }
        }
    }
}