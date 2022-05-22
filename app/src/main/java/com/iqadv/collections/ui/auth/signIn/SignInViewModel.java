package com.iqadv.collections.ui.auth.signIn;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iqadv.collections.model.LoginModel;
import com.iqadv.collections.repository.Repository;

public class SignInViewModel extends ViewModel {
    private final Repository repository;
    private MutableLiveData<LoginModel> malLogin;

    public SignInViewModel() {
        repository = new Repository();
    }

    public MutableLiveData<LoginModel> getUser(String email, String pass, String type, String method) {
        if (malLogin == null) {
            malLogin = repository.signIn(email, pass, type, method);
        }
        return malLogin;
    }
}
