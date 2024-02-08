package com.example.calendarapp.ui.Assignments;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.calendarapp.R;  // Add this import statement
//import com.example.calendarapp.databinding.FragmentHomeBinding;
import com.example.calendarapp.databinding.FragmentExamsBinding;
import com.example.calendarapp.ui.ClassDetails;
import com.example.calendarapp.ui.ClassListAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AssignmentFragment extends Fragment implements ClassListAdapter.OnEditButtonClickListener {

    private FragmentExamsBinding binding;
    private AssignmentViewModel assignmentViewModel;
    private RecyclerView recyclerViewExams;
    private ClassListAdapter classListAdapter;
    private EditText editTextExamName;
    private EditText editTextExamLocation;
    private EditText editTextExamDate;
    private Button addButton;
    private Button deleteButton;
    private List<ClassDetails> classList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        AssignmentViewModel assignmentViewModel= new ViewModelProvider(this).get(AssignmentViewModel.class);
        binding = FragmentExamsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        classListAdapter = new ClassListAdapter(getActivity(), classList);
//        recyclerViewExams.setAdapter(classListAdapter);
        // Initialize views
        editTextExamName = root.findViewById(R.id.editTextExamTitle);
        editTextExamDate = root.findViewById(R.id.editTextExamDayAndTime);
        editTextExamLocation = root.findViewById(R.id.editTextExamLocation);
        addButton = root.findViewById(R.id.buttonExamsAdd);
        deleteButton = root.findViewById(R.id.buttonItemAssignmentDelete);

        recyclerViewExams = root.findViewById(R.id.RecyclerViewExams);
        recyclerViewExams.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize class list and adapter
        classList = new ArrayList<>();
        classListAdapter = new ClassListAdapter(getActivity(), classList, this);
        recyclerViewExams.setAdapter(classListAdapter);

        classList.add(new ClassDetails("CS 1332 Exam 1", "5:00pm", "Faulker"));
        classList.add(new ClassDetails("MATH 1554 Exam 1", "9:00am", "Barone"));
        classList.add(new ClassDetails("CS 2340 Mini Assessment", "8:30am", "Pedro"));
        // classList.add(new ClassDetails("Math 1554", "5:00", "Sal Barone"));
       // classList.add(new ClassDetails("Intro to Programming", "9:30", "Pedro"));


        //assignmentViewModel.getClassList().observe(getViewLifecycleOwner(), newClasses -> {
        //    classListAdapter.updateData(newClasses);

        classListAdapter.notifyDataSetChanged();
        classListAdapter.updateData(classList);

        //Initialize views
//        editTextExamName = root.findViewById(R.id.editTextCalendarCourseName);
//        editTextExamLocation= root.findViewById(R.id.editTextExamDayAndTime);
//        editTextExamDate = root.findViewById(R.id.editTextCalendarInstructor);
//        addButton = root.findViewById(R.id.buttonExamsAdd);
//        deleteButton = root.findViewById(R.id.buttonItemAssignmentDelete);

//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String examName = editTextExamName.getText().toString();
//                String time = editTextExamLocation.getText().toString();
//                String dateAndTime = editTextExamDate.getText().toString();
//
//                if (!TextUtils.isEmpty(examName) && !TextUtils.isEmpty(time) && !TextUtils.isEmpty(dateAndTime)) {
//                    classList.add(new ClassDetails(examName, time, dateAndTime));
//                    classListAdapter.notifyDataSetChanged();
//                    clearInputFields();
//                } else {
//                    Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//     sortButton.setOnClickListener(v -> {
//        List<ClassDetails> sortedList = new ArrayList<>(assignmentViewModel.getClassList().getValue());
//        sortClassesByName(sortedList);
//        classListAdapter.updateData(sortedList);
//        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = editTextExamName.getText().toString();
                String time = editTextExamDate.getText().toString();
                String instructor = editTextExamLocation.getText().toString();

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
    public List<ClassDetails> getClassList() {
        return classList;
    }
    private void clearInputFields() {
        editTextExamName.getText().clear();
        editTextExamLocation.getText().clear();
        editTextExamDate.getText().clear();
    }

    public void onEditButtonClick(int position, String name, String time, String location) {
        // Populate the EditText fields with the received class details
        editTextExamName.setText(name);
        editTextExamDate.setText(time);
        editTextExamLocation.setText(location);
        addButton.setText("Save");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextExamName.getText().toString();
                String time = editTextExamDate.getText().toString();
                String location = editTextExamLocation.getText().toString();
                ClassDetails course = new ClassDetails(name, time, location);


                classList.remove(position);
                classList.add(position, course);
                // Notify the adapter that the data has changed
                classListAdapter.notifyItemChanged(position);
                addButton.setText("Add");
            }
        });
    }


    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }
    // Edit an existing class
    // Delete a class
//    private void sortClassesByName(List<ClassDetails> arr) {
//        if (arr == null) {
//            throw new IllegalArgumentException("Cannot insert null data into data structure.");
//        }
//
//        LinkedList<ClassDetails>[] buckets = new LinkedList[256];
//
//        for (int i = 0; i < 256; i++) {
//            buckets[i] = new LinkedList<>();
//        }
//
//        int maxLength = 0;
//        for (ClassDetails classDetails : arr) {
//            int length = classDetails.getCourseName().length();
//            if (length > maxLength) {
//                maxLength = length;
//            }
//        }
//
//        for (int i = maxLength - 1; i >= 0; i--) {
//            for (ClassDetails classDetails : arr) {
//                char ch = i < classDetails.getCourseName().length() ? classDetails.getCourseName().charAt(i) : ' ';
//                int bucket = ch;
//                buckets[bucket].addLast(classDetails);
//            }
//
//            int index = 0;
//            for (LinkedList<ClassDetails> bucket : buckets) {
//                if (bucket != null) {
//                    while (bucket.size() != 0) {
//                        arr.set(index, bucket.removeFirst());
//                        index++;
//                    }
//                }
//            }
//        }
//    }

    //@Override
    //public void onClassClick(int position) {

   // }

    //@Override
   // public void onDeleteButtonClick(int position) {
    //    assignmentViewModel.deleteClass(position);
    //}
}
