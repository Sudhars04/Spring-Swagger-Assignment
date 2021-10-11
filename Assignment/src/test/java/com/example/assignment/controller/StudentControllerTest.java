package com.example.assignment.controller;

import com.example.assignment.Application;
import com.example.assignment.model.Student;
import com.example.assignment.service.StudentService;
import com.example.assignment.utils.StudentTestConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(StudentController.class)
@ContextConfiguration(classes= Application.class)
public class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StudentService mockStudentService;

    @Test
    void testGetAllStudents() throws Exception {
        List<Student> studentList = Arrays.asList(
                new Student(StudentTestConstants.id, StudentTestConstants.name,
                       StudentTestConstants.date, StudentTestConstants.courseList));
        when(mockStudentService.getAllStudents()).thenReturn(studentList);

        MockHttpServletResponse response = mockMvc.perform(get("/getAllUsers").with(httpBasic("assignment","assignment"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"studentId\":1," +
                        "\"studentName\":\"studentName\"," + "\"dateOfAdmission\":\"10/01/2021\"," +
                        "\"courseList\":[{" + "\"courseId\":1," +
                        "\"courseName\":\"courseName\"" +
                        "}]}]"))
                .andReturn().getResponse();
    }

    @Test
    void addAllStudentsTest() throws Exception{
        Student mockStudent = new Student(StudentTestConstants.id, StudentTestConstants.name,
                StudentTestConstants.date, StudentTestConstants.courseList);
        when(mockStudentService.addStudent(ArgumentMatchers.<Student>any())).thenReturn(mockStudent);

        MockHttpServletResponse response = mockMvc.perform(post("/addAndUpdateUser").with(httpBasic("assignment","assignment"))
                .content(objectMapper.writeValueAsString(mockStudent))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"studentId\":1," +
                        "\"studentName\":\"studentName\"," + "\"dateOfAdmission\":\"10/01/2021\"," +
                        "\"courseList\":[{" + "\"courseId\":1," +
                        "\"courseName\":\"courseName\"" +
                        "}]}"))
                .andReturn().getResponse();
    }

    @Test
    void testRemoveStudent() throws Exception {
        int id=1;
        when(mockStudentService.removeStudent(anyInt())).thenReturn("Deleted");

        MockHttpServletResponse response= mockMvc.perform(delete("/removeUser").with(httpBasic("assignment","assignment"))
                .param("id","1").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted"))
                .andReturn().getResponse();
    }
}