package com.iqadv.collections.ui.splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentSplashBinding;
import com.iqadv.collections.utlils.AppPermissions;


public class SplashFragment extends Fragment {
    private Animation topAnim, bottomAnim;
    private NavController navController;
    private FragmentSplashBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        topAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.buttom_animation);

        binding.ivSplashIcon.setAnimation(topAnim);
        binding.linearSplashText.setAnimation(bottomAnim);
        navController = Navigation.findNavController(view);

        new Handler().postDelayed(() -> {
            if (!(AppPermissions.isLocationOk(requireActivity()))){
                AppPermissions.requestLocationPermission(requireActivity());
            }
            if (onBoardFinished()){
                navController.navigate(R.id.action_splashFragment_to_homeFragment);
            }else {
                navController.navigate(R.id.action_splashFragment_to_welcomePageFragment);

            }
        }, 3000);
    }

    private boolean onBoardFinished() {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
        return sharedPref.getBoolean("Finished", false);
    }

    @Override
    public void onDetach() {
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onDetach();
    }
}