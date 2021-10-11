package com.example.assignment.service;

import com.example.assignment.model.Student;
import com.example.assignment.repository.StudentRepository;
import com.example.assignment.utils.StudentTestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SequenceGenerator sequenceGenerator;

    @Mock
    private Student student;

    @InjectMocks
    private StudentService studentService;

    Student mockStudent;
    List<Student> mockStudentList;

    @BeforeEach
    public void init() {
        mockStudent = new Student(StudentTestConstants.id, StudentTestConstants.name,
                StudentTestConstants.date, StudentTestConstants.courseList);
        mockStudentList = Arrays.asList(mockStudent);
    }

    @Test
    public void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(mockStudentList);

        List<Student> studentList = studentService.getAllStudents();

        assertEquals(mockStudentList, studentList);
    }


    @Test
    public void testAddStudent() {
        when(studentRepository.insert(ArgumentMatchers.<Student>any())).thenReturn(mockStudent);
        when(sequenceGenerator.generateSequence(Student.SEQUENCE_NAME)).thenReturn(1);
        Student newStudent = studentService.addStudent(mockStudent);

        assertEquals("studentName", newStudent.getStudentName());
        assertEquals(1, newStudent.getStudentId());
    }

    @Test
    public void testRemoveStudent() {
        studentService.removeStudent(anyInt());
        verify(studentRepository, times(1)).deleteById(anyInt());
    }
}