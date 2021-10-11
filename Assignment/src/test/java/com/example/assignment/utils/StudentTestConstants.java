package com.example.assignment.utils;

import com.example.assignment.model.Course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class StudentTestConstants {

    public static final int id = 1;

    public static final String name = "studentName";

   public static final String date = "10/01/2021";

   public static final List<Course> courseList = new ArrayList<>(Arrays.asList(new Course(1,"courseName")));
}
