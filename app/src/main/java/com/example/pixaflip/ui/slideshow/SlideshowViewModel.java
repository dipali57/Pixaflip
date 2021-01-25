package com.example.pixaflip.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Show Favorite PDF here which are marked in displayPdfActivity.");
    }
    public LiveData<String> getText() {
        return mText;
    }
}