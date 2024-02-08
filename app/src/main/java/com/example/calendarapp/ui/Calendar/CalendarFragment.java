package com.example.calendarapp.ui.Calendar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.calendarapp.ui.ClassDetails;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendarapp.R;  // Add this import statement
import com.example.calendarapp.databinding.FragmentCalendarBinding;
import com.example.calendarapp.ui.ClassListAdapter;  // Add this import statement


public class CalendarFragment extends Fragment implements ClassListAdapter.OnEditButtonClickListener {
//    private FragmentHomeBinding binding;
    private FragmentCalendarBinding binding;
    private ClassListAdapter classListAdapter;
    private EditText editTextCourseName;
    private EditText editTextTime;
    private EditText editTextInstructor;
    private Button addButton;
    private Button deleteButton;
    private ListView listViewClasses;
    private List<ClassDetails> classList;
    private RecyclerView recyclerViewClasses;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalendarClassViewModel calendarClassViewModel = new ViewModelProvider(this).get(CalendarClassViewModel.class);
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize views
        editTextCourseName = root.findViewById(R.id.editTextCalendarCourseName);
        editTextTime = root.findViewById(R.id.editTextCalendarDayAndTime);
        editTextInstructor = root.findViewById(R.id.editTextCalendarInstructor);
        addButton = root.findViewById(R.id.buttonExamsAdd);
        deleteButton = root.findViewById(R.id.buttonItemAssignmentDelete);

        recyclerViewClasses = root.findViewById(R.id.recyclerViewClassesCalendar); // Make sure you have a RecyclerView with this ID in your layout
        recyclerViewClasses.setLayoutManager(new LinearLayoutManager(getActivity()));
        classList = new ArrayList<>();
        classListAdapter = new ClassListAdapter(getActivity(), classList, this);
        recyclerViewClasses.setAdapter(classListAdapter);

        classList.add(new ClassDetails("CS 3510", "Tuesday and Thursday @ 12:30pm", "Van Den Brand"));
        classList.add(new ClassDetails("CS 1332", "Tuesday and Thursday @ 9:30am", "HB"));
        classList.add(new ClassDetails("MATH 1554", "Monday, Wednesday @ 5:00pm", "Barone"));
        classList.add(new ClassDetails("PHIL 3050", "Monday and Wednesday @ 3:30pm", "Smith"));


        // assignmentViewModel.getClassList().observe(getViewLifecycleOwner(), newClasses -> {
        classListAdapter.notifyDataSetChanged();
        classListAdapter.updateData(classList);
        // Assuming you have an updateData method in your adapter
       // classList.add(new ClassDetails("Intro to Programming", "9:30", "Pedro"));
        //classListAdapter = new ClassListAdapter(getContext(), classList);
        //recyclerViewClasses.setAdapter(classListAdapter);
        //classListAdapter.notifyDataSetChanged();
       // classListAdapter.updateData(classList);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = editTextCourseName.getText().toString();
                String time = editTextTime.getText().toString();
                String instructor = editTextInstructor.getText().toString();

                if (!TextUtils.isEmpty(courseName) && !TextUtils.isEmpty(time) && !TextUtils.isEmpty(instructor)) {
                    classList.add(new ClassDetails(courseName, time, instructor));
                    classListAdapter.notifyDataSetChanged();
                    clearInputFields();
                } else {
                    Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }
    private void clearInputFields() {
        editTextCourseName.getText().clear();
        editTextTime.getText().clear();
        editTextInstructor.getText().clear();
    }

    public void onEditButtonClick(int position, String name, String time, String instructor) {
        // Populate the EditText fields with the received class details
        editTextCourseName.setText(name);
        editTextTime.setText(time);
        editTextInstructor.setText(instructor);
        addButton.setText("Save");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextCourseName.getText().toString();
                String time = editTextTime.getText().toString();
                String instructor = editTextInstructor.getText().toString();
                ClassDetails course = new ClassDetails(name, time, instructor);


                classList.remove(position);
                classList.add(position, course);
                // Notify the adapter that the data has changed
                classListAdapter.notifyItemChanged(position);
                addButton.setText("Add New Class");
            }
        });
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }
}