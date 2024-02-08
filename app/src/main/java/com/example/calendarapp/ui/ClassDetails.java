package com.example.calendarapp.ui;

public class ClassDetails implements Comparable<ClassDetails> {

    private String courseName;
    private String time;
    private String instructor;

    public ClassDetails(String courseName, String time, String instructor) {
        this.courseName = courseName;
        this.time = time;
        this.instructor = instructor;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTime() {
        return time;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public int compareTo(ClassDetails classDetails) {
        return this.courseName.compareTo(classDetails.getCourseName());
    }
}