package com.example.assignment.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "STUDENTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Transient
    public static final String SEQUENCE_NAME = "students_sequence";
    @Id
    private int studentId;
    private String studentName;
    private String dateOfAdmission;
    private List<Course> courseList;
}