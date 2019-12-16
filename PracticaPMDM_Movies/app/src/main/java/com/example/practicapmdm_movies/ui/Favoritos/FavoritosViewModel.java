package com.example.practicapmdm_movies.ui.Favoritos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoritosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavoritosViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }
}