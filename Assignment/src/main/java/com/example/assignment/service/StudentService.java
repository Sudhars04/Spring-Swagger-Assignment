package com.example.assignment.service;

import com.example.assignment.model.Student;
import com.example.assignment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    public List<Student> getAllStudents() {
        List<Student> studentList=null;
        studentList=studentRepository.findAll();
        return studentList;
    }

    public Student addStudent(Student student) {
        student.setStudentId(sequenceGenerator.generateSequence(Student.SEQUENCE_NAME));
        studentRepository.insert(student);
        return student;
    }

    public String removeStudent(Integer id) {
        studentRepository.deleteById(id);
        System.out.println("Hi all from removeStudent method");
        return "Deleted";
    }

    public Student updateStudent(int id,Student student){
        studentRepository.deleteById(id);
        studentRepository.insert(student);
        return student;
    }
}
