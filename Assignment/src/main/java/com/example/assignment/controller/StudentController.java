package com.example.assignment.controller;

import com.example.assignment.model.Student;
import com.example.assignment.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "studentsapi")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Operation(summary = "View list of Students along with their details")
    @GetMapping("/getAllUsers")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Add a Student's record")
    @PostMapping("/addAndUpdateUser")
    public Student addStudent(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Enter Student's details") @RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @Operation(summary = "Remove a Student's record")
    @DeleteMapping("/removeUser")
    public String removeStudent(@Parameter(description = "Enter Student Id") @RequestParam int id) {
        System.out.println("Hi from controller method");
        return studentService.removeStudent(id);
    }

    @Operation(summary = "Update a Student's record")
    @PutMapping("/updateUser")
    public Student updateStudent(@Parameter(description = "Enter Student Id") @RequestParam int id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Enter Student's details") @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }
}