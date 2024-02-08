package com.example.calendarapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendarapp.R;

import java.util.Collections;
import java.util.List;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ClassViewHolder> {
    private List<ClassDetails> classList;
    private Context context;
    private OnEditButtonClickListener editButtonClickListener;

    // Constructor
    public ClassListAdapter(Context context, List<ClassDetails> classList, OnEditButtonClickListener editButtonClickListener) {
        this.context = context;
        this.classList = classList;
        this.editButtonClickListener = editButtonClickListener;
    }

    public interface OnEditButtonClickListener {
        void onEditButtonClick(int position, String name, String time, String instructor);
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        public TextView courseNameTextView;
        public TextView timeTextView;
        public TextView instructorTextView;
        public Button deleteButton;
        public Button editButton;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);


            courseNameTextView = itemView.findViewById(R.id.editTextClassName);
            timeTextView = itemView.findViewById(R.id.editTextClassMeetingDays);
            instructorTextView = itemView.findViewById(R.id.editTextClassInstructor);
            editButton = itemView.findViewById(R.id.editButtonClass);
            deleteButton = itemView.findViewById(R.id.deleteButtonClass);
        }
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newCourseName = holder.courseNameTextView.getText().toString();
                String newDayAndTime = holder.timeTextView.getText().toString();
                String newInstructor = holder.instructorTextView.getText().toString();

                editButtonClickListener.onEditButtonClick(holder.getAdapterPosition(), newCourseName, newDayAndTime, newInstructor);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                classList.remove(position);
                notifyItemRemoved(position);
            }
        });

        Collections.sort(classList);
        ClassDetails currentClass = classList.get(position);

        holder.courseNameTextView.setText(currentClass.getCourseName());
        holder.timeTextView.setText(currentClass.getTime());
        holder.instructorTextView.setText(currentClass.getInstructor());
    }

    @Override
    public int getItemCount() {
        return classList != null ? classList.size() : 0;
    }

    public void updateData(List<ClassDetails> newClassList) {
        this.classList = newClassList;
        notifyDataSetChanged();
    }
}

