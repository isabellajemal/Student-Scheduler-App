package com.example.calendarapp.ui.ToDoList;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendarapp.R;
import com.example.calendarapp.databinding.FragmentAssignmentsandtodolistBinding;
import com.example.calendarapp.ui.ClassDetails;
import com.example.calendarapp.ui.ClassListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ToDoListFragment extends Fragment implements ClassListAdapter.OnEditButtonClickListener {

    private FragmentAssignmentsandtodolistBinding binding;
    private ClassListAdapter classListAdapter;
    private EditText editTextToDoTitle;
    private EditText editTextToDoDueDate;
    private EditText editTextToDoClassOrDescription;
    private Button addButton;
    private Button deleteButton;
    private ListView listViewClasses;
    private List<ClassDetails> classList;
    private RecyclerView recyclerViewClasses;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToDoListViewModel notificationsViewModel =
                new ViewModelProvider(this).get(ToDoListViewModel.class);

        binding = FragmentAssignmentsandtodolistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize views
        editTextToDoTitle = root.findViewById(R.id.editTextToDoTitle);
        editTextToDoDueDate = root.findViewById(R.id.editTextToDoDueDate);
        editTextToDoClassOrDescription = root.findViewById(R.id.editTextToDoClassOrDescription);
        addButton = root.findViewById(R.id.buttonToDoAdd);
        deleteButton = root.findViewById(R.id.buttonItemAssignmentDelete);

        recyclerViewClasses = root.findViewById(R.id.RecyclerViewAssignmentsAndToDo); // Make sure you have a RecyclerView with this ID in your layout
        recyclerViewClasses.setLayoutManager(new LinearLayoutManager(getActivity()));
        classList = new ArrayList<>();
        classListAdapter = new ClassListAdapter(getActivity(), classList, this);
        recyclerViewClasses.setAdapter(classListAdapter);

        classList.add(new ClassDetails("Project 1", "Friday @11:59pm", "CS 1332     Progress: 50%"));
        classList.add(new ClassDetails("Meal prep", "Sunday @ 5:00pm", "Make chicken and beans      Progress: 0%"));
        classList.add(new ClassDetails("HW01", "Wednesday @ 11:59pm", "MAth 1554        Progress: 75%"));
        classList.add(new ClassDetails("Call bank", "2/15/24", "Call BofA       Progress: 0%"));


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
                String courseName = editTextToDoTitle.getText().toString();
                String time = editTextToDoDueDate.getText().toString();
                String instructor = editTextToDoClassOrDescription.getText().toString();

                if (!TextUtils.isEmpty(courseName) && !TextUtils.isEmpty(time) && !TextUtils.isEmpty(instructor)) {
                    classList.add(new ClassDetails(courseName, time, instructor));
                    classListAdapter.notifyDataSetChanged();
//                    clearInputFields();
                }
            }
        });

        return root;
    }

    private void clearInputFields() {
        editTextToDoTitle.getText().clear();
        editTextToDoDueDate.getText().clear();
        editTextToDoClassOrDescription.getText().clear();
    }

    public void onEditButtonClick(int position, String name, String time, String instructor) {
        // Populate the EditText fields with the received class details
        editTextToDoTitle.setText(name);
        editTextToDoDueDate.setText(time);
        editTextToDoClassOrDescription.setText(instructor);
        addButton.setText("Save");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextToDoTitle.getText().toString();
                String time = editTextToDoDueDate.getText().toString();
                String instructor = editTextToDoClassOrDescription.getText().toString();
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}