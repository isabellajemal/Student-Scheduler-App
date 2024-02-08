package com.example.calendarapp.ui.Assignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.calendarapp.R;  // Add this import statement
import com.example.calendarapp.databinding.FragmentAssignmentsandtodolistBinding;
import com.example.calendarapp.ui.ClassDetails;
import com.example.calendarapp.ui.ClassListAdapter;  // Add this import statement
public class AssignmentInputFragment extends Fragment {

    private FragmentAssignmentsandtodolistBinding binding;
    private AssignmentViewModel assignmentViewModel;
    private ClassListAdapter classListAdapter;
    private EditText editTextCourseName;

    private EditText editTextTime;

    private EditText editTextInstructor;
    private Button addButton;
    private ListView listViewClasses;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        binding = FragmentAssignmentsandtodolistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize views
        EditText editTextCourseName = root.findViewById(R.id.editTextCalendarCourseName);
        EditText editTextTime = root.findViewById(R.id.editTextCalendarDayAndTime);
        EditText editTextInstructor = root.findViewById(R.id.editTextCalendarInstructor);
        Button addButton = root.findViewById(R.id.buttonExamsAdd);
        ListView listViewClasses = root.findViewById(R.id.listViewClasses);

//        // Set up adapter for the ListView
//        classListAdapter = new ClassListAdapter(requireContext(), assignmentViewModel.getClassList().getValue());
//        listViewClasses.setAdapter((ListAdapter) classListAdapter);
//        assignmentViewModel.getClassList().observe(getViewLifecycleOwner(), newClasses -> {
//            classListAdapter.updateData(newClasses); // Assuming you have an updateData method in your adapter
//        });
//
//
//        // Set up add button click listener
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String courseName = editTextCourseName.getText().toString();
//                String time = editTextTime.getText().toString();
//                String instructor = editTextInstructor.getText().toString();
//
//                if (!courseName.isEmpty() && !time.isEmpty() && !instructor.isEmpty()) {
//                    assignmentViewModel.addClass(new ClassDetails(courseName, time, instructor));
//                    classListAdapter.notifyDataSetChanged();
//                    clearInputFields();
//                } else {
//                    Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        return root;
    }


    private void clearInputFields() {
        editTextCourseName.getText().clear();
        editTextTime.getText().clear();
        editTextInstructor.getText().clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}