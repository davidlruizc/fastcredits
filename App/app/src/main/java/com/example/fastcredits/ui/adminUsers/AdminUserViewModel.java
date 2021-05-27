package com.example.fastcredits.ui.adminUsers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdminUserViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdminUserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is users fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}