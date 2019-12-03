package com.example.calculator.ui.mass;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MassViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public MassViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mass fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
