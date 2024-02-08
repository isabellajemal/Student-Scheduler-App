package com.example.calendarapp.ui.Calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalendarModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CalendarModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Add new class");
    }

    public LiveData<String> getText() {
        return mText;
    }
}