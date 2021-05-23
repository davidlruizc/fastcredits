package com.example.fastcredits.ui.clients;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClientsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ClientsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is clients fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
