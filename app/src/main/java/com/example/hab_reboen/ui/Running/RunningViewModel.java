package com.example.hab_reboen.ui.Running;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RunningViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RunningViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Daily running with partner ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}