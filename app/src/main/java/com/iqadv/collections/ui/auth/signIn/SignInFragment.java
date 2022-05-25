package com.iqadv.collections.ui.auth.signIn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentSignInBinding;
import com.iqadv.collections.db.RoomDB;
import com.iqadv.collections.utlils.LoadingDialog;

public class SignInFragment extends Fragment {
    private FragmentSignInBinding binding;
    private SignInViewModel signInViewModel;
    private String email;
    private String pass;
    private LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signInViewModel = new SignInViewModel();
        loadingDialog = new LoadingDialog(getActivity());
        bindView();
    }

    private void bindView() {

        binding.btnLognIn.setOnClickListener(view -> {

            if (validateForm()) {
                loadingDialog.startLoading();
                Log.e("vald", email + pass);
                signInViewModel.getUser(email, pass, "user", "login")
                        .observe(requireActivity(), loginModel -> {
                            RoomDB.getDatabase(requireActivity()).getUserDao().insertUserModel(loginModel);
                            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_homeFragment);
                            loadingDialog.stopLoading();
                        });


            }
        });
        binding.tvForgetPassword.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_forgetPasswordFragment));
        binding.tvSignUp.setOnClickListener(view ->
                Navigation.findNavController(view).popBackStack());

        binding.toolBarLogin.ivIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });
    }

    private Boolean validateForm() {
        email = binding.etEmail.getText().toString();
        pass = binding.etPassword.getText().toString();
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