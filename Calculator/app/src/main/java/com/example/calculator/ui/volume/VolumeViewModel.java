package com.example.calculator.ui.volume;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VolumeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public VolumeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is volume fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
