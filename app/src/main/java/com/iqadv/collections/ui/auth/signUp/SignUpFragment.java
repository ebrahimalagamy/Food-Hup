package com.iqadv.collections.ui.auth.signUp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {
    private FragmentSignUpBinding binding;
    private String name;
    private String email;
    private String pass;
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews();

    }

    private void bindViews() {
        binding.btnSignUp.setOnClickListener(view -> {
            if (validateForm()) {
                Log.e("number", name + email + pass);

                navController = Navigation.findNavController(view);
                NavDirections action = SignUpFragmentDirections.actionSignUpFragmentToPhoneNumberFragment(name, email, pass);
                navController.navigate(action);
            }

        });

        binding.tvLogIn.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment));
    }

    private Boolean validateForm() {
        name = binding.etFullName.getText().toString();
        email = binding.etEmail.getText().toString();
        pass = binding.etPassword.getText().toString();
        if (name.isEmpty()) {
            binding.etFullName.requestFocus();
            Toast.makeText(requireActivity(), "Full Name Require", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.isEmpty()) {
            binding.etEmail.requestFocus();
            Toast.makeText(requireActivity(), "E-mail Require", Toast.LENGTH_SHORT).show();

            return false;
        }
        if (pass.isEmpty()) {
            binding.etPassword.requestFocus();
            Toast.makeText(requireActivity(), "Password Require", Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }

}