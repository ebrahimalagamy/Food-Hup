package com.iqadv.collections.ui.auth.phone;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentPhoneNumberBinding;
import com.iqadv.collections.ui.auth.signUp.SignUpViewModel;
import com.iqadv.collections.utlils.LoadingDialog;

import java.io.IOException;

import okhttp3.ResponseBody;


public class PhoneNumberFragment extends Fragment {

    private FragmentPhoneNumberBinding binding;
    private PhoneNumberFragmentArgs args;
    private LoadingDialog loadingDialog;

    private SignUpViewModel signUpViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPhoneNumberBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        args = PhoneNumberFragmentArgs.fromBundle(getArguments());
        signUpViewModel = new SignUpViewModel();
        loadingDialog = new LoadingDialog(requireActivity());

        binding.btnSendPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoading();
                signUpViewModel.register("signup", "user",
                        args.getEmail(), args.getPass(), args.getName(),
                        binding.ccp.getSelectedCountryCode() + binding.etPhoneNumber.getText().toString()
                ).observe(requireActivity(), new Observer<ResponseBody>() {
                    @Override
                    public void onChanged(ResponseBody responseBody) {
                        try {
                            if (responseBody.string().equals("email_exist")){
                                Toast.makeText(requireActivity(), "Email exist try again", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).popBackStack();

                            }else if (responseBody.string().equals("mobile_exist")){
                                Toast.makeText(requireActivity(), "Mobile exist try again", Toast.LENGTH_SHORT).show();

                            }
                            Toast.makeText(requireActivity(), "Sign up Successfully", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.signInFragment);
                                Log.e("responseBody", responseBody.string());
                            loadingDialog.stopLoading();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
                Log.e("number", binding.ccp.getSelectedCountryCode() + binding.etPhoneNumber.getText().toString());

            }
        });


    }
}