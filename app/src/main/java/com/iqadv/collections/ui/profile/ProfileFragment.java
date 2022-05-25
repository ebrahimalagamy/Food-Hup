package com.iqadv.collections.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iqadv.collections.databinding.FragmentProfileBinding;
import com.iqadv.collections.db.RoomDB;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.etName.setText(RoomDB.getDatabase(requireActivity()).getUserDao().getUserInfo().getName());
        binding.etEmail.setText(RoomDB.getDatabase(requireActivity()).getUserDao().getUserInfo().getEmail());
        binding.etMobile.setText(RoomDB.getDatabase(requireActivity()).getUserDao().getUserInfo().getMobile());
    }
}