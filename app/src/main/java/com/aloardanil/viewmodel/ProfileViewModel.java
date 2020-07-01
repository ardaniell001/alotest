package com.aloardanil.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mName;
    private MutableLiveData<String> mPhone;
    private MutableLiveData<String> mPosition;

    public ProfileViewModel() {
        mName = new MutableLiveData<>();
        mPhone = new MutableLiveData<>();
        mPosition = new MutableLiveData<>();
        mName.setValue("Ardanil Maulana");
        mPhone.setValue("+6282386730796");
        mPosition.setValue("Android Developer");
    }

    public LiveData<String> getName() {
        return mName;
    }

    public LiveData<String> getPhone() {
        return mPhone;
    }

    public LiveData<String> getPosition() {
        return mPosition;
    }
}