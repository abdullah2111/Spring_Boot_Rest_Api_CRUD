package com.example.studentsystem.service;


import com.example.studentsystem.model.Teacher;
import com.example.studentsystem.repository.TeacherRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Data
@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;


    public List<Teacher> getAllTeachers() {
       return teacherRepository.findAll();
    }


    public List<Teacher> findTeachersByName(String name) {
        return teacherRepository.findByNameLike(name);
    }



    public List<Teacher> findTeachersByDepartment(String department) {
        return teacherRepository.findByDepartment(department);
    }




    public List<Teacher> findAllTeachersSortedByNameAsc() {
        return teacherRepository.findAllTeachersSortedByNameAsc();
    }




    public List<Teacher> findAllTeachersSortedByNameDesc() {
        return teacherRepository.findAllTeachersSortedByNameDesc();
    }




    public Optional<Teacher> findTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }



    public List<Object[]> countTeachersByDepartment() {
        return teacherRepository.countTeachersByDepartment();
    }



    public void updateTeacherDepartment(Long id, String department) {
        teacherRepository.updateTeacherDepartment(id, department);
    }



    public List<Teacher> findTeachersByNameNative(String name) {
        return teacherRepository.findTeachersByNameNative(name);
    }
}
