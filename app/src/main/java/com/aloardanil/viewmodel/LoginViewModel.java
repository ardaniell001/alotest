package com.aloardanil.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private String defaultusername = "user";
    private String defaultpassword = "123456";

    public LoginViewModel() {

    }

    public boolean validateUser(String username, String password){
        boolean isValid = false;

        if (username.equals(defaultusername) && password.equals(defaultpassword)){
            isValid = true;
        }

        return isValid;
    }

}