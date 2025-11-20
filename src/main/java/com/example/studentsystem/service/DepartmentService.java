package com.example.studentsystem.service;


import com.example.studentsystem.model.Department;
import com.example.studentsystem.model.Doctor;
import com.example.studentsystem.repository.DepartmentRepository;
import com.example.studentsystem.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;


    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }



    public Department addHeadDoctor(Long departmentId, Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new RuntimeException("doctor not found with id " + doctorId));
        Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new RuntimeException("department not found with id " + departmentId));

        department.setHeadDoctor(doctor);
        return departmentRepository.save(department);

    }

    @Transactional
    public Department changeHeadDoctor(Long departmentId, Long newDoctorId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("department not found with id " + departmentId));

        Doctor newHead = doctorRepository.findById(newDoctorId)
                .orElseThrow(() -> new RuntimeException("doctor not found with id " + newDoctorId));



        department.setHeadDoctor(newHead);
        return departmentRepository.save(department);
    }



    @Transactional
    public Department removeHeadDoctor(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("department not found with id " + departmentId));

        department.setHeadDoctor(null);
        return departmentRepository.save(department);
    }




}
