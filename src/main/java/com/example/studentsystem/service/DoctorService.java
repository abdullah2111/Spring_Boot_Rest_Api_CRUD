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
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;


    public Doctor createDoctor(Doctor incoming) {
        log.info("Creating new doctor: {}", incoming.getName());
        Doctor doctor = new Doctor();
        doctor.setName(incoming.getName());
        doctor.setSpecialization(incoming.getSpecialization());
        doctor.setEmail(incoming.getEmail());


        Doctor savedDoctor = doctorRepository.save(doctor);

        if(incoming.getDepartments() == null && incoming.getDepartments().isEmpty()){
            return savedDoctor;
        }

        Set<Department> departments = new HashSet<>();

        for(Department d :  incoming.getDepartments()){
            if(d.getId()!=null){
                Department dbDpt = departmentRepository.findById(d.getId())
                        .orElseThrow(() -> new RuntimeException("Department not found with id: " + d.getId()));

                dbDpt.getDoctors().add(savedDoctor);
                departments.add(dbDpt);

            }

        }
        savedDoctor.setDepartments(departments);
        departmentRepository.saveAll(departments);

        return savedDoctor;






    }





    public Optional<Doctor> getDoctorById(Long id) {
        log.info("Fetching doctor by id: {}", id);
        return doctorRepository.findById(id);
    }





    public Doctor addDepartmentToDoctor(Long doctorId, Long deptId) {
        log.info("Adding Department {} to Doctor {}", deptId, doctorId);

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctorId));
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found: " + deptId));



        doctor.getDepartments().add(department);
        department.getDoctors().add(doctor);

        log.info("Successfully added Department {} to Doctor {}", department.getName(), doctor.getName());

        return doctorRepository.save(doctor);
    }





}
