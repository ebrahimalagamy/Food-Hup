package com.iqadv.collections.ui.auth.signUp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iqadv.collections.model.LoginModel;
import com.iqadv.collections.repository.Repository;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class SignUpViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<ResponseBody> malRegister;

    public SignUpViewModel() {
        repository = new Repository();
    }

    public MutableLiveData<ResponseBody> register(String method, String type,
                                                String email, String pass, String name, String mobile) {
        if (malRegister == null) {
            malRegister = repository.signUp(method, type, email, pass, name, mobile);
        }
        return malRegister;
    }

}
