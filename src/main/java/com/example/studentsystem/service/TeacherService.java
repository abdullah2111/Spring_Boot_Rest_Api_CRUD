package com.example.studentsystem.service;


import com.example.studentsystem.model.Teacher;
import com.example.studentsystem.repository.TeacherRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;





@Data
@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;


    public List<Teacher> getAllTeachers() {
       return teacherRepository.findAll();
    }
}
