package com.example.calendarapp.ui.ToDoList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ToDoListViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ToDoListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}