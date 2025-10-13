package com.example.studentsystem.service;

import com.example.studentsystem.dto.AddStudentRequestDto;
import com.example.studentsystem.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);


    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);


    void deleteStudentById(Long id);

    StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);
}
