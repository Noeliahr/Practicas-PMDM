package com.example.practicapmdm_movies.ui.Peliculas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PeliculasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PeliculasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}