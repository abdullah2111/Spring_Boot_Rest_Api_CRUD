package com.example.studentsystem.repository;

import com.example.studentsystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}