package com.iqadv.collections.ui.splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentWelcomePageBinding;

public class WelcomePageFragment extends Fragment {
    private FragmentWelcomePageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding = FragmentWelcomePageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews();
    }

    private void bindViews() {
        binding.btnSkip.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(
                        R.id.action_welcomePageFragment_to_signUpFragment));

        binding.btnStartWithEmailOrPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBoardFinished()){
                    Navigation.findNavController(view).navigate(
                            R.id.action_welcomePageFragment_to_homeFragment);
                }else {
                    Navigation.findNavController(view).navigate(
                            R.id.action_welcomePageFragment_to_viewPagerFragment);
                }


            }
        }) ;

        binding.tvAlreadyHaveAccount.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(
                        R.id.signInFragment));
    }

    private boolean onBoardFinished() {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
        return sharedPref.getBoolean("Finished", false);
    }
}