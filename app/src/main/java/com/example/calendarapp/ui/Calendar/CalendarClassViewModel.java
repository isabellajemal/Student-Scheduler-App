package com.example.calendarapp.ui.Calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.calendarapp.ui.ClassDetails;

import java.util.ArrayList;
import java.util.List;

public class CalendarClassViewModel extends ViewModel {
    private MutableLiveData<List<ClassDetails>> classList;

    public CalendarClassViewModel() {
        classList = new MutableLiveData<>();
        classList.setValue(new ArrayList<>());
    }

    public LiveData<List<ClassDetails>> getClassList() {
        return classList;
    }

    // Add a new class
    public void addClass(ClassDetails classDetails) {
        List<ClassDetails> currentList = classList.getValue();
        if (currentList != null) {
            currentList.add(classDetails);
            classList.setValue(currentList);
        }
    }

    // Edit an existing class
    public void editClass(int position, ClassDetails editedClassDetails) {
        List<ClassDetails> currentList = classList.getValue();
        if (currentList != null && position >= 0 && position < currentList.size()) {
            currentList.set(position, editedClassDetails);
            classList.setValue(currentList);
        }
    }

    // Delete a class
    public void deleteClass(int position) {
        List<ClassDetails> currentList = classList.getValue();
        if (currentList != null && position >= 0 && position < currentList.size()) {
            currentList.remove(position);
            classList.setValue(currentList);
        }
    }
}

